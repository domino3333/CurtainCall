package com.curtaincall.mapper;


import com.curtaincall.domain.Member;

public interface MemberMapper {


    void signUp(Member member);

    Integer isAlreadyExistedId(String id);

}
