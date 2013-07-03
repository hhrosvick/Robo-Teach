package parser;

import java.lang.reflect.*;
import java.util.*;

@interface LISPFunction {
	String name() default "";
}

public class FormEvaluation {
	
	public static HashMap<String, Method> Instructions;
	public static HashMap<String, Integer> Variables;
	
	static {
		Instructions = new HashMap<String, Method>();
		
		Method[] methods = FormEvaluation.class.getMethods();
		
		for(Method method : methods)
		{
			LISPFunction func = method.getAnnotation(LISPFunction.class);
			if(func != null)
			{
				Instructions.put(func.name(), method);
			}
		}
		
	}

	public static Object evaluate(LISPForm form){
		
		if(Instructions.containsKey(form.function))
		{
			try {
				return Instructions.get(form.function).invoke(null, form);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
				
				e.printStackTrace();
				System.exit(0);
			}
		}
		
		if(form.primative)
		{
			
			
			
		}
		return null;
	}
	
	
	@LISPFunction (name="loop")
	public static Object INS_loop(LISPForm form){
		
		
		
		
		
		return null;
	}
	
}
