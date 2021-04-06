package com.example.demo_security.repository;

import com.example.demo_security.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public List<Member> findByUsername(String username);

}
