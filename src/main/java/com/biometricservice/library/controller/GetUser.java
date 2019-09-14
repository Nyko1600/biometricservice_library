package com.biometricservice.library.controller;

import com.biometricservice.library.service.RestApiCall;
import org.json.JSONObject;

public class GetUser {

  public boolean userHasConfirmed(String userId) throws Exception {
    String uri = buildUri(userId);
    JSONObject resp = null;
    try {
      resp = RestApiCall.INSTANCE.getMethod(uri);
    } catch (Exception e) {
      throw e;
    }

    if (resp !=null && resp.getString("message").equals("user exist")) {
      return true;
    }
    return false;
  }

  private String buildUri(String userId) {
    return new String("https://webserver-domain.ddns.net:8443/fingerService-api/search?user_id"+userId);
  }
}
