package com.sharesphere.dto;


public class AuthenticationResponse {
    private String token;
    private Long id;
    private String email;
    private String name;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public AuthenticationResponse() {}

    public AuthenticationResponse(String token, Long id, String email, String name) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static AuthenticationResponseBuilder builder() { return new AuthenticationResponseBuilder(); }

    public static class AuthenticationResponseBuilder {
        private String token;
        private Long id;
        private String email;
        private String name;

        public AuthenticationResponseBuilder token(String token) { this.token = token; return this; }
        public AuthenticationResponseBuilder id(Long id) { this.id = id; return this; }
        public AuthenticationResponseBuilder email(String email) { this.email = email; return this; }
        public AuthenticationResponseBuilder name(String name) { this.name = name; return this; }
        public AuthenticationResponse build() { return new AuthenticationResponse(this.token, this.id, this.email, this.name); }
    }

}