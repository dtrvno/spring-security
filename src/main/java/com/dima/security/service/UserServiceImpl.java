package com.dima.security.service;

import com.dima.security.domain.Role;
import com.dima.security.domain.User;
import com.dima.security.repository.RoleRepo;
import com.dima.security.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {0} into database",user.getName());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {0} into database",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("adding role {0} to user {1}",roleName,username);
        User user=userRepo.findByUsername(username);
        Role role=roleRepo.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) {
        log.info("Fetching  user {1}",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all user");
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username);
        if (user==null) {
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        }
        else {
            log.error("User found in database: {0}",username);
        }
        Collection <SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),authorities);
    }
}
