package com.http.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class PureCloudOauthSimpleClient {
  
  /**
   * Returns the accessToken 
   * 
   */
  public String getAccessTokenFromClientCredentialsGrant(String clientId, String clientSecret) throws SimpleClientException {
    
    Encoder encoder = Base64.getEncoder();
    String encodedAuthHeader = encoder.encodeToString((clientId + ":" + clientSecret).getBytes());
    //System.out.println(" >> " + encodedAuthHeader);
    //Decoder decoder = Base64.getDecoder();
    //System.out.println(" >> " + new String(decoder.decode(encodedAuthHeader), StandardCharsets.ISO_8859_1));
    
    CloseableHttpClient httpclient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    
    HttpPost post = new HttpPost("https://login.mypurecloud.ie/oauth/token");
    post.addHeader("Authorization", "Basic " + encodedAuthHeader);
    post.addHeader("Content-Type", "application/x-www-form-urlencoded");
    //System.out.println(" >> " + encodedAuthHeader);
    try {
      StringEntity entity = new StringEntity("grant_type=client_credentials"); 
      post.setEntity(entity);
    } catch(UnsupportedEncodingException e) {
      e.printStackTrace();
      throw new SimpleClientException("Problem calling PureCloud: " + e.getMessage());
    }
    
    Header[] headers = post.getAllHeaders();
    
    /*for (Header header : headers) {
			System.out.println("  " + header.getName() + ":" + header.getValue());
		}*/
    
    try {
      
      // Create a custom response handler
      ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
        
        @Override
        public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
          int status = response.getStatusLine().getStatusCode();
          //System.out.println("  Response retrieved with code " + status);
          HttpEntity entity = response.getEntity();
          if (status >= 200 && status < 300) {
            
            Gson gson = new Gson();
            ClientCredentialsResponse resp = gson.fromJson(EntityUtils.toString(entity), ClientCredentialsResponse.class);
            //System.out.println(" ** " + gson.toJson(resp));
            return resp.access_token;
          } else {
            //throw new ClientProtocolException("Unexpected response status: " + status);
            System.out.println("EIn");
            return entity != null ? EntityUtils.toString(entity) : null;
          }
        }
      };
      
      
      return httpclient.execute(post, responseHandler);
      
    } catch(ClientProtocolException e) {
      e.printStackTrace();
    } catch(IOException e) {
      e.printStackTrace();
    } finally {
      try {
        httpclient.close();
      } catch(IOException e) {
        
      }
      
    }
    
    return null;
    
  }
  
  /**
   * POST /oauth/token HTTP/1.1
   * Host: login.mypurecloud.com
   * 	Content-Type: application/x-www-form-urlencoded
   *Authorization: Basic BASE64(<client_id>:<client_secret>)
   */
  public String getAccessTokenFromAuthorisationCode(String code, String clientId, String clientSecret, String callbackUrl, String domain) throws SimpleClientException {
    
    String accessToken = "";
    
    Encoder encoder = Base64.getEncoder();
    String encodedAuthHeader = encoder.encodeToString((clientId + ":" + clientSecret).getBytes());
    //System.out.println(" >> " + encodedAuthHeader);
    //Decoder decoder = Base64.getDecoder();
    //System.out.println(" >> " + new String(decoder.decode(encodedAuthHeader), StandardCharsets.ISO_8859_1));
    
    CloseableHttpClient httpclient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    
    HttpPost post = new HttpPost("https://login." + domain + "/oauth/token");
    post.addHeader("Authorization", "Basic " + encodedAuthHeader);
    post.addHeader("Content-Type", "application/x-www-form-urlencoded");
    
    try {
      StringEntity entity = new StringEntity("grant_type=authorization_code&code=" + code + "&redirect_uri=" + callbackUrl);
      System.out.println("grant_type=authorization_code&code=" + code + "&redirect_uri=" + callbackUrl);
      System.out.println("--> " + entity.toString());
      
      post.setEntity(entity);
    } catch(UnsupportedEncodingException e) {
      e.printStackTrace();
      throw new SimpleClientException("Problem calling PureCloud: " + e.getMessage());
    }
    
    Header[] headers = post.getAllHeaders();
    
    /*for (Header header : headers) {
			System.out.println("  " + header.getName() + ":" + header.getValue());
		}*/
    
    try {
      
      // Create a custom response handler
      ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
        
        @Override
        public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
          int status = response.getStatusLine().getStatusCode();
          System.out.println("  Response retrieved with code " + status);
          HttpEntity entity = response.getEntity();
          if (status >= 200 && status < 300) {
            
            Gson gson = new Gson();
            ClientCredentialsResponse resp = gson.fromJson(EntityUtils.toString(entity), ClientCredentialsResponse.class);
            System.out.println(" ** " + gson.toJson(resp));
            return resp.access_token;
          } else {
            //throw new ClientProtocolException("Unexpected response status: " + status);
            return entity != null ? EntityUtils.toString(entity) : null;
          }
        }
      };
      
      
      return httpclient.execute(post, responseHandler);
      
    } catch(ClientProtocolException e) {
      e.printStackTrace();
    } catch(IOException e) {
      e.printStackTrace();
    } finally {
      try {
        httpclient.close();
      } catch(IOException e) {
        
      }
      
    }
    
    
    return accessToken;
  }
  
  public static void main(String args[]) {
    PureCloudOauthSimpleClient client = new PureCloudOauthSimpleClient();
    String clientId = "6cdc35eb-8f0e-4f08-9f3f-eb8ce7dec5ca";
    String clientSecret = "1RzCHA-dZY28Bht0Lg0EuREYOtRV8LcStINYPEO4Gxk";
    
    try {
      System.out.println("--> " + client.getAccessTokenFromClientCredentialsGrant(clientId, clientSecret));
    } catch(SimpleClientException e) {
      System.err.println("" + e.getMessage());
    }
  }
  
  
}
