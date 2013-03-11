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

	private JFrame frame;
	private JTextField SearchBox;
	private JTable table;
	private String[][] data = {
        	{"00000000", "Average", "", ""},	
            {"10012345", "David Ryan", "", ""},
            {"Tea", "", "", ""},
            {"Cofee", "", "", ""}
        };
	private String[][] d2;
	private String[][] d3;
	private int entries;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradesTab window = new GradesTab();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GradesTab() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel GradesTab = new JPanel();
		frame.getContentPane().add(GradesTab, BorderLayout.CENTER);
		GradesTab.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.BUTTON_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.GROWING_BUTTON_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel SearchPanel = new JPanel();
		GradesTab.add(SearchPanel, "2, 2, center, top");
		SearchPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel SearchLabel = new JLabel("Search:");
		SearchPanel.add(SearchLabel);
		
		SearchBox = new JTextField();
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
		SearchPanel.add(SearchBox);
		SearchBox.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GradesTab.add(scrollPane, "4, 2, 3, 1, fill, fill");
		
		String[] columnNames = {"Student ID", "Student Name", "Chapters Competed", "Challenges Completed" };
	    DefaultTableModel model = new DefaultTableModel(data, columnNames);
	    table = new JTable(model);
		scrollPane.setViewportView(table);
		
		
	}

	protected void refreshTable() {
		String[] cn = {"Student ID", "Student Name", "Chapters Competed", "Challenges Completed" };
	    DefaultTableModel m = new DefaultTableModel(this.data, cn);
	    table.setModel(m);
		
	}

	public void updateTable(String s) {
		String[] cn = {"Student ID", "Student Name", "Chapters Completed", "Challenges Completed"};
		int matches = 0;
		int index = 0;
		for(int i = 0; i < this.data.length; i++)
			if(this.data[i][1].contains(s))
				matches++;
		this.d3 = new String[matches][4];
		for(int i = 0; i < this.data.length; i++)
		{
			if(this.data[i][1].contains(s))
			{
				for(int j = 0; j < 4; j++)
				{
					this.d3[index][j] = this.data[i][j];
				}
				index++;
			}
		}
		DefaultTableModel m = new DefaultTableModel(d3, cn);
		table.setModel(m);
		
	}

}
