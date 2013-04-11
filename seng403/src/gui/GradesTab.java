package gui;

import javax.swing.JPanel;
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
	private JTable table;

	private static JLabel SearchLabel;
	private static JTextField SearchBox;
	private static JButton SortLessons;
	private static JButton SortChallenges;
	private static JButton SortByNameButton;
	private static JButton SortByIDButton;
	private static JButton RefreshTableButton;
	
	private GradesMatrix Matrix;
	private String[] TITLE = {"Student ID", "Student Name", "Chapters Completed", "Challenges Completed"}; 
	
	public GradesTab() 
	{
		GradesTab = new JPanel();
		Matrix = new GradesMatrix();
		initialize();
	}
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
		
		SearchLabel = new JLabel("Search:");
		GradesTab.add(SearchLabel, "2, 2");
		
		SearchBox = new JTextField();
		GradesTab.add(SearchBox, "2, 4");
		SearchBox.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
				String s = SearchBox.getText();
				if(s.compareTo("") != 0)
				{
					table.setModel(new DefaultTableModel(Matrix.updateMatrix(s), TITLE));
				}
				else
					table.setModel(new DefaultTableModel(Matrix.getMatrix(), TITLE));
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String s = SearchBox.getText();
				if(s.compareTo("") != 0)
				{
					table.setModel(new DefaultTableModel(Matrix.updateMatrix(s), TITLE));
				}
				else
					table.setModel(new DefaultTableModel(Matrix.getMatrix(), TITLE));
			}
			@Override
			public void keyPressed(KeyEvent e) {
				String s = SearchBox.getText();
				if(s.compareTo("") != 0)
				{
					table.setModel(new DefaultTableModel(Matrix.updateMatrix(s), TITLE));
				}
				else
					table.setModel(new DefaultTableModel(Matrix.getMatrix(), TITLE));
			}
		});
		SearchBox.setColumns(10);
		
		SortLessons = new JButton("Sort by Lessons");
		SortLessons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(new DefaultTableModel(Matrix.SortLessons(), TITLE));
			}
		});
		GradesTab.add(SortLessons, "2, 6");
		
		SortChallenges = new JButton("Sort by Challenges");
		SortChallenges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(new DefaultTableModel(Matrix.SortChallenges(), TITLE));
			}
		});
		GradesTab.add(SortChallenges, "2, 8");
		
		SortByNameButton = new JButton("Sort by Name");
		SortByNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel(Matrix.SortName(), TITLE));
			}
		});
		GradesTab.add(SortByNameButton, "2, 10");
		
		SortByIDButton = new JButton("Sort by ID");
		SortByIDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel(Matrix.SortID(), TITLE));
			}
		});
		GradesTab.add(SortByIDButton, "2, 12");
		
		RefreshTableButton = new JButton("Refresh");
		RefreshTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel(Matrix.refreshMatrix(), TITLE));
			}
		});
		GradesTab.add(RefreshTableButton, "2, 14");
		
		JScrollPane scrollPane = new JScrollPane();
		GradesTab.add(scrollPane, "4, 2, 3, 15, fill, fill");
		
	    DefaultTableModel model = new DefaultTableModel(Matrix.getMatrix(), TITLE);
	    table = new JTable(model);
		scrollPane.setViewportView(table);
		
		return GradesTab;
	}
	public static void RemoveButtonsForStudents()
	{
		SearchLabel.setVisible(false);
		SearchBox.setVisible(false);
		SortLessons.setVisible(false);
		SortChallenges.setVisible(false);
		SortByNameButton.setVisible(false);
		SortByIDButton.setVisible(false);
	}
}
