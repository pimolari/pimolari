package com.sdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.mypurecloud.sdk.v2.ApiClient;
import com.mypurecloud.sdk.v2.ApiException;
import com.mypurecloud.sdk.v2.Configuration;
import com.mypurecloud.sdk.v2.api.NotificationsApi;
import com.mypurecloud.sdk.v2.model.AvailableTopic;
import com.mypurecloud.sdk.v2.model.AvailableTopicEntityListing;
import com.mypurecloud.sdk.v2.model.Channel;
import com.mypurecloud.sdk.v2.model.ChannelTopic;
import com.sdk.exception.CustomSdkException;
import com.sdk.exception.InvalidTokenException;

public class CustomNotificationApi extends CustomApi {
  
  private static Logger logger = Logger.getLogger(CustomNotificationApi.class.getName());
  
  public CustomNotificationApi(CustomConfig config) {
    super(config);
  }
  
  public List<AvailableTopic> getAvailableTopics() throws InvalidTokenException {
    
    NotificationsApi apiInstance = new NotificationsApi();
    
 // Create ApiClient instance
    ApiClient apiClient = ApiClient.Builder.standard()
                                           .withAccessToken(getConfig().getAccessToken())
                                           .withBasePath("https://api.mypurecloud.ie")
                                           .build();
    
    Configuration.setDefaultApiClient(apiClient);
    
    //Configuration.getDefaultApiClient().setAccessToken(getConfig().getAccessToken());
    //Configuration.getDefaultApiClient().
    //Configuration.getDefaultApiClient().setBasePath("https://api.mypurecloud.ie");
    
    try {
      
      List<String> expand = Arrays.asList("expand_example"); // List<String> | Which fields, if any, to expand
      AvailableTopicEntityListing topics = null; // = apiInstance.getAvailabletopics(expand);
      System.out.println(topics);
      
      return topics.getEntities();
    
    } catch(Exception e) {
    }
  
    return null;
      //String topic = "v2.users." + user.getId() + ".presence";
      
    //} catch (ApiException e) {
    //  e.printStackTrace();
    //  throw new InvalidTokenException("Invalid Token " + getConfig().getAccessToken() + " :: " + e.getMessage());
    //}
  }
  
  /*public String subscribeTopics(List<String> topics) throws CustomSdkException, InvalidTokenException {
    
    if (topics == null || topics.size() <= 0) {
      throw new CustomSdkException("No topics provided");
    } 
    
    NotificationsApi notificacionApi = new NotificationsApi();
    Configuration.getDefaultApiClient().setAccessToken(getConfig().getAccessToken());
    //Configuration.getDefaultApiClient().setBasePath("https://api.mypurecloud.ie");
    
    
    List<ChannelTopic> topicList = new ArrayList<ChannelTopic>();
    for (String t : topics) {
      topicList.add(new ChannelTopic().id(t));
    }
    
    try {
      
      //Channel channel = notificacionApi.postChannels();
      //notificacionApi.postChannelsChannelIdSubscriptions(channel.getId(), topicList);
      
      //return channel.getConnectUri();
      
      //String topic = "v2.users." + user.getId() + ".presence";
      
    } catch (ApiException e) {
      e.printStackTrace();
      throw new InvalidTokenException("Invalid Token " + getConfig().getAccessToken() + " :: " + e.getMessage());
    }
  }*/
}
