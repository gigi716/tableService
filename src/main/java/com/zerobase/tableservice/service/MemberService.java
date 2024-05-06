package com.zerobase.tableservice.service;

import com.zerobase.tableservice.dto.Auth;
import com.zerobase.tableservice.entity.MemberEntity;
import com.zerobase.tableservice.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return this.memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find user ->" + username));
    }

    //회원 가입
    public MemberEntity register(Auth.SignUp member) {
        // 중복 아이디 체크
        boolean exists = this.memberRepository.existsByUsername(member.getUsername());
        if (exists) {
            throw new RuntimeException("이미 사용 중인 아이디 입니다.");
        }

        // DB에 저장할때 비밀번호 인코딩
        member.setPassword(this.passwordEncoder.encode(member.getPassword()));
        // 객체로 반환하여 바로 저장
        MemberEntity save = this.memberRepository.save(member.toEntity());


        return save;
    }


    // 로그인
    public MemberEntity authenticate(Auth.SignIn member) {
        // ID 확인
       var user = this.memberRepository.findByUsername(member.getUsername())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID 입니다."));

       // 비밀번호 확인
       if(!this.passwordEncoder.matches(member.getPassword(), user.getPassword())){
           throw new RuntimeException("비밀번호가 일치하지 않습니다.");
       }

        return user;
    }

}
