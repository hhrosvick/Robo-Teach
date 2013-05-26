package gui;

import java.io.File;
import javax.swing.ImageIcon;

public class Images {

	/**
	 * Returns an ImageIcon object for the specified chapter and lesson.
	 * @param Chapter The number of the chapter needed. Pass 0 (zero) into all other parameters if just the chapter imaged is needed.
	 * @param Lesson The number of the lesson needed. Pass 0 (zero) into the slide parameter if just the chapter/lesson imaged is needed.
	 * @param Slide The number of the slide needed. 
	 * @return the ImageIcon object
	 */
	public static ImageIcon getLesson(int Chapter, int Lesson, int Slide){
				
		String imgStr = "images/Lessons/";
		
		if(Chapter == 0)
			return new ImageIcon("images/Usermanual/UM " + Slide + ".png");
		
		if(Lesson == 0)
			imgStr += "Chapter " + Chapter;
		else if(Slide == 0)
			imgStr += "Lesson " + Chapter + "-" + Lesson;
		else
			imgStr += "LessonSlides/Lesson "+ Chapter + "-" + Lesson + "-" + Slide;
		
		imgStr += ".png";
		
		File image = new File(imgStr);
		
		if(image.exists())
			return new ImageIcon(imgStr);
		else
			return null;
	}

	/**
	 * Returns an ImageIcon object for the user manual.
	 * @param Slide The number of the slide needed.
	 * @return the ImageIcon object
	 */
	public static ImageIcon getUserManual(int Slide)
	{
		String imgStr = "images/UserManual/";
		
		
		if(Slide == 0)
			return null;
		else
			imgStr += "UserManual " + Slide;
		
		imgStr += ".png";

		File image = new File(imgStr);
		
		if(image.exists())
			return new ImageIcon(imgStr);
		else
			return null;		
	}

	/**
	 * Returns an ImageIcon object for the specified challenge.
	 * @param Tier The number of the Tier needed. Pass 0 (zero) or false into all other parameters if just the tier imaged is needed.
	 * @param Number The challenge number needed. Pass false into the slide parameter if just the Tier/Number imaged is needed.
	 * @param Slide true is the slide should be returned, false otherwise.
	 * @return the ImageIcon object
	 */
	public static ImageIcon getChallenge(int tier, int number, boolean Slide){

		String imgStr = "images/Challenges/";
		
		if(tier == 0)
			return null;
		
		if(number == 0)
			imgStr += "Tier " + tier;
		else if(!Slide)
			imgStr += "Challenge " + tier + "-" + number;
		else
			imgStr += "ChallengeSlides/Challenge " + tier + "-" + number;
		
		imgStr += ".png";

		File image = new File(imgStr);
		
		if(image.exists())
			return new ImageIcon(imgStr);
		else
			return null;
	}	
}
