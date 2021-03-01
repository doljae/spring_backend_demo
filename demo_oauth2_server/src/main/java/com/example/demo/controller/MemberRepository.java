package com.example.demo.controller;

import com.example.demo.vo.Member;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MemberRepository extends PagingAndSortingRepository <Member, Long> {
}
