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

        final JPanel newTab = new JPanel();
        add(newTab, paneNewTab);
        
        final NewTabIcon TabIcon = new NewTabIcon();
        final JLabel lblNew = new JLabel(TabIcon);
        lblNew.setFocusable(false);
	    MouseListener listener = new MouseListener() {
		    	private NewTabIcon icon = TabIcon;
				public void mouseClicked(MouseEvent arg0) {}
				public void mouseEntered(MouseEvent arg0) {
					icon.mouseEnter();
					lblNew.repaint();
				}
				public void mouseExited(MouseEvent arg0) {
					icon.mouseExit();
					lblNew.repaint();
				}
				public void mouseReleased(MouseEvent arg0) {
					createTab();
				}
				public void mousePressed(MouseEvent arg0) {	}
		    };	    
		lblNew.addMouseListener(listener);
        setTabComponentAt(0, lblNew);
        setEnabledAt(0, false);
                
        createTab();
        createTab();
	}	
	
	
	public void createTab(){
		
		createTab("untitled " + tabCount, "");
	}
	
	private void createTab(String name, String contents)
	{
		
		final EditorTab newEditor = new EditorTab(new JTextArea(contents));
		
		add(newEditor, tabCount);
		
		int pos = indexOfComponent(newEditor);
		
	    JPanel pnlTab = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
	    pnlTab.setOpaque(false);

	    JLabel lblTitle = new JLabel(name);

	    final CloseIcon newCloseIcon = new CloseIcon();
	    final JLabel lblClose = new JLabel(newCloseIcon);
	    lblClose.setFocusable(false);
	    MouseListener listener = new MouseListener() {
		    	private CloseIcon icon = newCloseIcon;
				public void mouseClicked(MouseEvent arg0) {}
				public void mouseEntered(MouseEvent arg0) {
					icon.mouseEnter();
					lblClose.repaint();
				}
				public void mouseExited(MouseEvent arg0) {
					icon.mouseExit();
					lblClose.repaint();
				}
				public void mouseReleased(MouseEvent arg0) {
					remove(newEditor);
					tabCount--;
				}
				public void mousePressed(MouseEvent arg0) {	}
		    };	    
	    lblClose.addMouseListener(listener);
	    
	    pnlTab.add(lblTitle);
	    pnlTab.add(lblClose);
	    //pnlTab.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

	    setTabComponentAt(pos, pnlTab);
	    setSelectedComponent(newEditor);
	    
		tabCount++;

	}	
}
