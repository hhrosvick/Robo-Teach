package gui;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("serial")
public class Quizzes extends JFrame {
	
	private JRadioButton first;
	private JRadioButton second;
	private JRadioButton third;
	private JRadioButton fourth;
	private ButtonGroup group;
	
	private JPanel radioPanel;
	private JFrame jframe;
	private LessonsTab ltab;
	
	private int nCorrect=0;
	
	private FileInputStream fstream;
	private DataInputStream in;
	private BufferedReader br;
	private int count = 0;
	
	
	@SuppressWarnings("deprecation")
	public Quizzes(int Chapter, JFrame frame, LessonsTab tab) throws IOException
	{
		super("Chapter " + Chapter + " Quiz");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
		        jframe.enable();
		        jframe.toFront();
			}
			@Override
			public void windowClosed(WindowEvent e) {
				setVisible(false);
		        jframe.enable();
		        jframe.toFront();
			}
		});

		setVisible(true);
		getContentPane().setForeground(SystemColor.info);
		getContentPane().setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		getContentPane().setBackground(SystemColor.control);
		
		setResizable(false);
		setSize(488,361);
		
		//get size of the screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = toolkit.getScreenSize(); 
				
		///TO SHOW THE WINDOW IN THE CENTER OF THE SCREEN
		int x = (screenSize.width - super.getWidth()) / 2;  
		int y = (screenSize.height - super.getHeight()) / 2;  
				
		setLocation(x,y);
		ltab = tab;
		jframe = frame;
		jframe.disable();
		
		if(Chapter == 1)
		{
			openFile(Chapter);
		}
		else if(Chapter == 2)
		{
			openFile(Chapter);
		}
		else if(Chapter == 3)
		{
			openFile(1);
		}
		
		Chapter();

	}
	
	public void openFile(int c)
	{
		try{
			  fstream = new FileInputStream("Quizzes/"+ c + ".txt");
			  in = new DataInputStream(fstream);
			  br = new BufferedReader(new InputStreamReader(in));
			  
		}catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
		}
	}
	
	public void Chapter() throws IOException
	{
		getContentPane().removeAll();
		getContentPane().repaint();
		revalidate();
		
		JTextPane txtpnQuestion = new JTextPane();
        txtpnQuestion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
        txtpnQuestion.setForeground(new Color(160, 82, 45));
        txtpnQuestion.setBackground(SystemColor.control);
        txtpnQuestion.setEditable(false);
        
		String s = br.readLine();
		String s1 = s.replace("\\r", "\n");
		s1 = s1.replace("\\t", "\t");
		
        txtpnQuestion.setText(s1);
        txtpnQuestion.setBounds(12, 13, 450, 120);
        getContentPane().add(txtpnQuestion);
		
        radioPanel = new JPanel();
        radioPanel.setBounds(12, 125, 458, 132);
		radioPanel.setLayout(new GridLayout(0, 1));
        
		first = new JRadioButton(br.readLine());
        first.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        first.setSelected(true);

        second = new JRadioButton(br.readLine());
        second.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

        third = new JRadioButton(br.readLine());
        third.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

        fourth = new JRadioButton(br.readLine());
        fourth.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

        first.setActionCommand("1");
        second.setActionCommand("2");
        third.setActionCommand("3");
        fourth.setActionCommand("4");

        //Group the radio buttons.
        group = new ButtonGroup();
        getContentPane().setLayout(null);
        group.add(first);
        group.add(second);
        group.add(third);
        group.add(fourth);

        //Put the radio buttons in a column in a panel.
        radioPanel.add(first);
        radioPanel.add(second);
        radioPanel.add(third);
        radioPanel.add(fourth);

        getContentPane().add(radioPanel);
        JButton btnNext;
        
        if(count == 4)
        	btnNext = new JButton("SUBMIT");
        else
        	btnNext = new JButton("NEXT");
        
        btnNext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		count++;
        		try {
					if (group.getSelection().getActionCommand().equals(br.readLine()))
						nCorrect++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		if(count < 5)
					try {
						Chapter();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else
        		{
        			count = 0;
        			setVisible(false);
            		jframe.enable();
            		jframe.toFront();
            		ltab.result(getResult());
            		try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	}
        });
        btnNext.setBounds(335, 263, 119, 30);
        getContentPane().add(btnNext);
	}
	
	public int getResult()
	{
		return nCorrect;
	}

}