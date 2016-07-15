import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;


public class HuffApplet extends Applet {
	HuffmanTree tree;
	int sizex = 1000;
	int sizey = 1000;
	int nodeWidth=50;
	int nodeHeight=50;
@Override
	public void init() 
	{
		super.init();
		this.setSize(sizex, sizey);
		int[] freq = new int[11];
		freq[0]= 0;
		freq[1] = 10;
		freq[2] = 12;
		freq[3] = 13;
		freq[4] = 20;
		freq[5] = 40;
		freq[6] = 50;
		freq[7] = 60;
		// your HuffmanTree object should have a field called "root" which is a HuffmanTreeNode Object. root is the root node of your tree
		// in addition to that for this applet to work without error, HuffmanTreeNode class should also have field "leftChild" and "rightChild" 
		// Note that this applet only shows the HuffmanTree and not the table. so you cannot test correctness of your result table by this applet.
		
		tree= new HuffmanTree(freq);
		
	}
@Override
	public void paint(Graphics g) 
	{
		// TODO Auto-generated method stub
		super.paint(g);
		sizex = this.getSize().width;
		sizey = this.getSize().height;
		treePaint(g);
		paintLegend(g);
		
	}
	public void paintLegend(Graphics g)
	{
		g.drawRect(1, 1, sizex/8+40, 2*nodeHeight+15);
		g.drawString("legend", sizex/128, nodeHeight/4+5);
		g.setColor(Color.red);
		g.drawString("Symbol Color",sizex/128 ,nodeHeight/4 + 2*nodeHeight/3);
		g.fillRect(sizex/8,nodeHeight/4 + 2*nodeHeight/3 -15,30	, 30);
		g.setColor(Color.GREEN);
		g.drawString("Frequency Color",sizex/128 ,nodeHeight/4 + 3*(nodeHeight/2));
		g.fillRect(sizex/8,nodeHeight/4 + 3*(nodeHeight/2)-15,30	, 30);
	}
	public void treePaint(Graphics g)
	{
		treePaint(tree.root, g, sizex/2, 10,sizex/2);
	}
	public void treePaint(HuffmanTreeNode root,Graphics g,int x,int y,int dx)
	{
		
		g.drawRect(x, y, nodeWidth	, nodeHeight);
		if(root.Parent!=null)
		g.drawString(String.valueOf(root.codedSymbol), (x+nodeWidth/2)-10, y+nodeHeight/2);
		if(root.leftChild!=null)
		{
			g.drawLine(x+nodeWidth/2, y+nodeHeight, x-dx/2+nodeWidth/2,y+nodeHeight);
			g.drawLine( x-dx/2+nodeWidth/2,y+nodeHeight, x-dx/2+nodeWidth/2,y+2*nodeHeight);
			g.drawLine(x+nodeWidth/2, y+nodeHeight, x+dx/2+nodeWidth/2,y+nodeHeight);
			g.drawLine( x+dx/2+nodeWidth/2,y+nodeHeight, x+dx/2+nodeWidth/2,y+2*nodeHeight);
			treePaint(root.leftChild,g,x-dx/2,y+2*nodeHeight,dx/2);
			treePaint(root.rightChild,g,x+dx/2,y+2*nodeHeight,dx/2);
			
			
		}
		else
			{
				g.setColor(Color.red);
				g.drawString(String.valueOf(root.symbol), x, y-10);
				g.setColor(Color.green);
				g.drawString(String.valueOf(root.freq), x+10+nodeWidth, y+nodeHeight/2);
				g.setColor(Color.black);
				String a = new String();
				for(int i = 0 ;i<tree.codeTable[root.symbol].length;i++)
				{
					if(tree.codeTable[root.symbol][i])
						a = a+"1";
					else
						a = a+"0";
				}
				g.drawString(a, x, y+nodeHeight+20);
			}
	}
	
}

