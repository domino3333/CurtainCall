package com.curtaincall.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpRequest {

    private String name;
    private String id;
    private String password;
    private LocalDate dateOfBirth;
    private String email;
    private String gender;
}
