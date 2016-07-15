
public class Huffman {
public static boolean[][] makeHuffmanCodes(int[] freq)
{
	HuffmanTree a = new HuffmanTree(freq);
	return a.codeTable;
}
public static void main(String[] args) {
	int[] freq = new int[5];
	freq[0]= 0;
	freq[1] = 10;
	freq[2] = 12;
	freq[3] = 13;
	freq[4] = 20;
	boolean[][] result = makeHuffmanCodes(freq);
	print(result);
	
	
	
}
public static void print(boolean[][] table)
{
	System.out.println("TABLE PRINTING");
	System.out.println("                      ");
	for(int i = 0 ; i<table.length;i++)
	{
		if(table[i]!=null)
		{
			System.out.print("entry "+ i+ " : ");
			for(int j = 0 ; j<table[i].length;j++)
			{
				if(table[i][j])
					System.out.print("1");
				else
					System.out.print("0");
			}
			System.out.println("\n");
			
		}
		else
			System.out.println("entry "+ i +" does not occur in file\n");
	}
	System.out.println("PRINT END");
}
}
