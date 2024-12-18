package com.library.validation;

import com.library.model.Member;
import com.library.exception.ValidationException;

public class MemberValidator {
    public void validate(Member member) {
        if (member == null) {
            throw new ValidationException("Member cannot be null");
        }
        validateName(member.getName());
        validateEmail(member.getEmail());
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Member name cannot be empty");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !isValidEmail(email)) {
            throw new ValidationException("Invalid email format");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}