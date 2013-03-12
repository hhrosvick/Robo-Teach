package api;

public class Authenticator {

	public static int auth(String user, String pass){
		
		// TODO Implement authentication check to external system
		if(user == "test" && pass == "1234") return 1;
		return 0;
	}
	
}
