package com.agrotis.agrotis.repositories;

import com.agrotis.agrotis.Entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends  JpaRepository<User, Long> {}