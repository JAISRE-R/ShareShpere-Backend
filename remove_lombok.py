import os
import re

def uppercase_first(s):
    if not s: return s
    return s[0].upper() + s[1:]

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    if 'lombok' not in content and '@Data' not in content and '@RequiredArgsConstructor' not in content and '@Builder' not in content:
        return

    # Remove lombok imports
    content = re.sub(r'import lombok\.[a-zA-Z.]+;\n?', '', content)
    
    has_data = '@Data' in content
    has_builder = '@Builder' in content
    has_req_args = '@RequiredArgsConstructor' in content
    has_no_args = '@NoArgsConstructor' in content
    has_all_args = '@AllArgsConstructor' in content
    
    # Remove annotations
    content = re.sub(r'@Data\s*\n', '', content)
    content = re.sub(r'@Builder\s*\n', '', content)
    content = re.sub(r'@NoArgsConstructor\s*\n', '', content)
    content = re.sub(r'@AllArgsConstructor\s*\n', '', content)
    content = re.sub(r'@RequiredArgsConstructor\s*\n', '', content)
    
    class_match = re.search(r'public\s+(class|enum)\s+([A-Za-z0-9_]+)', content)
    if not class_match: return
    
    is_class = class_match.group(1) == 'class'
    class_name = class_match.group(2)
    
    if not is_class:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        return
        
    # extract all private fields
    # Match: private [final] Type name [= value];
    fields = []
    final_fields = []
    
    # Simple regex to find private fields
    for match in re.finditer(r'private\s+(final\s+)?([A-Za-z0-9_<>\[\]\?]+)\s+([a-zA-Z0-9_]+)\s*(?:=[^;]+)?;', content):
        is_final = bool(match.group(1))
        t = match.group(2)
        n = match.group(3)
        fields.append((t, n, is_final))
        if is_final:
            final_fields.append((t, n))
            
    added_code = "\n"

    # Getters and setters
    if has_data and fields:
        for t, n, is_final in fields:
            cap_n = uppercase_first(n)
            
            # Skip if getter exists
            if f"get{cap_n}(" not in content and f"is{cap_n}(" not in content:
                added_code += f"    public {t} get{cap_n}() {{ return {n}; }}\n"
            
            # Skip if setter exists
            if not is_final and f"set{cap_n}(" not in content:
                added_code += f"    public void set{cap_n}({t} {n}) {{ this.{n} = {n}; }}\n"

    # Constructors
    if has_no_args or has_data:
        if f"public {class_name}()" not in content:
            added_code += f"\n    public {class_name}() {{}}\n"
            
    if has_all_args or (has_data and not has_no_args):
        if fields:
            args = ", ".join([f"{t} {n}" for t, n, _ in fields])
            assigns = "\n".join([f"        this.{n} = {n};" for t, n, _ in fields])
            # Only add if not already there
            if f"public {class_name}({args})" not in content:
                added_code += f"\n    public {class_name}({args}) {{\n{assigns}\n    }}\n"

    if has_req_args and final_fields:
        args = ", ".join([f"{t} {n}" for t, n in final_fields])
        assigns = "\n".join([f"        this.{n} = {n};" for t, n in final_fields])
        if f"public {class_name}(" not in content: # Avoid clashing with existing constructor
            added_code += f"\n    public {class_name}({args}) {{\n{assigns}\n    }}\n"

    # Builder
    if has_builder and fields:
        builder_name = f"{class_name}Builder"
        added_code += f"\n    public static {builder_name} builder() {{ return new {builder_name}(); }}\n"
        added_code += f"\n    public static class {builder_name} {{\n"
        for t, n, _ in fields:
            added_code += f"        private {t} {n};\n"
        added_code += "\n"
        for t, n, _ in fields:
            added_code += f"        public {builder_name} {n}({t} {n}) {{ this.{n} = {n}; return this; }}\n"
        
        args_call = ", ".join([f"this.{n}" for t, n, _ in fields])
        # Generate target constructor explicitly for builder just in case
        added_code += f"        public {class_name} build() {{ return new {class_name}({args_call}); }}\n"
        added_code += "    }\n"
        
        # Ensure target constructor for builder exists
        full_args = ", ".join([f"{t} {n}" for t, n, _ in fields])
        full_assigns = "\n".join([f"        this.{n} = {n};" for t, n, _ in fields])
        if f"public {class_name}({full_args})" not in content and not has_all_args:
             added_code += f"\n    public {class_name}({full_args}) {{\n{full_assigns}\n    }}\n"

    # Insert before last closing brace
    last_brace_idx = content.rfind('}')
    if last_brace_idx != -1 and len(added_code.strip()) > 0:
        content = content[:last_brace_idx] + added_code + "\n}"
        
    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

def main():
    base_dir = r"c:\Users\My\OneDrive\Desktop\shareSphere\backend\src\main\java"
    # Find all java files
    for root, dirs, files in os.walk(base_dir):
        for file in files:
            if file.endswith(".java"):
                process_file(os.path.join(root, file))

if __name__ == '__main__':
    main()
