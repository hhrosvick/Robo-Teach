package gui;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView.TableRow;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GradesTab {

	private JPanel GradesTab;
	private JTextField SearchBox;
	private JTable table;
	private String[][] data = {
			{"00000000", "Class Average", "1.5", "2"},	
            {"10012345", "David Ryan", "3", "3"},
            {"12345678", "Henry", "0", "3"},
            {"87654321", "Marshall", "2", "1"},
            {"12312399", "Bob", "1", "0"}};
	private String[][] d2;
	private String[][] sortedLessons;
	private boolean sLessons = false;
	private String[][] sortedChallenges;
	private boolean sChallenges = false;
	private String[][] sortedName;
	private boolean sName = false;
	private String[][] sortedID;
	private boolean sID = false;
	private final JButton btnNewButton = new JButton("New button");
	
	public GradesTab() 
	{
		GradesTab = new JPanel();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	public JPanel initialize() {
		GradesTab.setBounds(100, 100, 450, 300);
		GradesTab.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.BUTTON_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.GROWING_BUTTON_COLSPEC,
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
				RowSpec.decode("fill:default:grow"),}));
		
		JLabel SearchLabel = new JLabel("Search:");
		GradesTab.add(SearchLabel, "2, 2");
		
		SearchBox = new JTextField();
		GradesTab.add(SearchBox, "2, 4");
		SearchBox.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
				String s = SearchBox.getText();
				if(s.compareTo("") != 0)
				{
					updateTable(s);
				}
				else
					refreshTable();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String s = SearchBox.getText();
				if(s.compareTo("") != 0)
				{
					updateTable(s);
				}
				else
					refreshTable();
			}
			@Override
			public void keyPressed(KeyEvent e) {
				String s = SearchBox.getText();
				if(s.compareTo("") != 0)
				{
					updateTable(s);
				}
				else
					refreshTable();
			}
		});
		SearchBox.setColumns(10);
		
		JButton SortLessons = new JButton("Sort by Lessons");
		GradesTab.add(SortLessons, "2, 6");
		SortLessons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!sLessons)
					SortLessons();
				String[] cn = {"Student ID", "Student Name", "Chapters Completed", "Challenges Completed"};
				DefaultTableModel sl = new DefaultTableModel(sortedLessons, cn);
				table.setModel(sl);
			}
		});
		
		JButton SortChallenges = new JButton("Sort by Challenges");
		GradesTab.add(SortChallenges, "2, 8");
		SortChallenges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!sChallenges)
					SortChallenges();
				String[] cn = {"Student ID", "Student Name", "Chapters Completed", "Challenges Completed"};
				DefaultTableModel sc = new DefaultTableModel(sortedChallenges, cn);
				table.setModel(sc);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GradesTab.add(btnNewButton, "2, 10");
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GradesTab.add(btnNewButton_1, "2, 12");
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GradesTab.add(btnNewButton_2, "2, 14");
		
		JScrollPane scrollPane = new JScrollPane();
		GradesTab.add(scrollPane, "4, 2, 3, 15, fill, fill");
		
		String[] columnNames = {"Student ID", "Student Name", "Chapters Competed", "Challenges Completed" };
	    DefaultTableModel model = new DefaultTableModel(data, columnNames);
	    table = new JTable(model);
		scrollPane.setViewportView(table);
		
		return GradesTab;
	}

	protected void refreshTable() 
	{
		String[] cn = {"Student ID", "Student Name", "Chapters Competed", "Challenges Completed" };
	    DefaultTableModel m = new DefaultTableModel(this.data, cn);
	    table.setModel(m);
		
	}

	protected void updateTable(String s) 
	{
		String[] cn = {"Student ID", "Student Name", "Chapters Completed", "Challenges Completed"};
		int matches = 0;
		int index = 0;
		for(int i = 0; i < this.data.length; i++)
			if((this.data[i][1].toLowerCase()).contains(s.toLowerCase()))
				matches++;
		this.d2 = new String[matches][4];
		for(int i = 0; i < this.data.length; i++)
		{
			if((this.data[i][1].toLowerCase()).contains(s.toLowerCase()))
			{
				for(int j = 0; j < 4; j++)
				{
					this.d2[index][j] = this.data[i][j];
				}
				index++;
			}
		}
		DefaultTableModel m = new DefaultTableModel(d2, cn);
		table.setModel(m);
	}
	protected void SortLessons()
	{
		sortedLessons = new String[data.length][4];
		for(int i = 0; i < data.length; i++)
			for(int j = 0; j < 4; j++)
				sortedLessons[i][j] = data[i][j];
		for(int i = 1; i < sortedLessons.length; i++)
		{
			for(int j = i; j < sortedLessons.length-1; j++)
			{
				if(Integer.parseInt(sortedLessons[j][2]) < Integer.parseInt(sortedLessons[j+1][2]))
				{
					String[] temp = sortedLessons[j];
					sortedLessons[j] = sortedLessons[j+1];
					sortedLessons[j+1] = temp;
				}
			}
		}
	}
	protected void SortChallenges()
	{
		sortedChallenges = new String[data.length][4];
		for(int i = 0; i < data.length; i++)
			for(int j = 0; j < 4; j++)
				sortedChallenges[i][j] = data[i][j];
		for(int i = 1; i < sortedLessons.length; i++)
		{
			for(int j = i; j < sortedChallenges.length-1; j++)
			{
				if(Integer.parseInt(sortedChallenges[j][3]) < Integer.parseInt(sortedChallenges[j+1][3]))
				{
					String[] temp = sortedChallenges[j];
					sortedChallenges[j] = sortedChallenges[j+1];
					sortedChallenges[j+1] = temp;
				}
			}
		}
	}

}
