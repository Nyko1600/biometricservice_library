
import com.biometricservice.library.service.RestApiCall;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RestApiCallTest {

  @Test
  public void testPingOk() {
    String uri="https://test4.ddns.net:8443/fingerService-api/ping";
    JSONObject resp = RestApiCall.INSTANCE.getMethod(uri);
    assertEquals(resp.getString("resp"),"pong");
  }

  @Test
  public void testUserNotFound() {
    String uri ="https://test4.ddns.net:8443/fingerService-api/search?user_id=123456";
    JSONObject resp = RestApiCall.INSTANCE.getMethod(uri);
    assertEquals(resp,null);
  }
}
