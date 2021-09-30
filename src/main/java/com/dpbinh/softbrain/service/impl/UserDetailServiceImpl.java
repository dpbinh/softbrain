package com.dpbinh.softbrain.service.impl;

import com.dpbinh.softbrain.entity.AppUser;
import com.dpbinh.softbrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userOpt = userRepository.findOneByUsername(username);

        if(userOpt.isPresent()) {
            return new User(userOpt.get().getUsername(),
                    userOpt.get().getPassword(), Collections.emptyList());
        }
        return null;
    }
}
