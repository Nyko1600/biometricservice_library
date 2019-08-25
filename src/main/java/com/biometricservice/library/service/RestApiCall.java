package com.biometricservice.library.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Thread.sleep;

public enum RestApiCall {
  INSTANCE;
  public JSONObject getMethod(String url){
    try {
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
          try {
            return new JSONObject(resp);
          }  catch (JSONException e){
            JSONObject respException = new JSONObject();
            respException.put("resp",resp);
            respException.put("exception",e);
            return respException;
          }

        }
        sleep(500);
        count--;
      }
      throw new RuntimeException(url + ": Failed HTTP error code " + response.code());
    }catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }
}
