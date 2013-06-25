package api;

import java.util.ArrayList;

public class LISPForm {

	String rawform = null;
	String function = null;
	String rawElements = null;
	ArrayList<LISPForm> elements = null;
	
	boolean primative = false;
	Object value = null;
	
	boolean basicfunction = false;
	
	public LISPForm(String raw) {
		this.rawform = raw;

		String[] split = raw.split(" ", 2);
		
		this.function = split[0].substring(1);
		
		if(split.length != 1)
		{
			this.rawElements= split[1].substring(0, split[1].length()-1); 
			
			elements = findForms(rawElements);
			
			if(elements.size() == 0)
			{
				String[] primElements = rawElements.split(" ");
				for(String s : primElements){
					elements.add(new LISPForm(s, true));
				}
			}
		} else {
			this.function = this.function.substring(0, this.function.length()-1);
			this.basicfunction = true;
		}
	}
	
	LISPForm(String raw, boolean p)
	{
		int newint = 0;
		String newstring = null;
		try{
			newint = Integer.valueOf(raw);
		} catch (NumberFormatException e) {
			newstring = raw;
		}
		
		if(newstring != null) 
			this.value = newstring;
		else 
			this.value = newint;
		
		this.primative = true;
		this.rawform = raw;
		this.function = raw;
		this.rawElements = raw;
	}
	
	public static ArrayList<LISPForm> findForms(String raw){
		
		
		ArrayList<LISPForm> list = new ArrayList<LISPForm>();
		
		int begin = 0;
		int end = 0;
		int count = 0;
		boolean atleastone = false;
				
		for(int i = 0; i < raw.length(); i++)
		{
			if(raw.charAt(i) == '('){
				count ++;
				if(!atleastone) begin = i;
				atleastone = true;
			}
			else if(raw.charAt(i) == ')' && atleastone)
				count --;
			
			if(count == 0 && atleastone){
				end = i+1;
				list.add(new LISPForm(raw.substring(begin, end)));
				begin = i+1;
				atleastone = false;
			}
		}
		
		return list;
	}
	
	public String toString(){
		
		if(this.primative) return this.value.toString();
		if(this.basicfunction) return function;
		return  this.function + " : " + this.elements.toString();
	}
}
