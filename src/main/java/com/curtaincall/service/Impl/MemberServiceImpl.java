package com.curtaincall.service.Impl;

import com.curtaincall.domain.Member;
import com.curtaincall.dto.SignUpRequest;
import com.curtaincall.mapper.MemberMapper;
import com.curtaincall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public void signUp(SignUpRequest request) {


        if(memberMapper.isAlreadyExistedId(request.getId())>0){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "이미 사용중인 아이디입니다."
            );
        }
        

        memberMapper.signUp(Member.builder()
                .id(request.getId())
                .password(request.getPassword())
                .gender(request.getGender())
                .name(request.getName())
                .dateOfBirth(request.getDateOfBirth())
                .email(request.getEmail())
                .build());


    }
}