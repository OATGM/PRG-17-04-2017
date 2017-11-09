import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding.Style;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;

public class mainDraw extends JComponent implements MouseListener{

    //BASE
    	BufferedImage imgSnakeHead = null;
    	BufferedImage imgSnakeHead2 = null;
    	BufferedImage imgSnakeBody = null;
    	BufferedImage imgSnakeBody2 = null;
    	BufferedImage imgSnakeBody3 = null;
    	BufferedImage imgApple = null;
    	BufferedImage imgBanana = null;
    	BufferedImage imgPoison = null;
    	long startTime = System.currentTimeMillis();
    	boolean switchToMenu = false;
    	//MOUSE
    		int MouseX = 0;
    		int MouseY = 0;
    		boolean MouseClicked = false;
    	//MOUSE
    	boolean graphicsHigh = false;
    	boolean keyPressed = false;
    	boolean LOST = false;
    	boolean WON = false;
    	long lastTimeTicked = 0;
    	long tickUpdateInterval = 200;
    	int foodSpawnProbability = 20;
    //BASE
    		
    //SnakeGame
    int upperLineHeight = 40;
    int gridSizeX = 4;
    int gridSizeY = 4;
    int snakeDirection = 1; //UP,RIGHT,DOWN,LEFT
    int[][] map = new int[gridSizeX][gridSizeY];
    int snakeHeadX = gridSizeX/2;
    int snakeHeadY = gridSizeY/2;
    int snakeSize = 5;
    //SnakeGame
    
    //Options
    boolean AutoMove = false;
    boolean GoThroughWalls = false;
    //Options
    
    //FONTS
    Font basicFont = new Font("Calibri", Font.PLAIN , 20);
    Font lostFont = new Font("Calibri", Font.PLAIN, 40);
    //FONTS
    		
    public void setup(){
    	addMouseListener(this);
    	map[snakeHeadX][snakeHeadY] = snakeSize;
    	//PICTURES
    	try { //Tries to load picture
			imgSnakeHead = ImageIO.read(new File("bin/snakeHead.png"));
			System.out.println("Sehr Loaded");
		} catch (Exception e) {
			System.out.println("Sehr Not Loaded");
		}
    	try { //Tries to load picture
			imgSnakeHead2 = ImageIO.read(new File("bin/snakeHead2.png"));
			System.out.println("Sehr Loaded");
		} catch (Exception e) {
			System.out.println("Sehr Not Loaded");
		}
    	try { //Tries to load picture
			imgSnakeBody = ImageIO.read(new File("bin/snakeBody.png"));
			System.out.println("Sehr Loaded");
		} catch (Exception e) {
			System.out.println("Sehr Not Loaded");
		}
    	try { //Tries to load picture
			imgSnakeBody2 = ImageIO.read(new File("bin/snakeBody2.png"));
			System.out.println("Sehr Loaded");
		} catch (Exception e) {
			System.out.println("Sehr Not Loaded");
		}
    	try { //Tries to load picture
			imgSnakeBody3 = ImageIO.read(new File("bin/snakeBody3.png"));
			System.out.println("Sehr Loaded");
		} catch (Exception e) {
			System.out.println("Sehr Not Loaded");
		}
    	try { //Tries to load picture
			imgApple = ImageIO.read(new File("bin/apple.png"));
			System.out.println("Sehr Loaded");
		} catch (Exception e) {
			System.out.println("Sehr Not Loaded");
		}
    	try { //Tries to load picture
			imgBanana = ImageIO.read(new File("bin/banana.png"));
			System.out.println("Sehr Loaded");
		} catch (Exception e) {
			System.out.println("Sehr Not Loaded");
		}
    	try { //Tries to load picture
			imgPoison = ImageIO.read(new File("bin/poison.png"));
			System.out.println("Sehr Loaded");
		} catch (Exception e) {
			System.out.println("Sehr Not Loaded");
		}
    	//PICTURES
    	map[1][1] = -10;
 		repaint();
    }
    
