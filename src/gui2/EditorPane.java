package gui2;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class EditorPane extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	private JScrollPane paneNewTab;
	private JTextComponent txtNewTab;
	private int tabCount = 0;

	EditorPane()
	{
		paneNewTab = new javax.swing.JScrollPane();
        txtNewTab = new javax.swing.JTextArea();

        txtNewTab.setEditable(false);
        paneNewTab.setViewportView(txtNewTab);

        addTab("+", paneNewTab);
        createTab();
	}	
	
	
	private void createTab()
	{
		
		final Editor newEditor = new Editor(new JTextArea("New Tab" + tabCount));
		
		add(newEditor, tabCount);
		int pos = indexOfComponent(newEditor);
		
		FlowLayout f = new FlowLayout(FlowLayout.CENTER, 5, 0);
	    JPanel pnlTab = new JPanel(f);
	    pnlTab.setOpaque(false);

	    JLabel lblTitle = new JLabel("New Tab");

	    JLabel lblClose = new JLabel("x");
	    lblClose.setToolTipText("Close tab");
	    lblClose.setFocusable(false);
	    
	    pnlTab.add(lblTitle);
	    pnlTab.add(lblClose);
	    
	    pnlTab.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

	    setTabComponentAt(pos, pnlTab);
	    
	    MouseListener listener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				((JLabel) arg0.getSource()).setText("X");
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				((JLabel) arg0.getSource()).setText("x");
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				remove(newEditor);
				
			}
	    };
	    lblClose.addMouseListener(listener);
	    
	    setSelectedComponent(newEditor);
	    
		tabCount++;
	}
	
}
