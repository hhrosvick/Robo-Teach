package parser;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface LISPFunction {
	String name() default "";
}

public class FormEvaluation {
	
	public static HashMap<String, Method> Instructions = new HashMap<String, Method>();
	public static HashMap<String, Integer> Variables = new HashMap<String, Integer>();
	public static Stack<Loop> LoopStack = new Stack<Loop>();
	private static boolean initialized = false;
	private static api.AbstractRobot Robot = null;
	
	public static void initialize(api.AbstractRobot robot)
	{
		Instructions.clear();
		Variables.clear();
		LoopStack.clear();
		loop_id_count = 0;
		Robot = robot;
		
		findFunctions();
		
		initialized = true;
	}
	
	private static void findFunctions(){
		
		p("In findFunctions..");
		
		Method[] methods = FormEvaluation.class.getMethods();
		
		for(Method method : methods)
		{
			p("looping on: " + method.getName() + " :: " + method.getAnnotations().length);
			
			LISPFunction func = method.getAnnotation(LISPFunction.class);
			if(func != null)
			{
				Instructions.put(func.name(), method);
				p("Creating Instruction: " + func.name());
			}
		}
		
	}

	public static Object evaluate(LISPForm form){
		
		if(!initialized )
			return null;
		
		//p("--------\tEvaluating: " + form.rawform);
		
		
		if(Instructions.containsKey(form.function) || form.function.startsWith("irobot"))
		{
			try {
				if(form.function.startsWith("irobot")) return Instructions.get("irobot").invoke(null, form);
				return Instructions.get(form.function).invoke(null, form);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
				
				e.printStackTrace();
				System.exit(0);
			}
		}
		else
		{
			if(form.primative)
			{
				if(form.type == PrimType.INTEGER) return form.i_value;
				if(form.type == PrimType.STRING) return form.s_value;
			}
			else
			{
				if(form.noparameters)
					return getVar(form.function);
				else
					return setVar(form.function, (Integer)evaluate(form.elements.get(0)));
			}
			
		}
		return null;
	}
	
	private static Integer setVar(String name, Integer value)
	{
		p("Setting " + name + " to " + value);
		Variables.put(name, value);
		return Variables.get(name);
	}
	
	private static Integer getVar(String name)
	{
		p("Using " + name + " as " + Variables.get(name));
		return Variables.get(name);
	}
	
	private static int loop_id_count = 0;
	
	private static class Loop {
		
		private int id = 0;
		private boolean RT = false;
		ArrayList<LISPForm> forms;
		
		Loop(LISPForm form)
		{
			this.forms = form.elements;
			id = loop_id_count;
			loop_id_count++;
		}
		
		void run()
		{
			int loopcount = 0;
			
			do{
				for(LISPForm f : forms)
				{
					p(id + " - "+ loopcount);
					evaluate(f);
					if(RT) break;
				}
				p("Increase loop");
				loopcount++;
			} while(!RT);
		}
		
		void Loop_Return()
		{
			RT = true;
		}

	}
	
	@LISPFunction (name="loop")
	public static Object INS_loop(LISPForm form){
		
		LoopStack.push(new Loop(form));
		p("Starting Loop");
		LoopStack.peek().run();
		p("Loop finsihed");
		return null;
	}
	
	@LISPFunction (name="return")
	public static Object INS_return(LISPForm form){
		
		LoopStack.pop().Loop_Return();
		p("Exiting Loop");
		return null;
	}
	
	@LISPFunction (name="+")
	public static Object INS_plus(LISPForm form){
		
		Integer left = (Integer)evaluate(form.elements.get(0));
		Integer right = (Integer)evaluate(form.elements.get(1));
		p("Adding " + left +" + " + right);
		return left + right;
	}
	
	@LISPFunction (name=">")
	public static Object INS_greaterthan(LISPForm form){
		
		Integer left = (Integer)evaluate(form.elements.get(0));
		Integer right = (Integer)evaluate(form.elements.get(1));
		
		p("Checking if " + left + " > " + right);
		
		return (left > right);
	}
	
	@LISPFunction (name="when")
	public static Object INS_when(LISPForm form){
		boolean condition = false;
		try{
		condition = (boolean)evaluate(form.elements.get(0));
		} catch (NullPointerException e)
		{
			condition = false;
		}
		
		p("When condition: " + condition);
		
		if(condition)
		{
			p("When running...");
			for(LISPForm f : form.elements.subList(1, form.elements.size()))
			{
				evaluate(f);
			}
		}
		return null;
	}
	
	@LISPFunction (name="irobot")
	public static Object INS_irobot(LISPForm form){
		
		if(form.function.endsWith("error"))
			return Robot.error();
		
		StringBuilder function = new StringBuilder();
		function.append("(");
		function.append(form.function);
		
		if(form.elements != null){
			for(LISPForm f : form.elements)
			{
				function.append(" " + String.valueOf(evaluate(f)));
			}
		}
		
		function.append(")");
		
		p("iRobot function: " + function.toString());

		Robot.abclEval(function.toString(), null);
		while(!Robot.ABbreathing()){};
		
		return null;
	}

	/*
	 * TEMPLATE
	@LISPFunction (name="")
	public static Object INS_(LISPForm form){
		
		return null;
	}
	*/
	private static void p(String s){
		
		if(!LISPParser.dev) return;
		System.out.println(s);
	}
}
