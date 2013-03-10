package api;

public class Authenticator {

	public static boolean auth(String user, String pass){
		
		// TODO Implement authentication check to external system
		if(user == "test" && pass == "1234") return true;
		return false;
	}
	
}
