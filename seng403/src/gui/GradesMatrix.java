package gui;

import java.util.Map;
import java.util.Vector;
import api.API;
import api.API_Interface;

public class GradesMatrix 
{
	public static GradesMatrix instance = null;
	
	private String[][] data;
	private String[][] d2;
	
	private String[][] sortedLessons;
	private boolean sLessons = false;
	private String[][] sortedChallenges;
	private boolean sChallenges = false;
	private String[][] sortedName;
	private boolean sName = false;
	private String[][] sortedID;
	private boolean sID = false;
	
	public GradesMatrix()
	{
		GradesMatrix.instance = this;
		// Commented out to correct timing.
		// data = refreshMatrix();
	}
	public String[][] getMatrix()
	{
		
		return data;
		
	}
	public String[][] refreshMatrix()
	{
		
		if(RoboTeach.getAPI_Interface().getUserType(RoboTeach.getUserID()) == 1)
			createClassMatrix();
		else
			createStudentMatrix();
		
		sLessons = sChallenges = sName = sID = false;
		return data;
		
	}
	
	private void createStudentMatrix()
	{
		data = new String[2][4];
		System.out.println(RoboTeach.getUserID());
		Map<String, String> userData = RoboTeach.getAPI_Interface().getUserProgress(RoboTeach.getUserID());
		data[0][0] = ("0");
		data[0][1] = ("Class Average");
		data[0][2] = userData.get("avgchapter");
		data[0][3] = userData.get("avgchallenge");
		
		data[1][0] = userData.get("id");
		data[1][1] = userData.get("name");
		data[1][2] = userData.get("chapter");
		data[1][3] = userData.get("challenge");
	
	}
	
	private void createClassMatrix() 
	{
		Map<Integer, Map<String, String>> progress = RoboTeach.getAPI_Interface().getAllUserProgress();
		
		Vector<Integer> userData = RoboTeach.getAPI_Interface().getAllUserIDs();
		Object[] users = userData.toArray();
		
		Object name = "name";
		Object chapter = "chapter";
		Object challenge = "challenge";
		data = new String[users.length][4];
	
		for(int i = 0; i < users.length; i++)
		{
			data[i][0] = users[i].toString();
			System.out.println(users[i].toString());
			data[i][1] = progress.get(users[i]).remove(name);
			data[i][2] = progress.get(users[i]).remove(chapter);
			data[i][3] = progress.get(users[i]).remove(challenge);
		}
	}
	public String[][] updateMatrix(String s) 
	{
		int matches = 0;
		int index = 0;
		for(int i = 0; i < this.data.length; i++)
			if((this.data[i][0].contains(s)) || (this.data[i][1].toLowerCase()).contains(s.toLowerCase()) || (this.data[i][2].contains(s)) || (this.data[i][3].contains(s)))
				matches++;
		this.d2 = new String[matches][4];
		for(int i = 0; i < this.data.length; i++)
		{
			if((this.data[i][0].contains(s)) || (this.data[i][1].toLowerCase()).contains(s.toLowerCase()) || (this.data[i][2].contains(s)) || (this.data[i][3].contains(s)))
			{
				for(int j = 0; j < 4; j++)
				{
					this.d2[index][j] = this.data[i][j];
				}
				index++;
			}
		}
		return d2;
	}
	public String[][] SortLessons()
	{
		if(!sLessons)
		{
			sortedLessons = new String[data.length][4];
			for(int i = 0; i < data.length; i++)
				for(int j = 0; j < 4; j++)
					sortedLessons[i][j] = data[i][j];
			for(int i = 0; i < sortedLessons.length; i++)
			{
				for(int j = 1; j < sortedLessons.length-i; j++)
				{
					if(Float.parseFloat(sortedLessons[j-1][2]) < Float.parseFloat(sortedLessons[j][2]))
					{
						String[] temp = sortedLessons[j-1];
						sortedLessons[j-1] = sortedLessons[j];
						sortedLessons[j] = temp;
					}
				}
			}
			sLessons = true;
		}
		return sortedLessons;
	}
	public String[][] SortChallenges()
	{
		if(!sChallenges)
		{
			sortedChallenges = new String[data.length][4];
			for(int i = 0; i < data.length; i++)
				for(int j = 0; j < 4; j++)
					sortedChallenges[i][j] = data[i][j];
			for(int i = 0; i < sortedChallenges.length; i++)
			{
				for(int j = 1; j < sortedChallenges.length-i; j++)
				{
					if(Float.parseFloat(sortedChallenges[j-1][3]) < Float.parseFloat(sortedChallenges[j][3]))
					{
						String[] temp = sortedChallenges[j-1];
						sortedChallenges[j-1] = sortedChallenges[j];
						sortedChallenges[j] = temp;
					}
				}
			}
			sChallenges = true;
		}
		return sortedChallenges;
	}
	public String[][] SortName()
	{
		if(!sName)
		{
			sortedName = new String[data.length][4];
			for(int i = 0; i < data.length; i++)
				for(int j = 0; j < 4; j++)
					sortedName[i][j] = data[i][j];
			for(int i = 0; i < sortedName.length; i++)
			{
				for(int j = 1; j < sortedName.length-i; j++)
				{
					if((sortedName[j-1][1]).compareTo(sortedName[j][1]) > 0)
					{
						String[] temp = sortedName[j-1];
						sortedName[j-1] = sortedName[j];
						sortedName[j] = temp;
					}
				}
			}
			sName = true;
		}
		return sortedName;
	}
	public String[][] SortID()
	{
		if(!sID)
		{
			sortedID = new String[data.length][4];
			for(int i = 0; i < data.length; i++)
				for(int j = 0; j < 4; j++)
					sortedID[i][j] = data[i][j];
			for(int i = 0; i < sortedID.length; i++)
			{
				for(int j = 1; j < sortedID.length-i; j++)
				{
					if((sortedID[j-1][0]).compareTo(sortedID[j][0]) > 0)
					{
						String[] temp = sortedID[j-1];
						sortedID[j-1] = sortedID[j];
						sortedID[j] = temp;
					}
				}
			}
			sID = true;
		}
		return sortedID;
	}
}