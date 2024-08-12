package com.example.projectmanagementsystem.project_management_system.model;

public class JwtResponse {
    private String jwtToken;
    private String username;

    private JwtResponse(Builder builder) {
        this.jwtToken = builder.jwtToken;
        this.username = builder.username;
    }

    public String getUsername() {
        return username;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String jwtToken;
        private String username;

        public Builder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public JwtResponse build() {
            return new JwtResponse(this);
        }
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "jwtToken='" + jwtToken + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
