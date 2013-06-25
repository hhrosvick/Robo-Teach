package gui2;

import gui2.ExamplesTreeModel.ExamplesNode;

import java.awt.datatransfer.*;

import javax.swing.*;

public class ExamplesTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 1L;
	
	public int getSourceActions(JComponent c) {
		return COPY;
	}

	protected Transferable createTransferable(JComponent c) {
	   
		JTree tree = (JTree) c;
		ExamplesNode node = (ExamplesNode) tree.getSelectionPath().getLastPathComponent();
	
		return new StringSelection(node.getText());
		
	}
}
