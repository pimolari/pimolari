package com.sdk;

public class CustomApi {
  
  private CustomConfig config = null;

  public CustomApi() {
    
  }
  
  public CustomApi(CustomConfig config) {
    this.config = config;
  }
  
  public void setSession(CustomConfig config) {
    this.config = config;
  }
  
  public CustomConfig getConfig() {
    return this.config;
  }
  
}
