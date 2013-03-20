package api;

public class Authenticator {

	public static int auth(String user, String pass){
		
		// TODO ITER 3: Implement authentication check to external system
		if(user.equals("test") && pass.equals("1234")) return 1;
		return 0;
	}
	
}