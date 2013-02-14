package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
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
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		final JPanel BasePanel = new JPanel();
		frame.getContentPane().add(BasePanel);
		BasePanel.setLayout(new CardLayout(0, 0));
		
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
* TitlePage initialisation and components
*****************************************************************************************************************************************/		
		String imgStr = "Pic.png";
		ImageIcon image = new ImageIcon(imgStr);
		
		Panel TitleLabelPanel = new Panel();
		TitlePage.add(TitleLabelPanel, "2, 2, 25, 9, fill, fill");
		TitleLabelPanel.setLayout(new BorderLayout(0, 0));
		JLabel TitleLabel = new JLabel(" ", image, JLabel.CENTER);
		TitleLabelPanel.add(TitleLabel);
		
		JPanel TitleButtonPanel = new JPanel();
		TitlePage.add(TitleButtonPanel, "2, 12, 25, 3, fill, fill");
		TitleButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton StartButton = new JButton("Start");
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout) BasePanel.getLayout()).show(BasePanel, "TabPage");
			}
		});
		TitleButtonPanel.add(StartButton);
		
		JButton UserManualButton = new JButton("New button");
		TitleButtonPanel.add(UserManualButton);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		JTabbedPane TabPage = new JTabbedPane(JTabbedPane.TOP);
		BasePanel.add(TabPage, "TabPage");
		
		JPanel WelcomeTab = new JPanel();
		TabPage.addTab("New tab", null, WelcomeTab, null);
		
		JPanel LessonsTab = new JPanel();
		TabPage.addTab("New tab", null, LessonsTab, null);
		
		JPanel ProgramTab = new JPanel();
		TabPage.addTab("New tab", null, ProgramTab, null);
		
		JPanel ChallengesTab = new JPanel();
		TabPage.addTab("New tab", null, ChallengesTab, null);
	}
}
