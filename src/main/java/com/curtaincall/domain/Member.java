package com.curtaincall.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Member {

    private Long memberNo;
    private String name;
    private String id;
    private String password;
    private String email;
    private Date dateOfBirth;
    private String gender;
    private LocalDateTime createAt;
}
