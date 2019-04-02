package com.joeyabouharb.insuranceapp.infrastructure.controllers;

import java.util.Collection;
import java.util.stream.Collectors;

import com.joeyabouharb.insuranceapp.infrastructure.models.Claim;
import com.joeyabouharb.insuranceapp.infrastructure.models.User;
import com.joeyabouharb.insuranceapp.infrastructure.repostories.ClaimRepository;
import com.joeyabouharb.insuranceapp.infrastructure.repostories.UserRepository;
import com.joeyabouharb.insuranceapp.security.CurrentUser;
import com.joeyabouharb.insuranceapp.security.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping(value = "/api/claim")
@CrossOrigin(origins = "https://claim-app-angular.herokuapp.com/")
class ClaimController{

  @Autowired
  private ClaimRepository claimRepository;

  @Autowired
  private UserRepository userRepository;

 
  @GetMapping(value="/list")
  public Collection<Claim> getClaims(@CurrentUser UserPrincipal currentUser) {

    User user = userRepository
    .findByUsername(currentUser.getUsername())
    .orElseThrow(() -> new ResourceNotFoundException());

      return claimRepository.findByUser(user).stream()
      .collect(Collectors.toList());
  }

  
  @PostMapping(value="/add")
  public Claim newClaim(
    @CurrentUser UserPrincipal currentUser,
    @RequestBody Claim claim) {

      User user = userRepository
      .findByUsername(currentUser.getUsername())
      .orElseThrow(() -> new ResourceNotFoundException());

      claim.setUser(user);
      claim.setStatus("In Progress");
      return claimRepository.save(claim);
  }
  

}