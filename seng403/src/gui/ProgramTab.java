package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Element;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ProgramTab implements ActionListener {
	
	private JPanel ProgramTab;
	private JTextArea textArea;
	private JTextArea lines;
	private JFileChooser fc;
	private File f;
	private JButton btnNewFile, btnOpen, btnSave;
	private String filePath;
	private JButton btnRunOnEmulator;
	private JButton btnRunOnRobot;
	
	private File tempFile;
	private JButton btnStartRemote;
	private JButton btnRobotRemote;
	
	public ProgramTab()
	{
		ProgramTab = new JPanel();
		fc = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("LISP Files", "lisp");
	    fc.setFileFilter(filter);
		filePath = null;
		
		try {
			tempFile = new File("./robotemp.lisp");
			tempFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		ProgramTab.add(btnNewFile, "2, 6");
		
		btnOpen = new JButton("Open File");
		btnOpen.addActionListener(this);
		ProgramTab.add(btnOpen, "2, 8");
		
		btnSave = new JButton("Save File");
		btnSave.addActionListener(this);
		ProgramTab.add(btnSave, "2, 10");
		
		btnRunOnEmulator = new JButton("Run on Emulator");
		btnRunOnEmulator.addActionListener(this);
		ProgramTab.add(btnRunOnEmulator, "2, 12");
		
		btnRunOnRobot = new JButton("Run on Robot");
		btnRunOnRobot.addActionListener(this);
		ProgramTab.add(btnRunOnRobot, "2, 14");
		
		btnStartRemote = new JButton("Emulator Remote");
		btnStartRemote.addActionListener(this);
		ProgramTab.add(btnStartRemote, "2, 16");
		
		btnRobotRemote = new JButton("Robot Remote");
		btnRobotRemote.addActionListener(this);
		ProgramTab.add(btnRobotRemote, "2, 18");
		
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
	
	public void actionPerformed(ActionEvent e) 
	{
		
        if (e.getSource() == btnRunOnEmulator)
        {
        	writeToTemp();
        	RoboTeach.getAPI_Interface().loadToSimulator(tempFile.getAbsolutePath());
        }
        else if (e.getSource() == btnStartRemote)
        {
        	Remote r = new Remote(true);
        	r.OpenWindow();
        	////////////////////////////////////////////////////////////////////////////////////////////////////
        }
        else if (e.getSource() == btnRobotRemote)
        {
        	Remote r = new Remote(false);
        	r.OpenWindow();
        }
        else if (e.getSource() == btnRunOnRobot)
        {
        	writeToTemp();
        	RoboTeach.getAPI_Interface().loadToRobot(tempFile.getAbsolutePath());
        }
        //Handle new button action.
        else if (e.getSource() == btnNewFile)
        {
        	filePath = null;
        	textArea.setText("");
        }
      //Handle open button action.
        else if (e.getSource() == btnOpen) 
        {
        	int returnVal = fc.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				f = fc.getSelectedFile();
				
				filePath = f.getAbsolutePath();
				
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(f));
				} catch (FileNotFoundException f) {
					f.printStackTrace();
				}
				
		        String st="";
		        try {
		        	textArea.setText("");
					while((st=br.readLine())!=null){
						textArea.append(st + "\n");
					}
				} catch (IOException f) {
					f.printStackTrace();
				}
		        
			textArea.setCaretPosition(textArea.getDocument().getLength());
			
			}
        }
      //Handle save button action.
        else if (e.getSource() == btnSave)
        {
        	if(filePath != null)
        	{
        		BufferedWriter outFile;
				try {
					outFile = new BufferedWriter( new FileWriter( filePath ) );
					//outFile.write( textArea.getText() ); //put in text file
					textArea.write(outFile);
	                outFile.flush( ); 
	                outFile.close( );
	                
	                JOptionPane.showMessageDialog(null, "File Successfully Saved!");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        	else
        	{
        		try {
					saveButtonNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        }
    }
	
	public void saveButtonNewFile() throws IOException
	{
		int returnVal = fc.showSaveDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			f = new File (fc.getSelectedFile() + ".lisp");
			System.out.println(f);

            if(f != null)
            {
            	if(f.exists())
            	{
            		returnVal = JOptionPane.showConfirmDialog(null, "Replace existing file?");

            		while (returnVal == JOptionPane.NO_OPTION)
            		{
            			returnVal = fc.showSaveDialog(null);
            				if ( returnVal == JFileChooser.APPROVE_OPTION)
            				{
            					f = new File(fc.getSelectedFile( ) + ".lisp");
            					if(f.exists())
            					{
            						returnVal = JOptionPane.showConfirmDialog(null, "Replace existing file?");
                                                                
            					}
            				}

            		}
            	}

            filePath = f.getAbsolutePath();
            BufferedWriter outFile = new BufferedWriter( new FileWriter( f ) );
            //outFile.write( textArea.getText() ); //put in text file
            textArea.write(outFile);
            outFile.flush( ); 
            outFile.close( );
            
            JOptionPane.showMessageDialog(null, "File Successfully Saved!");
            }
		}
	}

	private void writeToTemp() {
		
		BufferedWriter outFile;
		try {
			
			outFile = new BufferedWriter( new FileWriter( tempFile ) );
	        //outFile.write( textArea.getText() ); //put in text file
	        textArea.write(outFile);
	        outFile.flush( ); 
	        outFile.close( );
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	class MyDocumentListener implements DocumentListener {
		public String getText()
		{
			int caretPosition = textArea.getDocument().getLength();
			Element root = textArea.getDocument().getDefaultRootElement();
			String text = "1";
			
			for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
				text +="\n"+ i;
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