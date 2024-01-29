package com.yerokha.demo.paymentservice.dto;

import java.time.LocalDate;

public record RegistrationRequest(
        String firstName,
        String lastName,
        String username,
        String email,
        String password,
        LocalDate dob
) {
}
