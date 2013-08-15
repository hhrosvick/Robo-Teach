package api;

public class Authenticator {

	public static int auth(String user, String pass){
		
		if(user == null || pass == null) return 0;
		
		if(user.equals("bob") && pass.equals("1234")) return 1234;
		if(user.equals("teach") && pass.equals("1234")) return 999;
		
		return 0;
	}
	
}