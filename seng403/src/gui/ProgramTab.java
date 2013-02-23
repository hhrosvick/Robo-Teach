package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import casa.CASAProcess;
import casa.Status;

public class ProgramTab implements ActionListener {
	
	private JPanel ProgramTab;
	private JTextArea textArea;
	private JTextArea lines;
	private JFileChooser fc;
	private File f;
	private JButton btnNewFile, btnStartEmulator, btnOpen, btnSave;
	private String fileName;
	
	
	public ProgramTab()
	{
		ProgramTab = new JPanel();
		fc = new JFileChooser();
		fileName = null;
		//UNCOMMENT THIS IS IF YOU WANTED TO SEE THE GUI ON THE DESIGN TAB
		//initialize();
	}
	
	public JPanel initialize()
	{
		ProgramTab.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		textArea = new JTextArea();
		
		btnNewFile = new JButton("New File");
		btnNewFile.addActionListener(this);
		ProgramTab.add(btnNewFile, "2, 4");
		
		btnStartEmulator = new JButton("Start Emulator");
		btnStartEmulator.addActionListener(this);
		ProgramTab.add(btnStartEmulator, "2, 2");
		
		btnOpen = new JButton("Open File");
		btnOpen.addActionListener(this);
		ProgramTab.add(btnOpen, "2, 6");
		
		btnSave = new JButton("Save File");
		btnSave.addActionListener(this);
		ProgramTab.add(btnSave, "2, 8");
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(113, 0, 298, 209);
		
		// LINE NUMBERS
		// from 
		// http://www.javaprogrammingforums.com/java-swing-tutorials/915-how-add-line-numbers-your-jtextarea.html
		lines = new JTextArea("1");
		 
		lines.setBackground(Color.LIGHT_GRAY);
		lines.setEditable(false);
 
		textArea.getDocument().addDocumentListener(new MyDocumentListener());
		
		scrollPane1.setViewportView(textArea);
		scrollPane1.setRowHeaderView(lines);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		ProgramTab.add(scrollPane1, "4, 2, 1, 31, fill, fill");
		
		return ProgramTab;
	}
	
	public void actionPerformed(ActionEvent e) {
		 
        //Handle start emulator button action.
        if (e.getSource() == btnStartEmulator) 
        {
        	System.out.println("ButtonPressed");
			Status p = CASAProcess.getInstance().abclEval("(load\"scripts/sim.lisp\")", null);
        
        } 
      //Handle open button action.
        else if (e.getSource() == btnOpen) 
        {
        	int returnVal = fc.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				f = fc.getSelectedFile();
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(f));
				} catch (FileNotFoundException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
		        String st="";
		        try {
		        	textArea.setText("");
					while((st=br.readLine())!=null){
						textArea.append(st + "\n");
					}
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
			textArea.setCaretPosition(textArea.getDocument().getLength());
			}
        }
        //Handle new button action.
        else if (e.getSource() == btnNewFile)
        {
        	
        }
      //Handle save button action.
        else if (e.getSource() == btnSave)
        {
        	
        }
    }
	
	class MyDocumentListener implements DocumentListener {
		public String getText()
		{
			int caretPosition = textArea.getDocument().getLength();
			Element root = textArea.getDocument().getDefaultRootElement();
			String text = "1" + System.getProperty("line.separator");
			
			for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
				text += i + System.getProperty("line.separator");
			}
			
			return text;
		}
		
		@Override
		public void changedUpdate(DocumentEvent de) {
			lines.setText(getText());
		}

		@Override
		public void insertUpdate(DocumentEvent de) {
			lines.setText(getText());
		}

		@Override
		public void removeUpdate(DocumentEvent de) {
			lines.setText(getText());
		}
	}
		
}