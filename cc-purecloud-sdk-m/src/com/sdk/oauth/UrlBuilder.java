package com.sdk.oauth;

import com.sdk.config.SdkConfig;

public class UrlBuilder {
  
  /**
   * Returns a normalised Purecloud login url for the Auth Code Grant Type
   * https://login.mypurecloud.ie/oauth/authorize
   * ?client_id=5708f173-e3b1-4fea-8f46-28ed7558150f
   * &response_type=code
   * &redirect_uri=http://localhost:8080/oauth/code/callback/
   * @param String clientId
   * @param String refirectUrl The callback url with the format http://example.com/oauth/callback?code=my-authorization-code
   * @return String the login url to redirect the user to
   */
  public static String authGrantLoginUrl (String clientId, String redirectUrl) {
    return loginUrl(SdkConfig.loginUrl, clientId, SdkConfig.authCodeGrantType, redirectUrl);
  }
  
  /***/
  public static String implicitGrantLoginUrl (String clientId, String redirectUrl) {
    return loginUrl(SdkConfig.loginUrl, clientId, SdkConfig.implicitGrantType, redirectUrl);
  }

  /**
   * Returns a normalised Purecloud login url 
   * Expects all possible params to be especified for a valid url
   * https://login.mypurecloud.ie/oauth/authorize
   * ?client_id=[]
   * &response_type=[code|token]
   * &redirect_uri=[]
   * */
  public static String loginUrl(String baseUri, String clientId, String responseType, String redirectUrl) {
    
    return baseUri + "?" + SdkConfig.clientIdParam + "=" + clientId 
                   + "&" + SdkConfig.responseTypeParam + "=" + responseType 
                   + "&" + SdkConfig.redirectUrlParam + "=" + redirectUrl;
  }
}
