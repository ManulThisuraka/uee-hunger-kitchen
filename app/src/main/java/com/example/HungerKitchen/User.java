package com.example.HungerKitchen;

public class User {

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;

    public User() {
    }

    public User(String fName, String Lname, String email, String phone, String password) {
        this.firstname = fName;
        this.lastname = Lname;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}