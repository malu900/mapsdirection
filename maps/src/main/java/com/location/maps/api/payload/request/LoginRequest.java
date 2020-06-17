package com.location.maps.api.payload.request;

// define the request and response payloads that the APIs will use
import javax.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty
    private String usernameOrEmail;

    @NotEmpty
    private String password;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}