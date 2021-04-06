package com.example.demo.jwt.refreshtoken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenVoRepository extends JpaRepository<RefreshTokenVo, Long> {

    public RefreshTokenVo findByUserid(String userid);

    public int deleteByUserid(String userid);
}
