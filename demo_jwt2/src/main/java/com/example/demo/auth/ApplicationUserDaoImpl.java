package com.example.demo.auth;

import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.UserVo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.ADMIN;
import static com.example.demo.security.ApplicationUserRole.USER;

@Repository
@Qualifier("User")
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public ApplicationUserDaoImpl(PasswordEncoder passwordEncoder, UserMapper mapper) {
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<UserVo> users = mapper.getUsers();
        List<ApplicationUser> applicationUsers = Lists.newArrayList();
        for (UserVo user : users) {
            //System.out.println(user);
            ApplicationUser applicationUser = new ApplicationUser(
                    user.getUserid(),
                    passwordEncoder.encode("password"),
                    user.getUserid().equals("dooly") ? ADMIN.getGrantedAuthorities() : USER.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true);
            //System.out.println(applicationUser);
            applicationUsers.add(applicationUser);
        }
        return applicationUsers;
    }
}
