package gui2;

import javax.swing.tree.*;

public class ExamplesTreeModel extends DefaultTreeModel{

	private static final long serialVersionUID = 1L;

	public ExamplesTreeModel(TreeNode arg0) {
		super(arg0);
	}
	
	ExamplesTreeModel()
	{
		super(null);
		/*super(new DefaultMutableTreeNode("Robo-Teach")
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
			}});*/
		
		ExamplesNode rootNode = new ExamplesNode("Robo-Teach");
		
		ExamplesNode node_1 = new ExamplesNode("Basic Commands");
		node_1.add(new ExamplesNode("Drive", "(irobot.drive 100)"));
		node_1.add(new ExamplesNode("Stop", "(irobot.drive 0)"));
		
		ExamplesNode node_2 = new ExamplesNode("Control Structure");
		node_2.add(new ExamplesNode("Loop", "(loop <...>)"));
		node_2.add(new ExamplesNode("Loop return", "(return)"));
		node_2.add(new ExamplesNode("When", "(when <condition> <...>)"));
	
		ExamplesNode node_3 = new ExamplesNode("Extras");
		node_3.add(new ExamplesNode("LED Control", "(...)"));
		node_3.add(new ExamplesNode("Song!", "(...)"));

		rootNode.add(node_1);
		rootNode.add(node_2);
		rootNode.add(node_3);
		
		this.setRoot(rootNode);
	}
	
	public static class ExamplesNode extends DefaultMutableTreeNode {

		private static final long serialVersionUID = 1L;
		
		private String text = "";
		
		ExamplesNode(String name) {
			super(name);
			this.text = null;
		}
		
		ExamplesNode(String name, String text){
			super(name);
			this.text = text;
		}
		
		public String getText() {
			return this.text;
		}
	}
	
}
