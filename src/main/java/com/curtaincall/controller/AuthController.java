package com.curtaincall.controller;


import com.curtaincall.dto.SignUpRequest;
import com.curtaincall.mapper.MemberMapper;
import com.curtaincall.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/member")
public class AuthController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request){

        memberService.signUp(request);
        log.info("signUp 컨트롤러 진입");

        return ResponseEntity.ok("회원가입 성공");
    }
}
