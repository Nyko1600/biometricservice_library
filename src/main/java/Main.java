import com.biometricservice.GetUser;

public class Main {
  public boolean validateAuth(String userId) {
    GetUser getUser=new GetUser();
   boolean answer= getUser.userHasConfirmed(userId);
    System.out.println(answer);
  return answer;
  }
}
