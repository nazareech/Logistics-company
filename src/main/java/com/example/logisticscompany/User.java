package com.example.logisticscompany;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private int roleId;
    private String gender;
    private int experienceLevel;

    public User() {}

    // Приватний конструктор, щоб заборонити створення нових об'єктів
    public User(String name, String lastName,
                 String username, String password,
                 String email, int roleId,
                 String gender, int experienceLevel) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
        this.gender = gender;
        this.firstName = name;
        this.lastName = lastName;
        this.experienceLevel = experienceLevel;
    }


    // Getter для імені
    public String getName() {
        return firstName + lastName;
    }

    // Getter для рівня досвіду
    public int getExperienceLevel() {
        return experienceLevel;
    }

    // Метод для збільшення рівня досвіду
    public void increaseExperience(int amount) {
        experienceLevel += amount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getGender() {
        return gender;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}