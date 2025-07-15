package com.spring.basic.chap3_2.controller;

import com.spring.basic.chap3_2.entity.Member;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v3-2/members")
public class MemberController3_2 {

    private Map<String, Member> memberStore = new HashMap<>();

    public MemberController3_2() {

        Member member1 = Member.builder()
                .account("abc1234")
                .password("9999")
                .nickname("뽀롱이")
                .build();

        Member member2 = Member.builder()
                .account("def9876")
                .password("5577")
                .nickname("핑순이")
                .build();

        memberStore.put(member1.getUid(), member1);
        memberStore.put(member2.getUid(), member2);

    }
}