    public void paintComponent(Graphics g) { //RUNABLE EXECUTED PAINTING
        super.paintComponent(g);
        
        //UPPERLINE ------------------------------------------------
        //drawLine
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), upperLineHeight);
        //drawLine
        //menubutton
        g.setFont(basicFont);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 150, upperLineHeight);
        g.setColor(Color.white);
        g.drawString("Menu", 10, 25);
        //menubutton
        //timer
        g.setColor(Color.BLACK);
        g.drawString("Time " + (Integer.toString((int) (System.currentTimeMillis()-startTime)/1000)), 160, 25);
        //timer
        //pointsCounter
        g.drawString("Points " + snakeSize, 240, 25);
        //pointsCounter
        //UPPERLINE -------------------------------------------------
        
        if(hasTicked())
        {
        	//LOWER MAP VARIABLES
            for (int i = 0; i < gridSizeX; i++) {
    			for (int j = 0; j < gridSizeY; j++) {
    				if(map[i][j] > 0)map[i][j]--; //If snake lower snake
    				if(map[i][j] < 0){map[i][j]++;if(map[i][j]%100==0)map[i][j] = 0;} //If food lower food
    			}
    		}
            //LOWER MAP VARIABLES
        	
        	 //SNAKE HEAD MOVE
        	switch (snakeDirection) {
			case 1:
				snakeHeadY--;
				break;
			case 2:
				snakeHeadX++;
				break;
			case 3:
				snakeHeadY++;
				break;
			case 4:
				snakeHeadX--;
				break;
			default:
				System.out.println("Snake Direction ERROR");
				break;
			}
            //SNAKE HEAD MOVE
        	
        	//CHECK IF HEAD NOT OUT OF BOUNDS
        	if(GoThroughWalls)
        	{
        		if(snakeHeadX >= gridSizeX)
        		{
        			snakeHeadX = 0;
        		}
        		if(snakeHeadX < 0)
        		{
        			snakeHeadX = gridSizeX-1;
        		}
        		if(snakeHeadY >= gridSizeY)
        		{
        			snakeHeadY = 0;
        		}
        		if(snakeHeadY < 0)
        		{
        			snakeHeadY = gridSizeY-1;
        		}
        	}
        	else
        	{
        		if(snakeHeadX >= gridSizeX)LOST = true;
        		if(snakeHeadX < 0)LOST = true;
        		if(snakeHeadY >= gridSizeY)LOST = true;
        		if(snakeHeadY < 0)LOST = true;
        	}
        	//CHECK IF HEAD NOT OUT OF BOUNDS
        	
        	//FOOD CRASH INDICATOR
        	if(!LOST)
        	{
        		if(map[snakeHeadX][snakeHeadY] < 0) //FOOD
        		{
        			eatFood(map[snakeHeadX][snakeHeadY]);
        		}
        		else if(map[snakeHeadX][snakeHeadY] > 0) //SNAKE
        		{
        			LOST = true;
        		}
        	}
        	//FOOD CRASH INDICATOR
        	
        	//SNAKE HEAD WRITE
            if(!LOST)map[snakeHeadX][snakeHeadY] = snakeSize;
            //SNAKE HEAD WRITE¨
            
            //SPAWN FOOD
            if(true)
            {
            	Random random = new Random();
            	if(random.nextInt(foodSpawnProbability) == 0)
            	{
            		while(true)
            		{
            			int randomX = random.nextInt(gridSizeX);
            			int randomY = random.nextInt(gridSizeY);
            			if(map[randomX][randomY] == 0)
            			{
            				int foodRandomType = random.nextInt(3)+1;
            				switch (foodRandomType) {
							case 1:
								map[randomX][randomY] = -random.nextInt(20)-10;
								break;
							case 2:
								map[randomX][randomY] = -random.nextInt(20)-10-100;
								break;
							case 3:
								map[randomX][randomY] = -random.nextInt(20)-10-200;
								break;

							default:
								System.out.println("Uknown Food");
								break;
							}
            				break;
            			}
            		}
        		}
            }
            //SPAWN FOOD
        }
        
        
        //DRAWSHIT (Low graphics)
        if(!graphicsHigh)
        {
        	for (int i = 0; i < gridSizeX; i++) {
        		for (int j = 0; j < gridSizeY; j++) {
					if(map[i][j] > 0) g.fillRect((i*getWidth())/gridSizeX, (j*(getHeight()-40))/gridSizeY + 40, (getWidth()/gridSizeX)+1, (getHeight()-40)/gridSizeY+1);
					if(map[i][j] < 0)
					{
						if(map[i][j] > -100)
						{
							g.setColor(Color.RED);
						}
						else if(map[i][j] > -200)
						{
							g.setColor(Color.YELLOW);
						}
						else if(map[i][j] > -300)
						{
							g.setColor(Color.DARK_GRAY);
						}
						if(map[i][j]%100>-6)
						{
							if(System.currentTimeMillis()%200<100)
							{
								g.fillRect((i*getWidth())/gridSizeX, (j*(getHeight()-40))/gridSizeY + 40, (getWidth()/gridSizeX)+1, (getHeight()-40)/gridSizeY+1);
							}
						}
						else
						{
							g.fillRect((i*getWidth())/gridSizeX, (j*(getHeight()-40))/gridSizeY + 40, (getWidth()/gridSizeX)+1, (getHeight()-40)/gridSizeY+1);
						}
						g.setColor(Color.BLACK);
					}
				}
        	}
        }
        //DRAWSHIT
        
        //DRAWSHIT (High Graphics)
        if(graphicsHigh)
        {
        	for (int i = 0; i < gridSizeX; i++) {
        		for (int j = 0; j < gridSizeY; j++) {
        			if(map[i][j] == snakeSize)
        			{
        				switch (snakeDirection) {
						case 1:
							g.drawImage(imgSnakeHead2,(i*getWidth())/gridSizeX, (j*(getHeight()-40))/gridSizeY + 40, (getWidth()/gridSizeX)+1, (getHeight()-40)/gridSizeY+1, this);
							break;
						case 2:
							g.drawImage(imgSnakeHead,(i*getWidth())/gridSizeX+(getWidth()/gridSizeX), (j*(getHeight()-40))/gridSizeY + 40, -(getWidth()/gridSizeX)+1, (getHeight()-40)/gridSizeY+1, this);
							break;
						case 3:
							g.drawImage(imgSnakeHead2,(i*getWidth())/gridSizeX, (j*(getHeight()-40))/gridSizeY + 40+(getHeight()-40)/gridSizeY+1, (getWidth()/gridSizeX)+1, -(getHeight()-40)/gridSizeY+1, this);
							break;
						case 4:
							g.drawImage(imgSnakeHead,(i*getWidth())/gridSizeX, (j*(getHeight()-40))/gridSizeY + 40, (getWidth()/gridSizeX)+1, (getHeight()-40)/gridSizeY+1, this);
							break;
						

						default:
							break;
						}
        			}
        			else if(map[i][j] > 0) g.fillRect((i*getWidth())/gridSizeX, (j*(getHeight()-40))/gridSizeY + 40, (getWidth()/gridSizeX)+1, (getHeight()-40)/gridSizeY+1);
					if(map[i][j] < 0)
					{
						BufferedImage fruit = null;
						if(map[i][j] > -100)
						{
							fruit = imgApple;
						}
						else if(map[i][j] > -200)
						{
							fruit = imgBanana;
						}
						else if(map[i][j] > -300)
						{
							fruit = imgPoison;
						}
						if(map[i][j]%100>-6)
						{
							if(System.currentTimeMillis()%200<100)
							{
								g.drawImage(fruit,(i*getWidth())/gridSizeX, (j*(getHeight()-40))/gridSizeY + 40, (getWidth()/gridSizeX)+1, (getHeight()-40)/gridSizeY+1,this);
							}
						}
						else
						{
							g.drawImage(fruit,(i*getWidth())/gridSizeX, (j*(getHeight()-40))/gridSizeY + 40, (getWidth()/gridSizeX)+1, (getHeight()-40)/gridSizeY+1,this);
						}
						g.setColor(Color.BLACK);
					}
				}
        	}
        }
        //DRAWSHIT
        
        //DRAWLINES
        for (int i = 1; i < gridSizeX; i++)g.drawLine((i*getWidth())/gridSizeX, upperLineHeight, (i*getWidth())/gridSizeX , getHeight()); //ClusterFuck math here
        for (int i = 1; i < gridSizeY; i++)g.drawLine(0, (i*(getHeight()-upperLineHeight))/gridSizeY+upperLineHeight, getWidth(), (i*(getHeight()-upperLineHeight))/gridSizeY+upperLineHeight);
        //DRAWLINES
        
        //DRAW LOST
        if(LOST)
        {
        	g.setColor(Color.RED);
        	g.setFont(lostFont);
        	g.drawString("LOST", getWidth()/2-40, getHeight()/2);
        	g.setColor(Color.BLACK);
        }
        //DRAW LOST
        
        if(MouseClicked) //MOUSE CLICK CHECKER
    	{
    		MouseClicked = false;
    		if(MouseX < 150 && MouseX > 0 && MouseY < upperLineHeight && MouseY > 0) //MENU BUTTON
    		{
    			System.out.println("Switching to Menu");
    			switchToMenu = true;
    		}
    	}
        
        updateUI();
        repaint(); //CALLS ITSELF
    }
    
    public void playFood() { //FoodSound
    	Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
        try {
			clip.open(AudioSystem.getAudioInputStream(new File("bin/switch.wav")));
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
        clip.start();
    }
    
    public boolean hasTicked() {
    	boolean ticked = false;
    	if(!LOST)
    	{
    		if(AutoMove)
    		{
    			if(lastTimeTicked + tickUpdateInterval < System.currentTimeMillis())
    			{
    				ticked = true;
    				lastTimeTicked = System.currentTimeMillis();
    			}
    		}
    		else
    		{
    			if(keyPressed)
    			{
    				ticked = true;
    				keyPressed = false;
    			}
    		}
    	}
    	return ticked;
    }
    
    public void eatFood(int foodValue) {
    	int snakeSizeChanger = 0;
		if(foodValue > - 100) //Apple
		{
			snakeSize += 1;
			snakeSizeChanger = 1;
		}
		else if(foodValue > - 200) //Banana
		{
			snakeSize += 2;
			snakeSizeChanger = 2;
		}
		else if(foodValue > - 300) //Poison
		{
			snakeSize += -3;
			snakeSizeChanger = -3;
			if(snakeSize <= 0)
			{
				snakeSize = 0;
				LOST = true;
			}
		}
		
    	//SNAKE SIZE CHANGER
        for (int i = 0; i < gridSizeX; i++) {
			for (int j = 0; j < gridSizeY; j++) {
				if(map[i][j] > 0){map[i][j] += snakeSizeChanger;if(map[i][j] <0)map[i][j] = 0;} //If snake lower snake
			}
		}
    }

	@Override
	public void mouseClicked(MouseEvent e) { //Mouse click action
		// TODO Auto-generated method stub
		Point p = e.getPoint();
		MouseX = (int) p.getX();
		MouseY = (int) p.getY();
		MouseClicked = true;
	}

	@Override
	public void mouseEntered(MouseEvent e) { //Not used
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) { //Not used
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) { //Not used
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) { //Not used
		// TODO Auto-generated method stub
		
	}
}