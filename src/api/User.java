package api;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class User {

	private static int UserID = 0;
	private static int UserType = 0;
	private static boolean authenticated = false;
		
	
	public static int getID() {
		return UserID;
	}
	
	public static int getType() {
		return UserType;
	}
	
	public static void logout(){
		authenticated = false;
		UserID = 0;
	}

	/**
	 * Checks a user name / password combination against a database or external system.<br>
	 * The implementing class should store the User's identifier for later use. (Persistent user) 
	 * @param user_name login name
	 * @param password login password (preferably the password has already been encrypted)
	 */
	public static void authenticate_user(String user_name, String password) {
		
		UserID = Authenticator.auth(user_name, password);
		
		if(UserID == 0) {
			UserType = 0;
			return;
		}
		
		try {
			String query = "SELECT * FROM sql24765.user WHERE id_number=" + String.valueOf(UserID);
			ResultSet response = Database.getInstance().query(query);
			
			if(!response.last()) throw new Exception("User does not exist in progree database");
			
			String type = "";
			type = response.getString("type");
			
			if(type.compareTo("teacher") == 0)
				UserType = 1;
			else if(type.compareTo("student") == 0)
				UserType = 2;
			else
				UserType = 0;
			
			authenticated = true;	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static int createUser(int id, String user_name) throws Exception {
		
		String query = "INSERT INTO sql24765.user (name, id_number, type) VALUES ('" + user_name + "', '"+ id +"', 'student')";
		Database.getInstance().execute(query);
		query = "INSERT INTO sql24765.completion (id, lesson, challenge) VALUES ('"+ id +"', 0, 0)";
		Database.getInstance().execute(query);
	
		return UserID;
	}
	
	/**
	 * Returns a associative array for a single user's progress.<br>
	 * The map will be in the format:<br>
	 * 	<pre>{@code
	 *["id" :           UserID, [int]
	 * "name" :         user name, [String]
	 * "chapter":       highest chapter completed, [int]
	 * "challenge":     highest challenge completed, [int]
	 * "avgchapter":    average chapter completed by the class, [double]
	 * "avgchallenge":  average challenge completed by the class [double]
	 *]}</pre>
	 *	To access these values use '.get("field_name")', where field_name is one of the above fields.<br>
	 *	Casting will be required for any integer or double values. eg: (int)x.get("chapter");
	 * 
	 * @param UserID a valid user id
	 * @return a {@code Map<String, String>} object. Casting will be required for integer values.
	 */
	public static Map<String, String> getUserProgress(){
		
		if(!authenticated) return null;
		
		Map<String,String> m = new HashMap<String,String>();
		
		try {			
			String query = "SELECT user.name, user.id_number, completion.lesson, completion.challenge FROM sql24765.user, sql24765.completion WHERE user.id_number = completion.id AND user.type = 'student' AND user.id_number = '"+ UserID +"'";
			ResultSet response = Database.getInstance().query(query);
					
			if(response.next() && response.getInt("id_number") == 0)
					return null;

			query = "SELECT AVG(lesson), AVG(challenge) FROM sql24765.completion";
			ResultSet avg = Database.getInstance().query(query);
			
			avg.next();
					
			double avgchpt = avg.getDouble(1);
			double avgcl = avg.getDouble(2);
					
			m.put("id",String.valueOf(response.getInt("id_number")));
			m.put("name",response.getString("name"));
			m.put("chapter", String.valueOf((int)response.getInt("lesson")));
			m.put("challenge", String.valueOf((int)response.getInt("challenge")));
			m.put("avgchapter", String.valueOf((double)avgchpt));
			m.put("avgchallenge", String.valueOf((double)avgcl));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return m;
	}
	
	/**
	 * Returns a map of associative arrays for all users' progress.<br>
	 * The array will be in the format:<br>
	 * <pre>{@code
	 * [ UserID : [ "name" :       user name, [String]
	 *             "chapter":     highest chapter completed, [int]
	 *             "challenge":   highest challenge completed, [int]
	 *           ]
	 *]}</pre>
	 * To access these values use '.get(UserID).get("field_name")', where UserID is the user's id and field_name is one of the above fields.<br>
	 * Casting will be required for any integer or double values. eg: (int)x.get("chapter");<br><br>
	 *
	 * The information at userID 0 will contain the average chapter and challenge completed by the class in place of the highest completed.<br><br>
	 * 
	 * Use in conjunction with getAllUserIDs to iterate over the map.<br>
	 * eg:
	 * <pre>{@code
	 * Map<Integer, Map<String, String>> allUsers = getAllUserProgress();
	 *Vector<Integer> userIDs = getAllUserIDs();
	 *for(int id : userIDs)
	 *     m.get(id).get("name");...etc
	 * }<br>
	 * @param UserID a valid user id
	 * @return a {@code Map<Integer ,Map<String, String>>} object. Casting will be required for integer values.
	 */
	public static Map<Integer, Map<String, String>> getAllUserProgress(){
		
		Map<Integer, Map<String,String>> m = new HashMap<Integer, Map<String,String>>();
		Map<String,String> average = new HashMap<String, String>();
		
		try {
			String query = "SELECT user.name, user.id_number, completion.lesson, completion.challenge FROM sql24765.user, sql24765.completion WHERE user.id_number = completion.id AND user.type = 'student'";
			ResultSet response = Database.getInstance().query(query);
			
			if(response.next() && response.getInt("id_number") == 0)
				return null;
			
			while(!response.isAfterLast()){
	
				Map<String,String> d = new HashMap<String, String>();
					
				d.put("name", response.getString("name"));
				d.put("chapter", String.valueOf((int)response.getInt("lesson")));			//d.put("chapter",lesson_complete)
				d.put("challenge", String.valueOf((int)response.getInt("challenge")));		//d.put("challenge", challenge_complete)
				
				m.put(response.getInt("id_number"), d);		//m.put(student_ID, Map object)
				response.next();
			}
			
			query = "SELECT AVG(lesson), AVG(challenge) FROM sql24765.completion";
			ResultSet avg = Database.getInstance().query(query);
			
			avg.next();
					
			double avgchpt = avg.getDouble(1);
			double avgcl = avg.getDouble(2);
			
			average.put("name","Class Average");
			average.put("chapter",String.valueOf((double)avgchpt));
			average.put("challenge",String.valueOf((double)avgcl));
			
			m.put(0,average);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return m;
	}
	
	
	/**
	 * Returns a Vector of all the UserIDs.<br>
	 * This vector will not include the average id (0)
	 * @return Vector of user ids.
	 */
	public static Vector<Integer> getAllUserIDs(){
		
		Vector<Integer> v = new Vector<Integer>();
		v.add(0);
		String query = "SELECT * FROM sql24765.user WHERE type = 'student'";
		try {
			ResultSet response = Database.getInstance().query(query);
			response.next();
			if(response.getInt("id_number") == 0)
				return null;
			
			while(!response.isAfterLast()){
				
				v.add(response.getInt("id_number"));
				response.next();
				
			}
			}catch (Exception e){
				e.printStackTrace();
			}
			
		return v;
	}
	
	/**
	 * Sets the chapter progress for the user with specified UserID.
	 * @param progress the new progress number
	 * @throws Exception 
	 */
	public static void setUserChapter(int progress) throws Exception{
		
		if(!authenticated) return;
		
		String query = "UPDATE sql24765.completion SET lesson=" + String.valueOf(progress) + " WHERE id=" + String.valueOf(UserID);
		boolean response = Database.getInstance().execute(query);
		if(!response)
			throw new Exception("No user with the ID " + UserID);
	}
	
	/**
	 * Sets the challenge progress for the user with specified UserID.
	 * @param progress the new progress number
	 * @throws Exception 
	 */
	public static void setUserChallenge(int progress) throws Exception{
		
		if(!authenticated) return;
		
		String query = "UPDATE sql24765.completion SET challenge=" + String.valueOf(progress) + " WHERE id=" + String.valueOf(UserID);
		boolean response = Database.getInstance().execute(query);
		if(!response)
			throw new Exception("No user with the ID " + UserID);
	}
}
