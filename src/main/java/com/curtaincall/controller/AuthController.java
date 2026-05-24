package com.curtaincall.controller;


import com.curtaincall.dto.SignUpRequest;
import com.curtaincall.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class AuthController {


    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request){




        return null;
    }
}
