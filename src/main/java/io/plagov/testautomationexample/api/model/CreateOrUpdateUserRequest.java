package io.plagov.testautomationexample.api.model;

public record CreateOrUpdateUserRequest(
        String name,
        String gender,
        String email,
        String status
) { }
