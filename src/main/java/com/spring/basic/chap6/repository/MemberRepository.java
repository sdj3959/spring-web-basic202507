package com.spring.basic.chap6.repository;

import com.spring.basic.chap3_2.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

// 데이터베이스를 관리 (CRUD)
@Repository
@Slf4j
public class MemberRepository {

    // 데이터베이스 정의
    private Map<String, Member> memberStore = new HashMap<>();

    public MemberRepository() {
        Member m1 = Member.builder()
                .account("abc1234")
                .password("12345678")
                .nickname("호돌이")
                .build();

        Member m2 = Member.builder()
                .account("def9876")
                .password("12345678")
                .nickname("핑돌이")
                .build();

        memberStore.put(m1.getAccount(), m1);
        memberStore.put(m2.getAccount(), m2);
    }

    // 개별조회하는 기능
    public void findByAccount() {
        log.info("서비스로부터 회원 개별조회를 위임받음.");
    }
}
