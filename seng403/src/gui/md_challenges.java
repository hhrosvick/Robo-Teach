package gui;

import java.awt.EventQueue;
import javax.swing.text.Element;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JFrame;

import casa.CASAProcess;
import casa.Status;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;

import java.awt.Panel;
import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.ScrollPane;
import java.awt.Label;
import java.awt.Scrollbar;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class md_challenges {

	private JFrame frmRoboteach;
	private JTextArea textArea;
	private JTextArea lines;
	private JFileChooser fc;
	private File f;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					md_challenges window = new md_challenges();
					window.frmRoboteach.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public md_challenges() {
		initialize();
		fc = new JFileChooser();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRoboteach = new JFrame();
		frmRoboteach.setTitle("Robo-Teach");
		frmRoboteach.setBounds(100, 100, 800, 600);
		frmRoboteach.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRoboteach.getContentPane().setLayout(new BoxLayout(frmRoboteach.getContentPane(), BoxLayout.X_AXIS));
		//Creating the base panel
		final JPanel BasePanel = new JPanel();
		frmRoboteach.getContentPane().add(BasePanel);
		BasePanel.setLayout(new CardLayout(0, 0));
		//Data needed for resizing
		JPanel TitlePage = new JPanel();
		BasePanel.add(TitlePage, "name_14339387689257");
		TitlePage.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("bottom:default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
/*****************************************************************************************************************************************
* TitlePage initialization and components
*****************************************************************************************************************************************/
		
		//Title screen picture, add picture to folder and change file name here
		String imgStr = "Pic.png";
		ImageIcon image = new ImageIcon(imgStr);
		//Creating the picture panel
		Panel TitleLabelPanel = new Panel();
		TitlePage.add(TitleLabelPanel, "2, 2, 25, 9, fill, fill");
		TitleLabelPanel.setLayout(new BorderLayout(0, 0));
		JLabel TitleLabel = new JLabel(" ", image, JLabel.CENTER);
		//Creating the button panel
		JPanel TitleButtonPanel = new JPanel();
		TitlePage.add(TitleButtonPanel, "2, 12, 25, 3, fill, fill");
		TitleButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//Start Button Activity
		JButton StartButton = new JButton("Start");
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout) BasePanel.getLayout()).show(BasePanel, "TabPage");
			}
		});
		//User Manual Button Activity
		JButton UserManualButton = new JButton("User Manual");
		UserManualButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		//Put everything onto the title panel
		TitleButtonPanel.add(StartButton);
		TitleLabelPanel.add(TitleLabel);
		TitleButtonPanel.add(UserManualButton);
		
/*****************************************************************************************************************************************
* TitlePage initialization and components
*****************************************************************************************************************************************/	
		JTabbedPane TabPage = new JTabbedPane(JTabbedPane.TOP);
		BasePanel.add(TabPage, "TabPage");
		
		JPanel WelcomeTab = new JPanel();
		TabPage.addTab("Welcome", null, WelcomeTab, null);
		WelcomeTab.setLayout(new FormLayout(new ColumnSpec[] {
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
		
		JButton btnNewButton = new JButton("New button");
		WelcomeTab.add(btnNewButton, "2, 2");
		
		String imgStr2 = "LargPicture.png";
		ImageIcon image2 = new ImageIcon(imgStr2);
		
		JScrollPane scrollPane = new JScrollPane();
		WelcomeTab.add(scrollPane, "4, 2, 1, 31, fill, fill");
		
		JLabel lblNewLabel = new JLabel("New label");
		scrollPane.setViewportView(lblNewLabel);
		
		
		JButton btnNewButton_1 = new JButton("New button");
		WelcomeTab.add(btnNewButton_1, "2, 4");
		
		JButton btnNewButton_2 = new JButton("New button");
		WelcomeTab.add(btnNewButton_2, "2, 6");

/*****************************************************************************************************************************************
* LessonsTab initialization and components
******************************************************************************************************************************************/	
		JPanel LessonsTab = new JPanel();
		TabPage.addTab("Lessons", null, LessonsTab, null);

/*****************************************************************************************************************************************
* ProgramTab initialization and components
******************************************************************************************************************************************/	
		JPanel ProgramTab = new JPanel();
		textArea = new JTextArea();
		
		
		TabPage.addTab("Program", null, ProgramTab, null);
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
		
		JButton btnStartEmulator = new JButton("Start Emulator");
		ProgramTab.add(btnStartEmulator, "2, 2");
		
		btnStartEmulator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("ButtonPressed");
				Status p = CASAProcess.getInstance().abclEval("(load\"scripts/sim.lisp\")", null);
			}
		});
		
		
		JButton btnOpen = new JButton("Open File");
		ProgramTab.add(btnOpen, "2, 4");
		
		btnOpen.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					f = fc.getSelectedFile();
					BufferedReader br = null;
					try {
						br = new BufferedReader(new FileReader(f));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        String st="";
			        try {
			        	textArea.setText("");
						while((st=br.readLine())!=null){
							textArea.append(st + "\n");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				textArea.setCaretPosition(textArea.getDocument().getLength());
				}
			}
		});
		
		JButton btnSave = new JButton("Save File");
		ProgramTab.add(btnSave, "2, 6");
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(113, 0, 298, 209);
		
		// LINE NUMBERS
		// from 
		// http://www.javaprogrammingforums.com/java-swing-tutorials/915-how-add-line-numbers-your-jtextarea.html
		lines = new JTextArea("1");
		 
		lines.setBackground(Color.LIGHT_GRAY);
		lines.setEditable(false);
 
		textArea.getDocument().addDocumentListener(new DocumentListener(){
			
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
 
		});
		scrollPane1.setViewportView(textArea);
		scrollPane1.setRowHeaderView(lines);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		ProgramTab.add(scrollPane1, "4, 2, 1, 31, fill, fill");


/*****************************************************************************************************************************************
* ChallengeTab initialization and components
******************************************************************************************************************************************/			

		JPanel ChallengesTab = new JPanel();
		TabPage.addTab("Challenges", null, ChallengesTab, null);

		
		
		
		
		
		
		
		

/*****************************************************************************************************************************************
* ChallengeTab initialization and components
******************************************************************************************************************************************/			
	
	}
}
