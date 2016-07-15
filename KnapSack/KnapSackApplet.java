import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Scanner;


public class KnapSackApplet extends Applet implements MouseListener,KeyListener,Runnable {
	boolean pause=true;
	int itemsTotal;
	int money;
	int[] price;
	int[] calorie;
	 Thread knapSackSolver;
	int[][] TableValues;
	int[][] knap;
	int pauseTime=500;
	int rowPointer=0, colPointer=0;
	boolean flagNextStep=false;
	//Table Information
	boolean flagDrawTable=false;
	int cellWidth=100,cellHeight=75;
	int leftMargin=4*cellWidth,topMargin=200;
	int leftMarginText = cellWidth/2;
	int topMarginText = cellHeight/2;
	boolean flagpause=false;
	int buttonWidth = 150;
	int buttonHeight = 30;
	TextField textCandyItem;
	TextField textMoney;
	TextField textSpeed;
	Container containerItemPrice;
	TextField[][] textItemPriceCal;
	Button buttonGetPriceCal;
	Button buttonAssignPriceCal;
	Button buttonStartKnapsack;
	Button buttonPauseKnapsack;
	Button buttonSetSpeed;
	Button buttonNextStep;
	Checkbox checkboxNextStep;
	
	TextArea textAreaPrice;
	TextArea textAreaCalorie;
	
	
	
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Main ks = new Main();
//		int[] price = {7, 3 ,1} ;
//		int[] calorie = {700,299,499};
//		int money = 8;
//		System.out.println(ks.knapsack01(price,calorie,money));
//	}
	public void clearApplet()
	{
		this.removeAll();
		
	}
	public int[] cellLocation(int row , int col)
	{
		int[] result= {0,0};
		result[0]=leftMargin+cellWidth*col;
		result[1]=topMargin+cellHeight*row;
		return result;
	}
	public void drawint(Graphics g,int value,int row,int col)
	{
		int[] location = cellLocation(row, col);
		
		g.drawString(Integer.toString(value), location[0]+leftMarginText, location[1]+topMarginText);
	}
	public void showTable(Graphics g)
	{
		for(int i = 0 ; i<this.itemsTotal+1;i++)
		{
			g.drawRect(0, topMargin+i*cellHeight, cellWidth	, cellHeight);
			g.drawRect(cellWidth,topMargin+i*cellHeight,cellWidth,cellHeight);
			g.drawRect(2*cellWidth,topMargin+i*cellHeight,cellWidth,cellHeight);
			
			if(i==0)
			{
				g.drawString("item Number", 0+10, topMargin+cellHeight/2);
				g.drawString("Price", 0+10+cellWidth, topMargin+cellHeight/2);
				g.drawString("Calorie", 0+10+2*cellWidth, topMargin+cellHeight/2);
				
			}
			else
			{
				g.drawString(Integer.toString(i), 0+10, topMargin+cellHeight/2+i*cellHeight);
				g.drawString(Integer.toString(price[i-1]), 0+10+cellWidth, topMargin+cellHeight/2+i*cellHeight);
				g.drawString(Integer.toString(calorie[i-1]), 0+10+2*cellWidth, topMargin+cellHeight/2+i*cellHeight);
			}
			
		}
		if(flagDrawTable)
		{
			
			buttonStartKnapsack.setVisible(true);
			buttonPauseKnapsack.setVisible(true);
			buttonSetSpeed.setVisible(true);
//			int tableWidth = cellWidth*(money+2);
//			int tableHeight = cellHeight*(itemsTotal+2);
//			g.drawRect(leftMargin, topMargin, tableWidth, tableHeight);
			int cellLocationx ; 
			int cellLocationy;
			for(int i =0 ; i< this.itemsTotal+2;i++)
			{
				for(int j = 0 ; j<this.money+2;j++)
				{
					cellLocationx = leftMargin+cellWidth*j; 
					cellLocationy = topMargin+cellHeight*i;
					g.drawRect(cellLocationx, cellLocationy, cellWidth, cellHeight);
					if(i==0&&j!=0)
						drawint(g, j-1, i, j);
					if(j==0&&i!=0)
						drawint(g, i-1, i, j);
						
				}
			}
		}
	}
	public void createPriceCalTextField()
	{
		System.out.println(itemsTotal);
		textItemPriceCal = new TextField[itemsTotal][2];
		textCandyItem.setVisible(false);
		textMoney.setVisible(false);
		buttonGetPriceCal.setVisible(false);
		buttonAssignPriceCal = new Button("assign Values and Run");
		buttonAssignPriceCal.setSize(200, 20);
		buttonAssignPriceCal.setLocation(500, 100);
		buttonAssignPriceCal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				price = new int[itemsTotal];
				calorie = new int[itemsTotal];
				TableValues = new int[itemsTotal+1][money+1];
				for(int i =0 ; i<itemsTotal;i++)
				{
					price[i] = Integer.parseInt(textItemPriceCal[i][0].getText());
					calorie[i] = Integer.parseInt(textItemPriceCal[i][1].getText());
				}
				removeAll();
				flagDrawTable=true;
				knap = new int[itemsTotal+1][money+1];
				repaint();
				showThirdPage();
//				knapsack01(price, calorie, money);
				
			}
		});
		textAreaPrice = new TextArea("Price");
		textAreaCalorie = new TextArea("Calorie");
		textAreaPrice.setSize(100, 20);
		textAreaCalorie.setSize(100, 20);
		textAreaCalorie.setLocation(250, 75);
		textAreaPrice.setLocation(100, 75);
		this.add(textAreaCalorie);
		this.add(textAreaPrice);
		for(int i = 0;i<itemsTotal;i++)
		{
			textItemPriceCal[i][0] = new TextField();
			textItemPriceCal[i][1] = new TextField();
			textItemPriceCal[i][0].setSize(100, 20);
			textItemPriceCal[i][1].setSize(100, 20);
			textItemPriceCal[i][0].setLocation(100, 100+25*i);
			textItemPriceCal[i][1].setLocation(250, 100+25*i);
			this.add(textItemPriceCal[i][0]);
			this.add(textItemPriceCal[i][1]);
//			containerItemPrice.add(textItemPriceCal[i][0], i);
//			containerItemPrice.add(textItemPriceCal[i][1]);
		}
		this.add(buttonAssignPriceCal);
