package com.wipro.productservice.repository;

import com.wipro.productservice.entity.ERole;
import com.wipro.productservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
