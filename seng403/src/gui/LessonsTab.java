package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LessonsTab {

	private JPanel LessonsTab;
	private String Selection;

	/**
	 * Create the application.
	 */
	public LessonsTab() 
	{
		LessonsTab = new JPanel();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	public JPanel initialize() {
		LessonsTab.setBounds(100, 100, 450, 300);
		LessonsTab.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.BUTTON_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.BUTTON_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton StartLessonButton = new JButton("Start Lesson");
		StartLessonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Selection.startsWith("Chapter") || Selection.compareTo("Lessons") == 0 || Selection == null)
					return;
				else
				{
					//Create new lesson window
				}
			}
		});
		LessonsTab.add(StartLessonButton, "2, 2, 3, 1");
		
		JScrollPane scrollPane_2 = new JScrollPane();
		LessonsTab.add(scrollPane_2, "6, 2, 17, 15, fill, fill");
		
		final JLabel LessonPreviewLabel = new JLabel("");
		scrollPane_2.setViewportView(LessonPreviewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		LessonsTab.add(scrollPane_1, "2, 4, 3, 13, fill, fill");
		
		final JTree LessonsTree = new JTree();
		LessonsTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Lessons") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Chapter 1");
						node_1.add(new DefaultMutableTreeNode("Lesson1.1"));
						node_1.add(new DefaultMutableTreeNode("Lesson1.2"));
						node_1.add(new DefaultMutableTreeNode("Lesson1.3"));
						node_1.add(new DefaultMutableTreeNode("Lesson1.4"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Chapter 2");
						node_1.add(new DefaultMutableTreeNode("Lesson2.1"));
						node_1.add(new DefaultMutableTreeNode("Lesson2.2"));
						node_1.add(new DefaultMutableTreeNode("Lesson2.3"));
						node_1.add(new DefaultMutableTreeNode("Lesson2.4"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Chapter 3");
						node_1.add(new DefaultMutableTreeNode("Lesson3.1"));
						node_1.add(new DefaultMutableTreeNode("Lesson3.2"));
						node_1.add(new DefaultMutableTreeNode("Lesson3.3"));
						node_1.add(new DefaultMutableTreeNode("Lesson3.4"));
					add(node_1);
				}
			}
		));
		scrollPane_1.setViewportView(LessonsTree);
		
		LessonsTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) LessonsTree.getLastSelectedPathComponent();	
				Object nodeInfo = node.getUserObject();
				Selection = nodeInfo.toString();
				LessonPreviewLabel.setText(Selection);
			}
		});
		return LessonsTab;
	}
}
