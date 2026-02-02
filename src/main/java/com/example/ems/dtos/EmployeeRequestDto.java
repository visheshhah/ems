package com.example.ems.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmployeeRequestDto {

    @NotBlank(message = "Employee name cannot be Null")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Department name cannot be Null")
    private String department;
}
