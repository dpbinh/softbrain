package com.dpbinh.softbrain.repository;

import com.dpbinh.softbrain.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findOneByUsername(String username);
}
