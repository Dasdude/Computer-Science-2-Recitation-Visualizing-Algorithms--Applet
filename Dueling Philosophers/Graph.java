import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;


public class Graph {
	BufferedReader reader;
	int totalEdges;
	int totalNodes;
	Node root;
	Vector<Node> nodes;
	boolean isMultiSolution=false;
	public Graph(String fileName) throws IOException {
		// TODO Auto-generated constructor stub
		reader = new BufferedReader(new FileReader(fileName));
		String parameter = reader.readLine();
		String[] parameters = parameter.split(" ");
		totalNodes = Integer.parseInt(parameters[0]);
		totalEdges = Integer.parseInt(parameters[1]);
		nodes = new Vector<Node>(totalNodes);
		for(int i=0;i<totalNodes;i++)
		{
			nodes.add(new Node(i));
		}
		while(true)
		{
			
			String parameter1 = reader.readLine();
			if(parameter1==null)
			{
//				System.out.println("end File");
				break;
			}
			String[] parameters1 = parameter1.split(" ");
			int nodeIndex1 = Integer.parseInt(parameters1[0])-1;
			int nodeIndex2 = Integer.parseInt(parameters1[1])-1;
//			System.out.println(nodes.get(nodeIndex2).index);
//			System.out.println(nodes.get(nodeIndex1).index);
			nodes.get(nodeIndex1).setChild(nodes.get(nodeIndex2));			
		}
				
		
	}
	public Node findParentLessNode()
	{
		int totalParentless=0;
		Node parentLess=null;
		for(int i = 0 ; i<nodes.size();i++)
		{
			if(nodes.get(i).parent.isEmpty())
			{
				parentLess= nodes.get(i);
				totalParentless++;
			}
		}
		if(totalParentless>1)
			this.isMultiSolution = true;
		return parentLess;
	}
	public void removeNode(Node parentless)
	{
		nodes.remove(parentless);
		System.out.println("Node" + (parentless.index+1)+" is removed");
		parentless.suicide();
	}
	public int processGraph()
	{
		while(nodes.size()!=0)
		{
			Node parentless = findParentLessNode();
			if(parentless==null)
			{
				return 0;
			}
			
			removeNode(parentless);
			
		}
		if(this.isMultiSolution)
			return 2;
		else
			return 1;
	}
	
	public int processGraphStep()
	{
		if(nodes.size()!=1)
		{
			Node parentless = findParentLessNode();
			if(parentless==null&&nodes.size()!=0)
			{
				System.out.println("0 - No Solution");
				return 0;
			}
			
			removeNode(parentless);
			
		}
		else{
			removeNode(nodes.get(0));
			
			if(this.isMultiSolution)
			{
				System.out.println("2 - Multiple Solution");
				return 2;
				
			}
			else
			{
				System.out.println("1 - One Solution");
				return 1;
			}
		}
		return 3;
	}
	
	public static void main(String[] args) throws IOException {
		Graph a = new Graph("a.txt");
		System.out.println(a.processGraph());
	}
}
