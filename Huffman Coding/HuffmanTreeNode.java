
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{
	public HuffmanTreeNode() {
		// TODO Auto-generated constructor stub
	}
	
	public HuffmanTreeNode(int freq,int symbol) {
		// TODO Auto-generated constructor stub
		this.freq = freq;
		this.symbol = symbol;
	}
	public HuffmanTreeNode(int freq)
	{
		this.freq = freq;
	}
	public void setLeftChild(HuffmanTreeNode node)
	{
		node.codedSymbol=false;
		this.leftChild = node;
		
		node.Parent = this;
	}
	public void setRightChild(HuffmanTreeNode node)
	{
		node.codedSymbol = true;
		this.rightChild = node;
		node.Parent = this;
	}
	HuffmanTreeNode Parent;
	HuffmanTreeNode leftChild,rightChild;
	boolean isByte;
	int freq;
	boolean codedSymbol;
	int symbol;
	@Override
	public int compareTo(HuffmanTreeNode node) {
		// TODO Auto-generated method stub
		if(this.freq>node.freq)
			return 1;
		else
			return 0;
					
	}
	
	
}
