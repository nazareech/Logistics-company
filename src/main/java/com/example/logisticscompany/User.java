package com.example.logisticscompany;

public class User {

    private String name;
    private int experienceLevel;

    // Єдиний екземпляр для Singleton
    private static User instance;

    // Приватний конструктор, щоб заборонити створення нових об'єктів
    private User(String name, int experienceLevel) {
        this.name = name;
        this.experienceLevel = experienceLevel;
    }

    // Метод для отримання екземпляра Singleton
    public static User getInstance(String name, int experienceLevel) {
        if (instance == null) {
            instance = new User(name, experienceLevel);
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
        return name;
    }

    // Getter для рівня досвіду
    public int getExperienceLevel() {
        return experienceLevel;
    }

    // Метод для збільшення рівня досвіду
    public void increaseExperience(int amount) {
        experienceLevel += amount;
    }

    // Метод для встановлення нового рівня досвіду
    public void setExperienceLevel(int newExperienceLevel) {
        this.experienceLevel = newExperienceLevel;
    }
}