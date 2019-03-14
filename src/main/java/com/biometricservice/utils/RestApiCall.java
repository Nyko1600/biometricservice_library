package com.biometricservice.utils;

import okhttp3.*;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;

public enum RestApiCall {
  INSTANCE;

  RestApiCall(){
    OkHttpClient client = new OkHttpClient();
    KeyStore keyStore = readKeyStore(); //your method to obtain KeyStore
    SSLContext sslContext = SSLContext.getInstance("SSL");
    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    trustManagerFactory.init(keyStore);
    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    keyManagerFactory.init(keyStore, "keystore_pass".toCharArray());
    sslContext.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(), new SecureRandom());
    client.setSslSocketFactory(sslContext.getSocketFactory());
  }

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
