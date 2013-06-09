package gui2;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;


public class EditorTab extends JScrollPane{

	private static final long serialVersionUID = 1L;
	
	private JTextArea linenumbers = new JTextArea("1");
	private JTextArea content = new JTextArea();
	private String filePath = "";

	EditorTab(String text, String filePath)
	{
		super();
		this.filePath = filePath; 
		linenumbers.setBackground(Color.LIGHT_GRAY);
		linenumbers.setEditable(false);
		content.getDocument().addDocumentListener(new LineNumberChangeListener());
		content.setText(text);
		
		setViewportView(content);
		setRowHeaderView(linenumbers);
	}
	
	EditorTab()
	{
		this("", null);
	}
	
	public JTextArea getTextArea() {
		return content;
	}
	
	public String getFilePath() {
		return filePath;
	}

	class LineNumberChangeListener implements DocumentListener {
		
		public String getText()
		{
			int caretPosition = content.getDocument().getLength();
			Element root = content.getDocument().getDefaultRootElement();
			String text = "1";
			
			for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
				text +="\n"+ i;
			}
			
			return text;
		}
		public void changedUpdate(DocumentEvent de) {
			linenumbers.setText(getText());}
		public void insertUpdate(DocumentEvent de) {
			linenumbers.setText(getText());}
		public void removeUpdate(DocumentEvent de) {
			linenumbers.setText(getText());}
	}
	
}

