package io.plagov.testautomationexample.model;

public record CreateUserResponse(
        int id,
        String name,
        String gender,
        String email,
        String status
) { }
