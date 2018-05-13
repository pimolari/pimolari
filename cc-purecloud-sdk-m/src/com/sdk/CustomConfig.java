package com.sdk;

public class CustomConfig {
  
  
  private String clientId = null;
  private String clientSecret = null;
  private String authCode = null;
  private String accessToken = null;
  private String username = null;
  private String name = null;
  
  
  public CustomConfig(String clientId, String clientSecret, String authCode, String accessToken) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.authCode = authCode;
    this.accessToken = accessToken;
  }
  
  public String getClientId() {
    return clientId;
  }
  public CustomConfig setClientId(String clientId) {
    this.clientId = clientId;
    return this;
  }
  public String getClientSecret() {
    return clientSecret;
  }
  public CustomConfig setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
    return this;
  }
  public String getAuthCode() {
    return authCode;
  }
  public CustomConfig setAuthCode(String authCode) {
    this.authCode = authCode;
    return this;
  }
  public String getUsername() {
    return username;
  }
  public CustomConfig setUsername(String username) {
    this.username = username;
    return this;
  }
  public String getName() {
    return name;
  }
  public CustomConfig setName(String name) {
    this.name = name;
    return this;
  }
  public String getAccessToken() {
    return accessToken;
  }
  public CustomConfig setAccessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }
  
  
}
