package com.joeyabouharb.insuranceapp.infrastructure.payloads;

public class LoginForm {
  private String usernameOrEmail;
  private String password;

  /**
   * @return the usernameOrPassword
   */
  public String getUsernameOrEmail() {
    return usernameOrEmail;
  }

  /**
   * @param usernameOrPassword the usernameOrPassword to set
   */
  public void setUsernameOrEmail(String usernameOrEmail) {
    this.usernameOrEmail = usernameOrEmail;
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

}