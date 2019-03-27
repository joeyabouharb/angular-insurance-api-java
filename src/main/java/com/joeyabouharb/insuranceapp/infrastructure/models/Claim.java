package com.joeyabouharb.insuranceapp.infrastructure.models;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;


import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor

public class Claim {

  @Id
  @GeneratedValue
  private Long id;
  private @NonNull String claim;
  private @NonNull String policy;
  private @NonNull String details;
  private @NonNull String incidentDate;
  private @NonNull String status;
  @ManyToOne
  @JsonIgnore
  private @NonNull User user;

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
   * @return the claim
   */
  public String getClaim() {
    return claim;
  }

  /**
   * @param claim the claim to set
   */
  public void setClaim(String claim) {
    this.claim = claim;
  }

  /**
   * @return the policy
   */
  public String getPolicy() {
    return policy;
  }

  /**
   * @param policy the policy to set
   */
  public void setPolicy(String policy) {
    this.policy = policy;
  }

  /**
   * @return the details
   */
  public String getDetails() {
    return details;
  }

  /**
   * @param details the details to set
   */
  public void setDetails(String details) {
    this.details = details;
  }

  /**
   * @return the incidentDate
   */
  public String getIncidentDate() {
    return incidentDate;
  }

  /**
   * @param incidentDate the incidentDate to set
   */
  public void setIncidentDate(String incidentDate) {
    this.incidentDate = incidentDate;
  }

  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

}