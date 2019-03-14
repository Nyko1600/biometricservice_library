import com.biometricservice.GetUser;

public class Main {
  public static void main(String[] args) {
    GetUser getUser=new GetUser();
   boolean a= getUser.userHasConfirmed("123456");
    System.out.println(a);
  }
}
