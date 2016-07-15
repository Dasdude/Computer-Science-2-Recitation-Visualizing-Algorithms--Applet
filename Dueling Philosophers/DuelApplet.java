import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


public class DuelApplet extends Applet {
	Graph graph;
	int nodeWidth = 50;
	int nodeHeight = 50;
	int lev=1;
	int windowWidth = 1000;
	int windowHeight = 1000;
	int leftMargin = 100;
	int rightMargin = 100;
	Button readFile;
	TextField path;
	Button next;
	Button reset;
	int result;
	String filename;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		result = 3;
		reset = new Button("Reset");
		path = new TextField("Enter File Path");
		this.add(path);
		path.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				path.setText(null);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				path.setText(null);
				
			}
		});
		path.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filename = path.getText();
				System.out.println(filename);
				try {
					graph = new Graph(filename);
					repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		readFile = new Button("Get File");
		this.add(readFile);
		readFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filename = path.getText();
				System.out.println(filename);
				try {
					graph = new Graph(filename);
					repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		path.setBounds(100, 200, 100,100);
		next = new Button("next");
		next.setBounds(100, 600, 100, 100);
		next.setVisible(true);
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				result = graph.processGraphStep();
				repaint();
			}
		});
		this.add(next);
		this.setSize(windowWidth, windowHeight);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		windowHeight = this.getHeight();
		windowWidth = this.getWidth();
		if(graph!=null)
		{
			
			drawAllNodes(g);
			drawEdges(g);
			switch (result) {
			case 0:
				g.setColor(Color.cyan);
				g.fillRect( (windowWidth/10)-20, (windowHeight/10)-20, 150, 40);
				g.setColor(Color.black);
				g.drawString("No Solution", windowWidth/10,windowHeight/10 );
				break;
			case 1:
				g.setColor(Color.cyan);
				g.fillRect( (windowWidth/10)-20, (windowHeight/10)-20, 150, 40);
				g.setColor(Color.black);
				g.drawString("One Solution", windowWidth/10,windowHeight/10 );
				break;
			case 2:
				g.setColor(Color.cyan);
				g.fillRect( (windowWidth/10)-20, (windowHeight/10)-20, 150, 40);
				g.setColor(Color.black);
				g.drawString("Multiple Solutions", windowWidth/10,windowHeight/10 );
				break;
				
				
			default:
				break;
			}
		}
	}
	public void drawAllNodes(Graphics g)
	{
		for(int i = 0 ; i<graph.nodes.size();i++)
		{
			drawNode(g, graph.nodes.get(i).index);
		}
	}
	public void drawNode(Graphics g,int index)
	{
		int x = leftMargin +index*(windowWidth-leftMargin-rightMargin)/graph.totalNodes;
		g.drawRect( x, windowHeight/2, nodeWidth, nodeHeight);
		g.drawString(String.valueOf(index+1), x+nodeWidth/2, windowHeight/2+nodeHeight/2);
	}
	public int getBoxYcoordinat()
	{
		return windowHeight/2;
	}
	public int getBoxXcoordinate(int index)
	{
		return (leftMargin +index*(windowWidth-leftMargin-rightMargin)/graph.totalNodes);
	}
	public void drawEdges(Graphics g)
	{
		for(int i = 0 ; i<graph.nodes.size();i++)
		{
			int q = graph.nodes.get(i).index;
			for(int j = 0 ; j<graph.nodes.get(i).child.size();j++)
			{
				int p =graph.nodes.get(i).child.get(j).index;
				
				drawEdge(q,p,g);
				
			}
		}
	}
	public void drawEdge(int index1,int index2,Graphics g)
	{
		int height = nodeHeight*(index1-index2)+(index1+index2)*5;
		if(height<0)
		{
			g.drawLine(getBoxXcoordinate(index1)+(nodeWidth/2)+(index1-index2)*2,getBoxYcoordinat() , getBoxXcoordinate(index1)+(nodeWidth/2)+(index1-index2)*2, getBoxYcoordinat()+height);
			g.drawLine(getBoxXcoordinate(index1)+(nodeWidth/2)+(index1-index2)*2,getBoxYcoordinat()+height , getBoxXcoordinate(index2)+(nodeWidth/2)+(index1-index2)*2, getBoxYcoordinat()+height);
			g.drawLine(getBoxXcoordinate(index2)+(nodeWidth/2)+(index1-index2)*2,getBoxYcoordinat()+height , getBoxXcoordinate(index2)+(nodeWidth/2)+(index1-index2)*2, getBoxYcoordinat());
			
			if(index1>index2)
			{
				g.drawLine((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2,getBoxYcoordinat()+height+10 , ((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2)-10, getBoxYcoordinat()+height);
				g.drawLine((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2,getBoxYcoordinat()+height-10 , ((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2)-10, getBoxYcoordinat()+height);
				
			}
			else
			{
				
				g.drawLine((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2,getBoxYcoordinat()+height+10 , ((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2)+10, getBoxYcoordinat()+height);
				g.drawLine((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2,getBoxYcoordinat()+height-10 , ((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2)+10, getBoxYcoordinat()+height);
				
			}
		}
		else
		{
			
			g.drawLine(getBoxXcoordinate(index1)+(nodeWidth/2)+(index1-index2)*2,getBoxYcoordinat()+nodeHeight , getBoxXcoordinate(index1)+(nodeWidth/2)+(index1-index2)*2, getBoxYcoordinat()+nodeHeight+height);
			g.drawLine(getBoxXcoordinate(index1)+(nodeWidth/2)+(index1-index2)*2,getBoxYcoordinat()+nodeHeight+height , getBoxXcoordinate(index2)+(nodeWidth/2)+(index1-index2)*2, getBoxYcoordinat()+nodeHeight+height);
			g.drawLine(getBoxXcoordinate(index2)+(nodeWidth/2)+(index1-index2)*2,getBoxYcoordinat()+nodeHeight+height , getBoxXcoordinate(index2)+(nodeWidth/2)+(index1-index2)*2, getBoxYcoordinat()+nodeHeight);
			
			if(index1>index2)
			{
				g.drawLine((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2,getBoxYcoordinat()+height+10+nodeHeight , ((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2)-10, getBoxYcoordinat()+height+nodeHeight);
				g.drawLine((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2,getBoxYcoordinat()+height-10+nodeHeight , ((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2)-10, getBoxYcoordinat()+height+nodeHeight);
				
			}
			else
			{
				
				g.drawLine((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2,getBoxYcoordinat()+height+10 +nodeHeight, ((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2)+10, getBoxYcoordinat()+height+nodeHeight);
				g.drawLine((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2,getBoxYcoordinat()+height-10 +nodeHeight, ((getBoxXcoordinate(index1)+getBoxXcoordinate(index2))/2)+10, getBoxYcoordinat()+height+nodeHeight);
				
			}
			
		}
		
	}
}
