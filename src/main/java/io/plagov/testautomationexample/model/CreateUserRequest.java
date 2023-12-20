package io.plagov.testautomationexample.model;

public record CreateUserRequest(
        String name,
        String gender,
        String email,
        String status
) { }
