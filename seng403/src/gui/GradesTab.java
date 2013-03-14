package gui;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import api.API;
import api.API_Interface;

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
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

public class GradesTab 
{

	private JPanel GradesTab;
	private JTextField SearchBox;
	private JTable table;
	private String[] title = {"Student ID", "Student Name", "Chapters Completed", "Challenges Completed"}; 
	private String[][] data;
	private String[][] d2;
	
	private String[][] sortedLessons;
	private boolean sLessons = false;
	private String[][] sortedChallenges;
	private boolean sChallenges = false;
	private String[][] sortedName;
	private boolean sName = false;
	private String[][] sortedID;
	private boolean sID = false;
	
	private API_Interface api;
	
	public GradesTab() 
	{
		GradesTab = new JPanel();
		try 
		{
			api = new API();
		} catch (Exception e) {}
		refreshTable();
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
					fullTable();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String s = SearchBox.getText();
				if(s.compareTo("") != 0)
				{
					updateTable(s);
				}
				else
					fullTable();
			}
			@Override
			public void keyPressed(KeyEvent e) {
				String s = SearchBox.getText();
				if(s.compareTo("") != 0)
				{
					updateTable(s);
				}
				else
					fullTable();
			}
		});
		SearchBox.setColumns(10);
		
		JButton SortLessons = new JButton("Sort by Lessons");
		SortLessons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!sLessons)
					SortLessons();
				DefaultTableModel tm = new DefaultTableModel(sortedLessons, title);
				table.setModel(tm);
			}
		});
		GradesTab.add(SortLessons, "2, 6");
		
		JButton SortChallenges = new JButton("Sort by Challenges");
		SortChallenges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!sChallenges)
					SortChallenges();
				DefaultTableModel tm = new DefaultTableModel(sortedChallenges, title);
				table.setModel(tm);
			}
		});
		GradesTab.add(SortChallenges, "2, 8");
		
		JButton SortByNameButton = new JButton("Sort by Name");
		SortByNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sName)
					SortName();
				DefaultTableModel tm = new DefaultTableModel(sortedName, title);
				table.setModel(tm);
			}
		});
		GradesTab.add(SortByNameButton, "2, 10");
		
		JButton SortByIDButton = new JButton("Sort by ID");
		SortByIDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sID)
					SortID();
				DefaultTableModel tm = new DefaultTableModel(sortedID, title);
				table.setModel(tm);
			}
		});
		GradesTab.add(SortByIDButton, "2, 12");
		
		JButton RefreshTableButton = new JButton("Refresh");
		RefreshTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
				DefaultTableModel sc = new DefaultTableModel(data, title);
				table.setModel(sc);
			}
		});
		GradesTab.add(RefreshTableButton, "2, 14");
		
		JScrollPane scrollPane = new JScrollPane();
		GradesTab.add(scrollPane, "4, 2, 3, 15, fill, fill");
		
	    DefaultTableModel model = new DefaultTableModel(data, title);
	    table = new JTable(model);
		scrollPane.setViewportView(table);
		
		return GradesTab;
	}

	protected void refreshTable() 
	{
		Map<Integer, Map<String, String>> progress = api.getAllUserProgress();
		
		Vector<Integer> userData = api.getAllUserIDs();
		Object[] users = userData.toArray();
		
		Object name = "name";
		Object chapter = "chapter";
		Object challenge = "challenge";
		data = new String[users.length+1][4];

		Map<String, String> average = api.getUserProgress(000000);
		
		data[0][0] = average.get("id");
		data[0][1] = average.get("name");
		data[0][2] = average.get("avgchapter");
		data[0][3] = average.get("avgchallenge");

		for(int i = 0; i < users.length; i++)
		{
			data[i+1][0] = users[i].toString();
			data[i+1][1] = progress.get(users[i]).remove(name);
			data[i+1][2] = progress.get(users[i]).remove(chapter);
			data[i+1][3] = progress.get(users[i]).remove(challenge);
		}
	}

	protected void fullTable() 
	{
	    DefaultTableModel model = new DefaultTableModel(data, title);
	    table.setModel(model);
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
		for(int i = 0; i < sortedLessons.length; i++)
		{
			for(int j = 1; j < sortedLessons.length-i; j++)
			{
				if(Float.parseFloat(sortedLessons[j-1][2]) < Float.parseFloat(sortedLessons[j][2]))
				{
					String[] temp = sortedLessons[j-1];
					sortedLessons[j-1] = sortedLessons[j];
					sortedLessons[j] = temp;
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
		for(int i = 0; i < sortedChallenges.length; i++)
		{
			for(int j = 1; j < sortedChallenges.length-i; j++)
			{
				if(Float.parseFloat(sortedChallenges[j-1][3]) < Float.parseFloat(sortedChallenges[j][3]))
				{
					String[] temp = sortedChallenges[j-1];
					sortedChallenges[j-1] = sortedChallenges[j];
					sortedChallenges[j] = temp;
				}
			}
		}
	}
	protected void SortName()
	{
		sortedName = new String[data.length][4];
		for(int i = 0; i < data.length; i++)
			for(int j = 0; j < 4; j++)
				sortedName[i][j] = data[i][j];
		for(int i = 0; i < sortedName.length; i++)
		{
			for(int j = 1; j < sortedName.length-i; j++)
			{
				if((sortedName[j-1][1]).compareTo(sortedName[j][1]) > 0)
				{
					String[] temp = sortedName[j-1];
					sortedName[j-1] = sortedName[j];
					sortedName[j] = temp;
				}
			}
		}
	}
	protected void SortID()
	{
		sortedID = new String[data.length][4];
		for(int i = 0; i < data.length; i++)
			for(int j = 0; j < 4; j++)
				sortedID[i][j] = data[i][j];
		for(int i = 0; i < sortedID.length; i++)
		{
			for(int j = 1; j < sortedID.length-i; j++)
			{
				if((sortedID[j-1][0]).compareTo(sortedID[j][0]) > 0)
				{
					String[] temp = sortedID[j-1];
					sortedID[j-1] = sortedID[j];
					sortedID[j] = temp;
				}
			}
		}
	}
}
