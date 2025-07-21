package com.spring.basic.chap6.controller;


import com.spring.basic.chap5_3.dto.request.MemberCreateDto;
import com.spring.basic.chap5_4.dto.response.MemberListResponse;
import com.spring.basic.chap6.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v6/members")
@Slf4j
@RequiredArgsConstructor // final 필드만 골라서 초기화
public class MemberApiControllerV6 {

    // 서비스에게 의존
    private final MemberService memberService;

    // 회원 단일 조회
    @GetMapping("/{account}")
    public ResponseEntity<?> findMember(@PathVariable String account) {

        log.info("회원 단일 조회 요청이 들어옴!");
        log.debug("parameter - account : {}", account);

        // 1. 회원 계정명을 통해 데이터베이스에서 회원정보를 조회해야 함.
        // 2. 예외처리: 계정명이 10글자 미만인지 확인
        //              회원이 존재하지 않을 가능성도 처리
        // 3. 데이터베이스에서 가져온 회원정보를 그대로 응답하면 X - 정제가 필요
        //  ====> 서비스에게 위임
//        try {
//            MemberListResponse responseMember = memberService.findOneMember(account);
//            return ResponseEntity.ok(responseMember);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }

        MemberListResponse responseMember = memberService.findOneMember(account);
        return ResponseEntity.ok(responseMember);

    }

    // 회원 생성
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid MemberCreateDto dto, BindingResult result) {

//        if (result.hasErrors()) {
//            String defaultMessage = result.getFieldError().getDefaultMessage();
//            return ResponseEntity.badRequest().body(ErrorResponse.builder()
//                    .message(defaultMessage)
//                    .error(result.getFieldError().getDefaultMessage())
//                    .build()
//            );
//        }

        // 서비스에게 위임
        MemberListResponse response = memberService.createMember(dto);

        return ResponseEntity.ok(response);

    }
}
