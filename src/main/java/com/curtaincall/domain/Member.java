package com.curtaincall.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class Member {

    private Long memberNo;
    private String name;
    private String id;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
    private String gender;
    private LocalDateTime createAt;
}
