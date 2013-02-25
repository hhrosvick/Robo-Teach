package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.ImageIcon;
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
import java.awt.FlowLayout;

public class ChallengesTab {

	private JPanel ChallengesTab;
	private String Selection;

	/**
	 * Create the application.
	 */
	public ChallengesTab() 
	{
		ChallengesTab = new JPanel();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	public JPanel initialize() {
		ChallengesTab.setBounds(100, 100, 450, 300);
		ChallengesTab.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(44dlu;pref)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(39dlu;pref)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("8dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(7dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(9dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(9dlu;default)"),
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
		
		JButton StartChallengeButton = new JButton("Start Challenge");
		StartChallengeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Selection.endsWith("Tier") || Selection.compareTo("Challenges") == 0 || Selection == null)
					return;
				else
				{
					ChallengeWindow NewWindow = new ChallengeWindow(Selection);
					NewWindow.OpenWindow();
				}
			}
		});
		ChallengesTab.add(StartChallengeButton, "2, 2, 3, 1");
		
		JScrollPane PreviewPane = new JScrollPane();
		ChallengesTab.add(PreviewPane, "6, 2, 17, 15, fill, fill");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		PreviewPane.setViewportView(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		String imgStr = "Challenges/ChallengesPreview.png";
		final ImageIcon LessonsTitle = new ImageIcon(imgStr);
		final JLabel ChallengePreviewLabel = new JLabel("", LessonsTitle, JLabel.CENTER);
		panel.add(ChallengePreviewLabel);
		
		JScrollPane TreePane = new JScrollPane();
		ChallengesTab.add(TreePane, "2, 4, 3, 13, fill, fill");
		
		final JTree ChallengesTree = new JTree();
		ChallengesTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Challenges") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Beginner Tier");
						node_1.add(new DefaultMutableTreeNode("Challenge1"));
						node_1.add(new DefaultMutableTreeNode("ExampleChallenge"));
						node_1.add(new DefaultMutableTreeNode("Challenge3"));
						node_1.add(new DefaultMutableTreeNode("Challenge4"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Advanced Tier");
						node_1.add(new DefaultMutableTreeNode("Challenge5"));
						node_1.add(new DefaultMutableTreeNode("Challenge6"));
						node_1.add(new DefaultMutableTreeNode("Challenge7"));
						node_1.add(new DefaultMutableTreeNode("Challenge8"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Expert Tier");
						node_1.add(new DefaultMutableTreeNode("Challenge9"));
						node_1.add(new DefaultMutableTreeNode("Challenge10"));
						node_1.add(new DefaultMutableTreeNode("Challenge11"));
						node_1.add(new DefaultMutableTreeNode("Challenge12"));
					add(node_1);
				}
			}
		));
		TreePane.setViewportView(ChallengesTree);
		
		ChallengesTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) ChallengesTree.getLastSelectedPathComponent();	
				Object nodeInfo = node.getUserObject();
				Selection = nodeInfo.toString();
				String imgStr = "Challenges/" + Selection + "Preview" + ".png";
				final ImageIcon ChallengePreview = new ImageIcon(imgStr);
				ChallengePreviewLabel.setIcon(ChallengePreview);
			}
		});
		return ChallengesTab;
	}
}
