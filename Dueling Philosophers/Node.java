import java.util.Arrays;
import java.util.Vector;


public class Node 
{
	Vector<Node> parent;
	Vector<Node> child;
	int index;
	public Node(int i) {
		// TODO Auto-generated constructor stub
		this.parent = new Vector<Node>();
		this.child = new Vector<Node>();
		this.index = i;
	}
	public Node() {
		// TODO Auto-generated constructor stub
		this.parent = new Vector<Node>();
		this.child = new Vector<Node>();
	}
	public void suicide()
	{
		for(int i =0;i<child.size();i++)
		{
			child.get(i).removeParent(this);
		}
	}
	public void setChild(Node child)
	{
		this.child.add(child);
		child.parent.add(this);
	}
	public boolean hasParent(Node parent)
	{
		if(this.parent.contains(parent))
			return true;
		else
			return false;
	}
	public void removeParent(Node parent)
	{
		this.parent.remove(parent);
	}
	
}
