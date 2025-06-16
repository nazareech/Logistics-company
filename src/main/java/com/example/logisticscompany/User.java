package com.example.logisticscompany;

public class User {

    private final String firstName;
    private final String lastName;
    private int experienceLevel;

    // Єдиний екземпляр для Singleton
    private static User instance;

    // Приватний конструктор, щоб заборонити створення нових об'єктів
    private User(String name, String lastName, int experienceLevel) {
        this.firstName = name;
        this.lastName = lastName;
        this.experienceLevel = experienceLevel;
    }

    // Метод для отримання екземпляра Singleton
    public static User getInstance(String firstName, String lastName, int experienceLevel) {
        if (instance == null) {
            instance = new User(firstName, lastName, experienceLevel);
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
}