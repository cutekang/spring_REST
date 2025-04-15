package com.app.restful.controller;

import com.app.restful.domain.MemberVO;
import com.app.restful.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/members/api/*")
@RequiredArgsConstructor
public class MemberAPI {

    private final MemberService memberService;

//    url 파라미터 : 모든 컨트롤러에서 사용이 가능하지만 보통 rest 에서 사용된다.
    @GetMapping("member/{id}")
    public MemberVO getMember(@PathVariable Long id) {
        Optional<MemberVO> foundMember = memberService.getMemberInfo(id);
        if (foundMember.isPresent()) {
            return foundMember.get();
        }
//        잘못 전달 시 빈객체로 전달한다.
//        exception 보다는 null을 보내서 값을 잘못 전달하도록 처리한다.
//        그래서 대부분 Optional로 안보낼 때가 많지만 상세하게 전달할 때에는 Optional로 전달한다.

        return new MemberVO();
    }
}
