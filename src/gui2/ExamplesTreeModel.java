package gui2;

import javax.swing.tree.*;

public class ExamplesTreeModel extends DefaultTreeModel{

	private static final long serialVersionUID = 1L;

	public ExamplesTreeModel(TreeNode arg0) {
		super(arg0);
	}
	
	ExamplesTreeModel()
	{
		super(new DefaultMutableTreeNode("Robo-Teach")
			{
				private static final long serialVersionUID = 1L;

			{	DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("Basic Commands");
				node_1.add(new DefaultMutableTreeNode("Drive"));
				node_1.add(new DefaultMutableTreeNode("Stop"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Control Structure");
				node_1.add(new DefaultMutableTreeNode("Loop"));
				node_1.add(new DefaultMutableTreeNode("If - Then"));
				node_1.add(new DefaultMutableTreeNode("If - Then - Else"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Extras");
				node_1.add(new DefaultMutableTreeNode("LED Control"));
				node_1.add(new DefaultMutableTreeNode("Song!"));
				add(node_1);
			}});
	}
}
