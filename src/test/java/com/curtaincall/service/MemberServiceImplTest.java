package com.curtaincall.service;


import com.curtaincall.domain.Member;
import com.curtaincall.dto.SignUpRequest;
import com.curtaincall.mapper.MemberMapper;
import com.curtaincall.service.Impl.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MemberServiceImplTest {

    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberServiceImpl memberService;


    //service에서 mapper까지 그 사이에 넘어간 인자들이 잘 넘어갔는지 확인하는 단위테스트
    @Test
    void signUp(){
        SignUpRequest request = new SignUpRequest();
        request.setName("홍길동");
        request.setId("hong");
        request.setPassword("1234");
        request.setEmail("hong@test.com");
        request.setDateOfBirth(LocalDate.of(2000, 1, 1));
        request.setGender("M");

        memberService.signUp(request);

        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
        verify(memberMapper).signUp(captor.capture());

        Member savedMember = captor.getValue();

        assertThat(savedMember.getName()).isEqualTo("홍길동");
        assertThat(savedMember.getId()).isEqualTo("hong");
        assertThat(savedMember.getPassword()).isEqualTo("1234");
        assertThat(savedMember.getEmail()).isEqualTo("hong@test.com");
        assertThat(savedMember.getDateOfBirth()).isEqualTo(LocalDate.of(2000, 1, 1));
        assertThat(savedMember.getGender()).isEqualTo("M");

    }


}
