import java.util.Comparator;
import java.util.Vector;

import org.omg.CORBA.FREE_MEM;


public class HuffmanTree {
//	HuffmanTreeNode[] leaves;
BinaryHeap heap;
HuffmanTreeNode root;
boolean codeTable[][];
int tableSize;
int iterationDepth;
boolean codingReference[];
public HuffmanTree(int[] freq) {
	// TODO Auto-generated constructor stub
	iterationDepth = -1;
	heap = new BinaryHeap();
	tableSize=0;
	for ( int i = 0 ; i<freq.length;i++)
	{
		if(freq[i]!=0)
		{
			HuffmanTreeNode a = new HuffmanTreeNode(freq[i], i);
			heap.insert(a);
		}
	}
	codeTable = new boolean[freq.length][];
	codingReference = new boolean[8];
	constructHuffmanTree();
}
private void constructHuffmanTree()
{
	while(heap.getLenght()!=1)
	{
		
		HuffmanTreeNode node1 = heap.Extract(0);
		HuffmanTreeNode node2 = heap.Extract(0);
		HuffmanTreeNode mergeNode = new HuffmanTreeNode(node1.freq+node2.freq);
		mergeNode.setLeftChild(node1);
		mergeNode.setRightChild(node2);
//		System.out.println(node1.symbol);
//		System.out.println(node2.symbol);
//		System.out.println();
		heap.insert(mergeNode);
	}
	root = heap.Extract(0);
	setTable();
}
private void setTable(HuffmanTreeNode node)
{
	if(iterationDepth>=0)
		{
			codingReference[iterationDepth]=node.codedSymbol;
			
		}
	if(node.rightChild!=null)
	{
		iterationDepth++;
		setTable(node.leftChild);
		setTable(node.rightChild);
		iterationDepth--;
		
	}
	else
	{
		codeTable[node.symbol] = new boolean[iterationDepth+1];
		for(int i = 0 ; i<=iterationDepth;i++)
		{
			codeTable[node.symbol][i]=this.codingReference[i];
		}
	}
}
public void setTable()
{
	setTable(root);
}
{
	
}
}
