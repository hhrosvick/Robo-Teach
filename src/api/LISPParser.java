package api;

import java.io.*;
import java.nio.*;
import java.util.*;

public class LISPParser {

	private static boolean dev = true;
	
	private String content = "";
	private ArrayList<LISPForm> rootForms;
	
	public static void main(String[] args) {
		
		LISPParser.parse("./robotemp.lisp");

	}
	
	private static void parse(String filePath) {
		
		LISPParser lp = new LISPParser();
		
		p("Parsing file at " + filePath);
		
		lp.readFile(filePath);
		lp.consumeWhitespace();
		p(lp.content);
		p("Tokenizing...");
		lp.generateTokens();
		p(lp.rootForms.toString());
		
	}

	private void readFile(String filePath)
	{
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			
			StringBuilder sb = new StringBuilder();
			
			String temp = "";
			while((temp = br.readLine()) != null){
				sb.append(temp + "\n");
			}

			content = sb.toString().trim();
			
			br.close();
			
		} catch (IOException e) {
			if(!dev) System.exit(0);
			e.printStackTrace();
		}
	}
	
	

	private void generateTokens() {

		rootForms = LISPForm.findForms(content);
		
	}
	
	
	private void consumeWhitespace() {

		boolean first_space = true;
		boolean within_quotes = false;
		boolean replace = false;
		boolean last_was_brace = false;
		boolean append = true;
		
		char replaceChar = ' ';
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < content.length(); i++)
		{

			char C = content.charAt(i);
			if(replace) {
				C = replaceChar;
				replace = false;
			}
			
			append = true;

			switch(C){
		
				case '(':
				case ')':
					last_was_brace = true;
					break;
			
				case ' ':
					if(last_was_brace) {
						append = false;
						last_was_brace = false;
					}
					else if(first_space) first_space = false;
					else if(!within_quotes)	append = false;

					break;
					
				case '\n':
					if(!within_quotes) {
						replace = true;
						replaceChar = ' ';
						i--;
						append = false;
						if(last_was_brace)
							first_space = false;
					}
					break;
					
				case '"':
					within_quotes = !within_quotes;
					last_was_brace = false;
					break;
					
				default:
					first_space = true;
					last_was_brace = false;

			}
			
			if(append) sb.append(C);
		}
		
		content = sb.toString();
	}
	
	

	private static void p(String s){
		
		if(!dev) return;
		System.out.println(s);
	}	
}