//		repaint();
//		containerItemPrice.validate();
//		containerItemPrice.repaint();
		
	}
	public void createThread()
	{
		knapSackSolver = new Thread(this);
		knapSackSolver.start();
		buttonStartKnapsack.setEnabled(false);
	}
	public void showThirdPage()
	{
		buttonStartKnapsack = new Button("Start");
		buttonStartKnapsack.setSize(buttonWidth	, buttonHeight);
		buttonStartKnapsack.setLocation(0, 0);
		buttonStartKnapsack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buttonPauseKnapsack.setEnabled(true);
				createThread();
				
			}
		});
		this.add(buttonStartKnapsack);
//		buttonStartKnapsack.setVisible(false);
		buttonPauseKnapsack = new Button("Pause");
		buttonPauseKnapsack.setSize(buttonWidth	, buttonHeight);
		buttonPauseKnapsack.setLocation(buttonStartKnapsack.getX()+buttonStartKnapsack.getWidth(), buttonStartKnapsack.getY());
		buttonPauseKnapsack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flagpause = !flagpause;
				if(flagpause)
				buttonPauseKnapsack.setLabel("Resume");
				else
					buttonPauseKnapsack.setLabel("Pause");
					
			}
		});
//		buttonPauseKnapsack.setVisible(false);
		this.add(buttonPauseKnapsack);
		textSpeed = new TextField(5);
		textSpeed.setSize(buttonWidth, buttonHeight);
		textSpeed.setLocation(buttonPauseKnapsack.getX()+buttonPauseKnapsack.getWidth()+100, buttonPauseKnapsack.getY());
		this.add(textSpeed);
		buttonSetSpeed = new Button("Set Thread Pause Time");
		buttonSetSpeed.setSize(buttonWidth	, buttonHeight);
		buttonSetSpeed.setLocation(textSpeed.getX()+textSpeed.getWidth(), textSpeed.getY());
		buttonSetSpeed.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String temp = textSpeed.getText();
				if(temp!=null)
				pauseTime=Integer.parseInt(textSpeed.getText());
			}
		});
		
