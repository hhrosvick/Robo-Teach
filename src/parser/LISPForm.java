package parser;

import java.util.ArrayList;

enum PrimType {STRING, INTEGER};

public class LISPForm {

	String rawform = null;
	String function = null;
	String rawElements = null;
	ArrayList<LISPForm> elements = null;
	
	boolean primative = false;
	PrimType type = null;
	String s_value = null;
	Integer i_value = null;
	
	boolean noparameters = false;
	
	LISPForm(String raw) {
		this.rawform = raw;

		String[] split = raw.split(" ", 2);
		
		this.function = split[0].substring(1);
		
		if(split.length != 1)
		{
			this.rawElements= split[1].substring(0, split[1].length()-1);

			elements = findForms(rawElements);
			
		} else {
			this.function = this.function.substring(0, this.function.length()-1);
			this.noparameters = true;
		}
	}
	
	LISPForm(String raw, boolean p)
	{
		this.primative = true;
		this.rawform = raw;
		this.function = raw;
		this.rawElements = raw;
		
		try{
			this.i_value = Integer.valueOf(raw);
			this.type = PrimType.INTEGER;

		} catch (NumberFormatException e) {
			this.i_value = null;
			
			if(raw.startsWith("\""))
			{
				this.s_value = raw.substring(1, raw.length()-1);
				this.type = PrimType.STRING;
			}
			else
			{
				this.primative = false;
				this.noparameters = true;
			}
		}
	}
	
	public static ArrayList<LISPForm> findForms(String raw){
		
		StringBuilder primative_string = new StringBuilder();
		
		ArrayList<LISPForm> form_list = new ArrayList<LISPForm>();
		ArrayList<LISPForm> Complete_list = new ArrayList<LISPForm>();
		
		int begin_form = 0;
		int end_form = 0;
		int count_form = 0;
		boolean within_form = false;
		
		for(int i = 0; i < raw.length(); i++)
		{
			if(!within_form)
				primative_string.append(raw.charAt(i));
			
			if(raw.charAt(i) == '(')
			{		
				count_form ++;
				if(!within_form) {
					begin_form = i;
				}
				within_form = true;
			}
			else if(raw.charAt(i) == ')' && within_form)
			{
				count_form --;
				if(count_form == 0)
				{
					end_form = i+1;
					form_list.add(new LISPForm(raw.substring(begin_form, end_form)));
					primative_string.append((form_list.size() - 1) + ")");
					begin_form = i+1;
					within_form = false;
				}
			}
		}
				
		String[] split = primative_string.toString().split(" ");
		
		for(String s : split)
		{
			if(s.startsWith("("))
				Complete_list.add(form_list.get(Integer.valueOf(s.substring(1, s.length()-1))));
			else
				Complete_list.add(new LISPForm(s, true));
		}
		
		return Complete_list;
	}
	
	
	
	public String toString(){
		
		String r = "";
		
		r += "\nFunction:\t" + this.function;
		r += "\nPrimative:\t" + this.primative;
		if(!this.primative) r += "\nNo Parameters:\t" + this.noparameters;
		if(!this.noparameters && !this.primative) r += "\nParameters:\t" + this.rawElements;
		r += "\n----------------------";
		if(this.elements != null){
			for(LISPForm f : this.elements)
			{
				r += f.toString();
			}
		}
		return r;
		
//		if(this.primative && this.type == PrimType.INTEGER) return this.i_value.toString();
//		if(this.primative && this.type == PrimType.STRING) return this.s_value;
//		if(this.noparameters) return function;
//		return  this.function + " : " + this.elements.toString();
	}
}
