package gui;

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

public class GradesTab {

	private JFrame frame;
	private JTextField SearchBox;
	private JTable table;

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
		SearchPanel.add(SearchBox);
		SearchBox.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GradesTab.add(scrollPane, "4, 2, 3, 1, fill, fill");
		
		 String[] columnNames = {"Student ID", "Student Name", "Chapters Competed", "Challenges Completed" };
	        Object[][] data = {
	        	{"00000000", "Average", new Double(2.22), new Double(1.22)},	
	            {"10012345", "David Ryan", new Double(2), new Double(0)},
	            {"Tea", new Integer(1), new Double(3.33), new Double(3.33)},
	            {"Cofee", new Integer(1), new Double(4.44), new Double(4.44)}
	        };
	        DefaultTableModel model = new DefaultTableModel(data, columnNames);
	    table = new JTable(model);
		scrollPane.setViewportView(table);
	}

}
