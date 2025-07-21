package com.spring.basic.chap6.service;

import com.spring.basic.chap3_2.entity.Member;
import com.spring.basic.chap5_4.dto.response.MemberListResponse;
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

    // 회원 개별 조회시 비지니스로직 처리
    public MemberListResponse findOneMember(String account) {
        log.info("회원 개별조회를 컨트롤러로부터 위임받음!");

        // 2. 예외처리: 계정명이 10글자 초과인지 확인
        if (account.length() > 10) {
            String errorMessage = "계정명이 10글자를 넘어서는 안됩니다!";
            log.warn(errorMessage);
            // 강제 예외를 일으킴 - 코드 흐름이 끊김
            throw new RuntimeException(errorMessage);
        }

        // 1. 회원 계정명을 통해 데이터베이스에서 회원정보를 조회해야 함.
        //     => DB 접근은 Repository에게 위임
        Member foundMember = memberRepository.findByAccount(account);

        //              회원이 존재하지 않을 가능성도 처리
        if (foundMember == null) {
            String errorMessage = "회원이 존재하지 않습니다.";
            log.warn(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        // 3. 데이터베이스에서 가져온 회원정보를 그대로 응답하면 X - 정제가 필요
        return MemberListResponse.from(foundMember);

    }
}
