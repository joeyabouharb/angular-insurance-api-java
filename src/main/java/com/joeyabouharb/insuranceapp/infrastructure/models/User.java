package com.joeyabouharb.insuranceapp.infrastructure.models;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;

import java.util.Set;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor

public class User {

  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private String role;
  private String email;
  private @NonNull String password;

  @OneToMany
  @JsonIgnore
  private Set<Claim> claims;

  public User(String username, String email, String password, String role) {
    this.role = role;
    this.username = username;
    this.email = email;
    this.password = password;
}

public User(){
  
}
  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the claims
   */
  public Set<Claim> getClaims() {
    return claims;
  }

  /**
   * @param claims the claims to set
   */
  public void setClaims(Set<Claim> claims) {
    this.claims = claims;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the role
   */
  public String getRole() {
    return role;
  }

  /**
   * @param role the role to set
   */
  public void setRole(String role) {
    this.role = role;
  }
}