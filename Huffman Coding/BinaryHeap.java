import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;


public class BinaryHeap {
	HuffmanTreeNode[] Heap;
	int Pointer;
	public BinaryHeap() {
		// TODO Auto-generated constructor stub
		Heap = new HuffmanTreeNode[300];
		Pointer = -1 ;
	}
	public int getLenght()
	{
		return Pointer+1;
	}
	public int getParentIndex(int childIndex)
	{
		return childIndex/2;
	}
	public HuffmanTreeNode getParent(int childIndex)
	{
		return Heap[getParentIndex(childIndex)];
	}
	public int getLeftChildIndex(int parentIndex)
	{
		return (2*(parentIndex+1))-1;
	}
	public int getRightChildIndex(int parentIndex)
	{
		return (2*(parentIndex+1));
	}
	private void swap(int index1 , int index2)
	{
		HuffmanTreeNode temp = Heap[index1];
		Heap[index1] = Heap[index2];
		Heap[index2] = temp;
	}
	public void insert(int weight)
	{
		HuffmanTreeNode a = new HuffmanTreeNode(weight);
		this.insert(a);
	}
	public void siftUp(int index)
	{
		if(index!=0)
		{
			if(Heap[index].compareTo(getParent(index))==0)
			{
				swap(index, getParentIndex(index));
				siftUp(getParentIndex(index));
			}
		}
	}
	public void heapify(int index)
	{
		if(getRightChildIndex(index)<=Pointer)
		{
			if(Heap[getRightChildIndex(index)].compareTo(Heap[index]) + Heap[getRightChildIndex(index)].compareTo(Heap[getLeftChildIndex(index)])==0)
				{
				swap(getRightChildIndex(index), index);
				heapify(getRightChildIndex(index));
				}
			else
			{
				if(Heap[getLeftChildIndex(index)].compareTo(Heap[index]) + Heap[getLeftChildIndex(index)].compareTo(Heap[getRightChildIndex(index)])==0)
				{
					swap(getLeftChildIndex(index), index);
					heapify(getLeftChildIndex(index));
				}
				else
					return;
				
			}
			
		}
		else
		{
			if(getLeftChildIndex(index)<=Pointer)
			{
				if(Heap[getLeftChildIndex(index)].compareTo(Heap[index]) ==0)
				{
					swap(getLeftChildIndex(index), index);
					heapify(getLeftChildIndex(index));
				}
				else
					return;
			}
			else
				return;
		}
	}
	public HuffmanTreeNode Extract(int index)
	{
		swap(index, Pointer);
		HuffmanTreeNode result = Heap[Pointer];
		Heap[Pointer]= null;
		Pointer--;
		heapify(index);
		
		return result;
	}
	public void insert(HuffmanTreeNode insNode)
	{
		Pointer++;
		Heap[Pointer] = insNode;
		siftUp(Pointer);
	}
	public void printHeap()
	{
		for(int i = 0 ; i<=Pointer;i++)
		{
			System.out.println("Current Node Freq :   " +Heap[i].freq);
			if(getLeftChildIndex(i)<=Pointer)
			{
				System.out.println("LeftChild Freq:    " + Heap[getLeftChildIndex(i)].freq);
			}
			if(getRightChildIndex(i)<=Pointer)
			{
				System.out.println("RightChild Freq:    " + Heap[getRightChildIndex(i)].freq);
			}
			
			
		}
		System.out.println("END OF TREEE          ");
		System.out.println("");
	}
	public static void main(String[] args) {
		
		System.out.println("hello");
		BinaryHeap a = new BinaryHeap();
		a.insert(50);
		a.insert(10);
		a.insert(20);
		a.insert(40);
		a.insert(50);
		a.insert(90);
		a.insert(100);
		a.printHeap();
		a.Extract(0);
		a.printHeap();
	}
	
}
