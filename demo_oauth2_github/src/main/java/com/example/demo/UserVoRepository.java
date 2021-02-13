package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVoRepository extends JpaRepository<UserVo, Long> {

    public UserVo findByGithub(String github);
}