//		buttonSetSpeed.setVisible(false);
		this.add(buttonSetSpeed);
		buttonNextStep = new Button("Next Step");
		buttonNextStep.setSize(buttonWidth, buttonHeight);
		buttonNextStep.setLocation(buttonSetSpeed.getX()+buttonSetSpeed.getWidth(), buttonSetSpeed.getY());
		buttonNextStep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flagNextStep = true;
			}
		});
		this.add(buttonNextStep);
		checkboxNextStep = new Checkbox("Step Calculation");
		checkboxNextStep.setState(true);
		checkboxNextStep.setLocation(buttonNextStep.getX()+buttonNextStep.getWidth(), buttonNextStep.getY());
		this.add(checkboxNextStep);
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		setSize(1000,1000);
		textCandyItem = new TextField("Total Candy");
		textMoney = new TextField("Total Money");
		buttonGetPriceCal = new Button("insert Price/Cal");

		buttonGetPriceCal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				itemsTotal = Integer.parseInt((textCandyItem.getText()));
				money = Integer.parseInt((textMoney.getText()));
				createPriceCalTextField();
			}
		});
		
		this.add(buttonGetPriceCal);
		this.add(textCandyItem);
		this.add(textMoney);
		
		textCandyItem.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				textCandyItem.setText("");
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		textMoney.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				textMoney.setText("");
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		containerItemPrice = new Container();
		containerItemPrice.setVisible(true);
		containerItemPrice.setSize(500, 500);
		this.add(containerItemPrice);
	}
	public void refreshTable(Graphics g)
	{
		for(int i = 0 ; i<rowPointer;i++)
		{
			for(int j = 0 ; j<= this.money;j++)
			{
				drawint(g, knap[i][j], i+1, j+1);
				if(i==rowPointer&&j==colPointer)
					break;
			}
		}
		for(int i = 0 ; i<=colPointer;i++)
		{
			drawint(g, knap[rowPointer][i], rowPointer+1, i+1);
			
		}
		
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		if(this.flagDrawTable)
		{
			
			showTable(g);
			refreshTable(g);
		}
		
	
		
	}
	
	public  int knapsack(int[] price, int[] calorie, int money) {
		   // instantiate knap the dynamic programming matrix
			
		   knap = new int[price.length+1][money+1];
		   // initial row  - solutions to the subproblems with no items selected
		   for (int i = 0; i <= money; i++) 
		   {
			   knap[0][i] = 0;
		   }
		   for(int i = 0 ; i<itemsTotal;i++)
		   {
			   knap[i][0]=0;
		   }
		   for (int i = 0; i < money; i++) 
		   {
			   knap[0][i] = 0;
		   }
		   // process one item at price time for each weight
		   for (int item = 1; item <= price.length; item++)
		   {
			   int MoneyWeHave=0;
			   rowPointer = item;
			   for ( MoneyWeHave = 0; MoneyWeHave <= money; MoneyWeHave++) 
			   {
				   colPointer = MoneyWeHave;
				  
		            if (MoneyWeHave < price[item-1]) 
		                knap[item][MoneyWeHave] = knap[item-1][MoneyWeHave];
		            else
		            {
		            		knap[item][MoneyWeHave] = Math.max(knap[item-1][MoneyWeHave], knap[item][MoneyWeHave-price[item-1]] + calorie[item-1]);
		            }
		          repaint();
		          while(flagpause&&!checkboxNextStep.getState())
				   {
				   }
				   if(checkboxNextStep.getState())
				   {
					   while(!flagNextStep){
						   System.out.println(flagNextStep);
					   }
					   flagNextStep=false;
				   }
		          if(!checkboxNextStep.getState())
		          {
		        	  
		        	  try {
		        		  Thread.currentThread().sleep(pauseTime);
		        	  } catch (InterruptedException e) {
		        		  // TODO Auto-generated catch block
		        		  e.printStackTrace();
		        	  }
		          }
			   }
		   }
		   return knap[price.length][money];
		}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		knapsack(this.price, this.calorie, this.money);
		buttonPauseKnapsack.setEnabled(false);
		buttonStartKnapsack.setEnabled(true);
	}

}
