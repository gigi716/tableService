package com.zerobase.tableservice.controller;

import com.zerobase.tableservice.dto.Auth;
import com.zerobase.tableservice.security.TokenProvider;
import com.zerobase.tableservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;

    private final TokenProvider tokenProvider;


    // 회원가입 API (권한이 없어도 누구나 가능)
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Auth.SignUp request){
        var result = this.memberService.register(request);
        return ResponseEntity.ok(result);
    }

    // 로그인 API (권한이 없어도 누구나 가능)
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody Auth.SignIn request){
        var member = this.memberService.authenticate(request);
        var token = this.tokenProvider.generateToken(member.getUsername(), member.getRoles());
        return ResponseEntity.ok(token);
    }
}
