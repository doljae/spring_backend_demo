package com.example.demo_security.security;

import com.example.demo_security.entity.Member;
import com.example.demo_security.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.demo_security.security.MemberRole.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberUserDetailServiceImpl implements MemberUserDetailService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return selectMemberUserDetailByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found!!", username)));
    }

    public Optional<MemberUserDetail> selectMemberUserDetailByUsername(String username) {
        Optional<MemberUserDetail> memberUserDetaill = getMemberUserDetails(username)
                .stream()
                .filter(memberUserDetail -> username.equals(memberUserDetail.getUsername()))
                .findFirst();
        System.out.println(memberUserDetaill);
        return memberUserDetaill;
    }


    public List<MemberUserDetail> getMemberUserDetails(String username) {
        List<Member> members = memberRepository.findByUsername(username);
        List<MemberUserDetail> memberUserDetails = new ArrayList<>();
        for (Member member : members) {
            System.out.println(member);
            MemberUserDetail memberUserDetail = new MemberUserDetail(
                    member.getUsername(),
                    passwordEncoder.encode(member.getPassword()),
                    member.getUsername().equals("seokjae") ? ADMIN.getGrantedAuthorities() : USER.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true
            );

            memberUserDetails.add(memberUserDetail);
        }
        System.out.println(memberUserDetails);
        return memberUserDetails;
    }
}
