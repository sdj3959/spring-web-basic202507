package com.spring.basic.chap6.service;

import com.spring.basic.chap6.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    // 의존 객체 설정
    private final MemberRepository memberRepository;

    // 회원 개별 조회시 비즈니스 로직 처리
    public void findOneMember(String account) {
        log.info("회원 개별조회를 컨트롤러로부터 위임받음!");
        // 1. 회원 계정명을 통해 데이터베이스에서 회원정보를 조회해야 함.
        //     ==> DB 접근은 Repository에게 위임
        // 2. 예외처리: 계정명이 10글자 미만인지 확인
        //              회원이 존재하지 않을 가능성도 처리
        // 3. 데이터베이스에서 가져온 회원정보를 그대로 응답하면 X - 정제가 필요
    }
}
