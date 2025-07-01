package com.example.logisticscompany;

public class User {

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String email;
    private final int roleId;
    private final String gender;
    private int experienceLevel;

    // Єдиний екземпляр для Singleton
    private static User instance;

    // Приватний конструктор, щоб заборонити створення нових об'єктів
    private User(String name, String lastName,
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

    public User(int experienceLevel, String gender,
                int roleId, String email,
                String password, String username,
                String lastName, String firstName) {
        this.experienceLevel = experienceLevel;
        this.gender = gender;
        this.roleId = roleId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    // Метод для отримання екземпляра Singleton
    public static User getInstance(String firstName, String lastName,
                                   String username, String password,
                                   String email, int roleId,
                                   String gender, int experienceLevel) {
        if (instance == null) {
            instance = new User(experienceLevel, gender, roleId, email, password, username, lastName, firstName);
        }
        return instance;
    }

    public static User getInstance() {
        if (instance == null) {
            throw new IllegalStateException("User instance has not been initialized yet. Call getInstance(String name, int experienceLevel) first.");
        }
        return instance;
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

    public static void setInstance(User instance) {
        User.instance = instance;
    }
}