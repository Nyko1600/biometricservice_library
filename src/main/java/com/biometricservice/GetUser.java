package com.biometricservice;

import com.biometricservice.utils.RestApiCall;
import org.json.JSONObject;

public class GetUser {

  public boolean userHasConfirmed(String userId) {
    String uri = buildUri(userId);
    JSONObject resp = null;
    try {
      resp = RestApiCall.INSTANCE.getMethod(uri);
    } catch (Exception e) {
      throw e;
    }

    if (resp.getString("message").equals("user exist")) {
      return true;
    }
    return false;
  }

  private String buildUri(String userId) {
    return new String("http://192.168.1.141:8080/fingerService-api/search?user_id="+userId);
    //return new String("https://192.168.1.141:8443/fingerService-api/search?user_id=" + userId);
  }

}
