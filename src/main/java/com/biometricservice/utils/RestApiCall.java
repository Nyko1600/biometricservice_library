package com.biometricservice.utils;

import okhttp3.*;
import org.json.JSONObject;
import javax.net.ssl.SSLSocketFactory;

public enum RestApiCall {
  INSTANCE;
  public JSONObject getMethod(String url){
    try {
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder()
          .get()
          .url(url)
          .addHeader("Accept", "application/json")
          .build();

      Response response = client.newCall(request).execute();
      if (response.code() == 404) {
        throw new RuntimeException(url + ": not found");
      }

      if (response.code() != 200) {
        throw new RuntimeException(url + ": Failed HTTP error code " + response.code());
      }
      String resp = response.body().string();

      return new JSONObject(resp);

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

}
