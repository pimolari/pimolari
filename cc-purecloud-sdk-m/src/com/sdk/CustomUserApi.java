package com.sdk;

import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;

import com.mypurecloud.sdk.v2.ApiClient;
import com.mypurecloud.sdk.v2.ApiException;
import com.mypurecloud.sdk.v2.Configuration;
import com.mypurecloud.sdk.v2.api.UsersApi;
import com.mypurecloud.sdk.v2.api.request.GetUsersMeRequest;
import com.mypurecloud.sdk.v2.model.UserMe;
import com.sdk.exception.InvalidTokenException;

public class CustomUserApi extends CustomApi {
  
  private static Logger logger = Logger.getLogger(CustomUserApi.class.getName());
  
  public CustomUserApi(CustomConfig config) {
    super(config);
  }
  
  public UserMe getUser() throws InvalidTokenException {
    
    // Create ApiClient instance
    ApiClient apiClient = ApiClient.Builder.standard()
                                           .withAccessToken(getConfig().getAccessToken())
                                           .withBasePath("https://api.mypurecloud.ie")
                                           .build();
    
    Configuration.setDefaultApiClient(apiClient);
    
    UserMe me = null;
    
    try {
      
      UsersApi usersApi = new UsersApi();
      GetUsersMeRequest request = GetUsersMeRequest.builder().withExpand(Collections.singletonList("presence")).build();
      me = usersApi.getUsersMe(request);
      System.out.println("Hello " + me.getName());
      logger.info("User info retrieved for " + me.getName());
      
      
    } catch (ApiException e) {
      //System.err.println("Exception when calling NotificationsApi#getAvailabletopics");
      e.printStackTrace();
      throw new InvalidTokenException("Invalid Token " + getConfig().getAccessToken() + " :: " + e.getMessage());
    } catch(IOException e) {
      e.printStackTrace();
    }
    
    return me;
  }
}
