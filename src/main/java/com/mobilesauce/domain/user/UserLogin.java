package com.mobilesauce.domain.user;

/**
 * A domain class representing a user's login.
 * It has the basics: username and password.
 *
 * @author sso
 */
public class UserLogin {

    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
