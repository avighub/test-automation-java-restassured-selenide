package com.saucelab.pages.enums;

public enum UserRole {
    STANDARD_USER,
    LOCKED_OUT_USER,
    PROBLEM_USER,
    PERFORMANCE_GLITCH_USER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
