package gui;

import javax.swing.JPanel;
import api.API;
import api.API_Interface;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GradesTab 
{

	private JPanel GradesTab;
	private JTextField SearchBox;
	private JTable table;
	private String[] title = {"Student ID", "Student Name", "Chapters Completed", "Challenges Completed"}; 
	
	private API_Interface api;
	private GradesMatrix GM;
	private int id;
	
	public GradesTab(int id, API_Interface a) 
	{
		GradesTab = new JPanel();
		try 
		{
			api = a;
		} catch (Exception e) {}
		GM = new GradesMatrix(id, api);
		this.id = id;
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
		if(true)//api.getUserType(id) == 1)
		{
			JLabel SearchLabel = new JLabel("Search:");
			GradesTab.add(SearchLabel, "2, 2");
			
			SearchBox = new JTextField();
			GradesTab.add(SearchBox, "2, 4");
			SearchBox.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent arg0) {
					String s = SearchBox.getText();
					if(s.compareTo("") != 0)
					{
						table.setModel(new DefaultTableModel(GM.updateMatrix(s), title));
					}
					else
						table.setModel(new DefaultTableModel(GM.getMatrix(), title));
				}
				@Override
				public void keyReleased(KeyEvent e) {
					String s = SearchBox.getText();
					if(s.compareTo("") != 0)
					{
						table.setModel(new DefaultTableModel(GM.updateMatrix(s), title));
					}
					else
						table.setModel(new DefaultTableModel(GM.getMatrix(), title));
				}
				@Override
				public void keyPressed(KeyEvent e) {
					String s = SearchBox.getText();
					if(s.compareTo("") != 0)
					{
						table.setModel(new DefaultTableModel(GM.updateMatrix(s), title));
					}
					else
						table.setModel(new DefaultTableModel(GM.getMatrix(), title));
				}
			});
			SearchBox.setColumns(10);
			
			JButton SortLessons = new JButton("Sort by Lessons");
			SortLessons.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					table.setModel(new DefaultTableModel(GM.SortLessons(), title));
				}
			});
			GradesTab.add(SortLessons, "2, 6");
			
			JButton SortChallenges = new JButton("Sort by Challenges");
			SortChallenges.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					table.setModel(new DefaultTableModel(GM.SortChallenges(), title));
				}
			});
			GradesTab.add(SortChallenges, "2, 8");
			
			JButton SortByNameButton = new JButton("Sort by Name");
			SortByNameButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					table.setModel(new DefaultTableModel(GM.SortName(), title));
				}
			});
			GradesTab.add(SortByNameButton, "2, 10");
			
			JButton SortByIDButton = new JButton("Sort by ID");
			SortByIDButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					table.setModel(new DefaultTableModel(GM.SortID(), title));
				}
			});
			GradesTab.add(SortByIDButton, "2, 12");
			
			JButton RefreshTableButton = new JButton("Refresh");
			RefreshTableButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					table.setModel(new DefaultTableModel(GM.refreshMatrix(), title));
				}
			});
			GradesTab.add(RefreshTableButton, "2, 14");
		}
		JScrollPane scrollPane = new JScrollPane();
		GradesTab.add(scrollPane, "4, 2, 3, 15, fill, fill");
		
	    DefaultTableModel model = new DefaultTableModel(GM.getMatrix(), title);
	    table = new JTable(model);
		scrollPane.setViewportView(table);
		
		return GradesTab;
	}
}
