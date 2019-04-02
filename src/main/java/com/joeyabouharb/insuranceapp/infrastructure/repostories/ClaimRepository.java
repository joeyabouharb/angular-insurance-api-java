package com.joeyabouharb.insuranceapp.infrastructure.repostories;

import java.util.List;

import com.joeyabouharb.insuranceapp.infrastructure.models.Claim;
import com.joeyabouharb.insuranceapp.infrastructure.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin(origins = "https://claim-app-angular.herokuapp.com/")
public interface ClaimRepository extends JpaRepository<Claim, Long> {

 List<Claim> findByUser(User user);
}