package com.sportyshoes.Sportyshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sportyshoes.Sportyshoes.entity.Users;

public interface UserRepo extends JpaRepository<Users, Long> {
}
