package com.joeyabouharb.insuranceapp.infrastructure.repostories;

import java.util.Optional;

import com.joeyabouharb.insuranceapp.infrastructure.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin(origins = "https://claim-app-angular.herokuapp.com/")
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByUsernameOrEmail(String identifier, String identifier2);

   Optional<User> findByUsername(String username);

   boolean existsByUsername(String username);

   boolean existsByEmail(String email);
}
