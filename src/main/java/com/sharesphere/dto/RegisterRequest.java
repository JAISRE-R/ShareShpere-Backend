package com.sharesphere.dto;


public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String location;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public RegisterRequest() {}

    public RegisterRequest(String name, String email, String password, String phone, String location) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.location = location;
    }

    public static RegisterRequestBuilder builder() { return new RegisterRequestBuilder(); }

    public static class RegisterRequestBuilder {
        private String name;
        private String email;
        private String password;
        private String phone;
        private String location;

        public RegisterRequestBuilder name(String name) { this.name = name; return this; }
        public RegisterRequestBuilder email(String email) { this.email = email; return this; }
        public RegisterRequestBuilder password(String password) { this.password = password; return this; }
        public RegisterRequestBuilder phone(String phone) { this.phone = phone; return this; }
        public RegisterRequestBuilder location(String location) { this.location = location; return this; }
        public RegisterRequest build() { return new RegisterRequest(this.name, this.email, this.password, this.phone, this.location); }
    }

}