package gui2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.*;

public class EditorPane extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	private int tabCount = 0;
	private JFileChooser fileChooser = new JFileChooser();
	private File tempFile;

	EditorPane()
	{
		fileChooser.setFileFilter(new FileNameExtensionFilter("LISP Files", "lisp"));
		//createTab();
		
		try {
			tempFile = new File("./robotemp.lisp");
			tempFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
// UNCOMMENT TO SHOW 'NEW TAB' TAB BUTTON
		
//	 JScrollPane paneNewTab;
//	 JTextArea txtNewTab;
		
//		paneNewTab = new JScrollPane();
//        txtNewTab = new JTextArea();
//
//        txtNewTab.setEditable(false);
//        paneNewTab.setViewportView(txtNewTab);


//        final JPanel newTab = new JPanel();
//        add(newTab, paneNewTab);
//        
//        final NewTabIcon TabIcon = new NewTabIcon();
//        final JLabel lblNew = new JLabel(TabIcon);
//        lblNew.setFocusable(false);
//	    MouseListener listener = new MouseListener() {
//		    	private NewTabIcon icon = TabIcon;
//				public void mouseClicked(MouseEvent arg0) {}
//				public void mouseEntered(MouseEvent arg0) {
//					icon.mouseEnter();
//					lblNew.repaint();
//				}
//				public void mouseExited(MouseEvent arg0) {
//					icon.mouseExit();
//					lblNew.repaint();
//				}
//				public void mouseReleased(MouseEvent arg0) {
//					createTab();
//				}
//				public void mousePressed(MouseEvent arg0) {	}
//		    };	    
//		lblNew.addMouseListener(listener);
//        setTabComponentAt(0, lblNew);
//        setEnabledAt(0, false);
//                
	}	
	
	
	public void createTab(){
		
		createTab("untitled " + tabCount, "", "");
	}
	
	private void createTab(String name, String contents, String filePath)
	{
		JLabel lblTitle = new JLabel(name);
		final EditorTab newEditor = new EditorTab(contents, filePath, lblTitle);
		
		add(newEditor, tabCount);
		
		int pos = indexOfComponent(newEditor);
		
	    JPanel pnlTab = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
	    pnlTab.setOpaque(false);


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
	
	public void openFile(){
		
		int returnVal = fileChooser.showOpenDialog(this.getParent());
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			File file = fileChooser.getSelectedFile();
			
			int openedIndex = getOpenIndex(file.getAbsolutePath());
			if(openedIndex >= 0)
			{
				this.setSelectedIndex(openedIndex);
				return;
			}
			
			try {
				
				BufferedReader reader = new BufferedReader(new FileReader(file));
		
				String temp = "";
				StringBuilder content = new StringBuilder();
				
				while((temp = reader.readLine()) != null){
					content.append(temp + "\n");
				}
				
				createTab(file.getName(), content.toString(), file.getAbsolutePath());
				
				reader.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveFile(){
		
		int returnVal = fileChooser.showSaveDialog(this.getParent());
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			writeToTemp();
			
			File file = fileChooser.getSelectedFile();
			
			try {
				
				String temp = "";
				StringBuilder content = new StringBuilder();
				BufferedReader reader = new BufferedReader(new FileReader(getTempFile()));
				while((temp = reader.readLine()) != null){
					content.append(temp + "\n");
				}
				reader.close();
				
				FileWriter writer = new FileWriter(file, false);
				writer.write(content.toString());
				writer.close();
				
				getSelectedTab().updateTabFile(file.getAbsolutePath(), file.getName());
				this.doLayout();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void writeToTemp() {
		
		BufferedWriter outFile;
		try {
			
			outFile = new BufferedWriter( new FileWriter( tempFile ) );
			getSelectedTab().getTextArea().write(outFile);
	        outFile.flush( ); 
	        outFile.close( );
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private int getOpenIndex(String path) {
		
		for(int i = 0; i < tabCount; i++)
			if(((EditorTab) this.getComponentAt(i)).getFilePath().compareTo(path) == 0)
				return i;
		
		return -1;
	}
	
	private EditorTab getSelectedTab() {
		return (EditorTab) this.getComponentAt(getSelectedIndex());
	}
	
	public File getTempFile() {
		return tempFile;
	}
}
