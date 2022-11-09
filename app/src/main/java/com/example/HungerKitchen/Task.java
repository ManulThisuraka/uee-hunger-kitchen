package com.example.HungerKitchen;

public class Task {
    private String name;
    private String skills;
    private String date;
    private String role;
    private String message;

    public Task() {}

    public Task(String name, String skills, String date, String role, String message) {
        this.name = name;
        this.skills = skills;
        this.date = date;
        this.role = role;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
