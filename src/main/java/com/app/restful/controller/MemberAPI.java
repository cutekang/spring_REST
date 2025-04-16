package com.app.restful.controller;

import com.app.restful.domain.MemberVO;
import com.app.restful.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/members/api/*")
@RequiredArgsConstructor
public class MemberAPI {

    private final MemberService memberService;

    private final HttpSession session;

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
    
//    회원 가입
    @Operation(summary = "회원 가입", description = "회원가입을 위한 API")
    @Parameter(
            name = "memberVO",
            description = "회원 정보",
            schema = @Schema(type = "Object"),
            required = true
    )
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    @PostMapping("join")
    public void join(@RequestBody MemberVO memberVO) {
        memberService.join(memberVO);
    }

//    회원정보 수정
    @Operation(summary = "회원정보 수정", description = "회원정보 수정을 위한 API")
    @Parameter(
            name = "memberVO",
            description = "회원 정보",
            schema = @Schema(type = "MemberVO"),
            required = true
    )
    @PutMapping("modify")
    public void update(@RequestBody MemberVO memberVO) {
        memberService.modify(memberVO);
    }
    
//    회원탈퇴
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴를 위한 API")
    @ApiResponse(responseCode = "200", description = "회원탈퇴 성공")
    @DeleteMapping("withdraw/{id}")
    public void delete(@PathVariable Long id) {
//        Long id = (Long)session.getAttribute("loginId");
        memberService.withdraw(id);
    }
}
