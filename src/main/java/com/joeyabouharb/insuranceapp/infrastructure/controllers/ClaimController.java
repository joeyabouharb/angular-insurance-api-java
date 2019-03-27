package com.joeyabouharb.insuranceapp.infrastructure.controllers;

import java.util.Collection;
import java.util.stream.Collectors;

import com.joeyabouharb.insuranceapp.infrastructure.models.Claim;
import com.joeyabouharb.insuranceapp.infrastructure.repostories.ClaimRepository;
import com.joeyabouharb.insuranceapp.security.CurrentUser;
import com.joeyabouharb.insuranceapp.security.UserPrincipal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping(value = "/api/claim")
@CrossOrigin(origins = "http://localhost:4200/")
class ClaimController{
  private ClaimRepository claimRepository;
  
  public ClaimController(ClaimRepository claimRepository){
    this.claimRepository = claimRepository;
  }

  @GetMapping(value="/list")
  public Collection<Claim> getClaims() {

      return claimRepository.findAll().stream()
      .collect(Collectors.toList());
  }

  @PostMapping(value="/add")
  public Claim newClaim(@RequestBody Claim claim) {

      System.out.println(claim.getIncidentDate());
      claim.setStatus("In Progress");
      return claimRepository.save(claim);
  }
  
  
}