package io.plagov.testautomationexample.model;

public record CreateOrUpdateUserRequest(
        String name,
        String gender,
        String email,
        String status
) { }
