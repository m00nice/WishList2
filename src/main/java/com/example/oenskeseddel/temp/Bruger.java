package com.example.oenskeseddel.temp;

public class Bruger {
    private String Email;
    private String Username;
    private String Password;
    private String PasswordRE;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public String getPasswordRE() {
        return PasswordRE;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPasswordRE(String passwordRE) {
        PasswordRE = passwordRE;
    }
}

