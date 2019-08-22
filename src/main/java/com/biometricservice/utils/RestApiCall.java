package com.biometricservice.utils;

import okhttp3.*;
import org.apache.axis.AxisProperties;
import org.json.JSONObject;
import javax.net.ssl.SSLSocketFactory;
import com.biometricservice.utils.AxisSSLSocketFactory;

import static java.lang.Thread.sleep;

public enum RestApiCall {
  INSTANCE;
  public JSONObject getMethod(String url){
    try {
//      com.biometricservice.utils.AxisSSLSocketFactory.setKeystorePassword("123456");
//      com.biometricservice.utils.AxisSSLSocketFactory.setResourcePathToKeystore("utilities/keystore.jks");
//      AxisProperties.setProperty("axis.socketSecureFactory", "utilities.AxisSSLSocketFactory");

      OkHttpClient client = new OkHttpClient();
      Response response=null;

      int count  =10;
      while (count>0){
        Request request = new Request.Builder()
            .get()
            .url(url)
            .addHeader("Accept", "application/json")
            .build();

        response = client.newCall(request).execute();
        if (response.code() == 200) {
          String resp = response.body().string();
          return new JSONObject(resp);
        }
          sleep(500);
          count--;
      }
      throw new RuntimeException(url + ": Failed HTTP error code " + response.code());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

}
