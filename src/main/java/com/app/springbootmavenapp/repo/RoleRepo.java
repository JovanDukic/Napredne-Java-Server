package com.app.springbootmavenapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.springbootmavenapp.model.UserRole;

public interface RoleRepo extends JpaRepository<UserRole, Long> {

	Optional<UserRole> findByRoleName(String roleName);

}
