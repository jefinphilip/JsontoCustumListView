package com.example.jefin.jsontocustumlistview;

/**
 * Created by jefin on 24/9/16.
 */
public class Contact {
    private String username,email;

   public Contact ( String username,String email)

    {
        this.setEmail(email);
        this.setUsername(username);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
