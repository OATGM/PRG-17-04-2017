import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.RepaintManager;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ButtonUI;


public class mainFrame extends JFrame implements KeyListener{
    static mainDraw draw;
    static mainFrame menuFrame;
    static mainFrame drawFrame;
    
    static boolean switchToGame = false;
    static int previousMenuState = 1;
    static int menuState = 1;
    
    static int menuGame = 1;
    
    //MenuComponets
    //MainScreen
    static JButton btnStart;
    static JButton btnHelp;
    static JButton btnOptions;
    static JButton btnExit;
    static JLabel menuImage;
    //Help
    static JButton btnMenu;
    static JLabel helpText;
    static JLabel helpImage;
    //Options
    static JCheckBox WallsOption;
    static JCheckBox AutoOption;
    static JSlider option2;
    static JLabel option2label;
    static JSlider option3;
    static JLabel option3label;
    static JCheckBox HDGraphics;
    static JSlider optionGridX;
    static JLabel optionGridXlabel;
    static JSlider optionGridY;
    static JLabel optionGridYlabel;
    //MenuComponents
    
    public mainFrame(){
    	if(menuGame == 1) //Skips for mainFrame, enables mainDraw smth
    	{
        menuGame = 2;
    	}
    	else
    	{
    	this.draw=new mainDraw();
		addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        draw.setup();
        MenuInicialization();
    	}
    }

    public static void main(String[] args) {
    	menuFrame = new mainFrame();
    	drawFrame = new mainFrame();
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	menuFrame.setUndecorated(true);
                menuFrame.setTitle("SnakeMenu");
                menuFrame.setResizable(false);
                menuFrame.setSize(500, 300);
                menuFrame.setMinimumSize(new Dimension(500, 300));
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                menuFrame.setLocation(dim.width/2-menuFrame.getSize().width/2, dim.height/2-menuFrame.getSize().height/2);
                menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                menuFrame.pack();
                menuFrame.setVisible(true);
                
            	drawFrame.setTitle("Snejk 8000");
            	drawFrame.setResizable(true);
            	drawFrame.setSize(600, 640);
            	drawFrame.setMinimumSize(new Dimension(600, 600));
            	drawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	drawFrame.getContentPane().add(drawFrame.draw);
            	drawFrame.pack();
            	drawFrame.getContentPane().setBackground(Color.white);
            	drawFrame.setVisible(false);
            }
        });
        while(true)
        {
        	if(switchToGame)
        	{
        		switchToGame = false;
        		menuFrame.setVisible(false);
        		drawFrame.setVisible(true);
        	}
        	if(draw.switchToMenu)
        	{
        		draw.switchToMenu = false;
        		menuFrame.setVisible(true);
        		drawFrame.setVisible(false);
        	}
        	if(previousMenuState != menuState)
        	{
        		switch (previousMenuState) { //UNLOAD MENU
				case 1:
					menuFrame.remove(btnStart);
					menuFrame.remove(btnHelp);
					menuFrame.remove(btnOptions);
					menuFrame.remove(btnExit);
					menuFrame.remove(menuImage);
					break;
				case 2:
					menuFrame.remove(helpText);
					menuFrame.remove(btnMenu);
					menuFrame.remove(helpImage);
					break;
				case 3:
					menuFrame.remove(btnMenu);
					menuFrame.remove(WallsOption);
					menuFrame.remove(AutoOption);
					menuFrame.remove(option2);
				    menuFrame.remove(option2label);
				    menuFrame.remove(option3);
				    menuFrame.remove(option3label);
				    menuFrame.remove(HDGraphics);
				    menuFrame.remove(optionGridX);
				    menuFrame.remove(optionGridXlabel);
				    menuFrame.remove(optionGridY);
				    menuFrame.remove(optionGridYlabel);
					break;

				default:
					break;
				}
        		previousMenuState = menuState;
        		switch (previousMenuState) { //LOAD MENU
				case 1:
					menuFrame.add(btnStart);
					menuFrame.add(btnHelp);
					menuFrame.add(btnOptions);
					menuFrame.add(btnExit);
					menuFrame.add(menuImage);
					menuFrame.repaint();
					break;
				case 2:
					menuFrame.add(helpText);
					menuFrame.add(btnMenu);
					menuFrame.add(helpImage);
					menuFrame.repaint();
					break;
				case 3:
					menuFrame.add(btnMenu);
					menuFrame.add(WallsOption);
					menuFrame.add(AutoOption);
					menuFrame.add(option2);
				    menuFrame.add(option2label);
				    menuFrame.add(option3);
				    menuFrame.add(option3label);
				    menuFrame.add(HDGraphics);
				    menuFrame.add(optionGridX);
				    menuFrame.add(optionGridXlabel);
				    menuFrame.add(optionGridY);
				    menuFrame.add(optionGridYlabel);
					menuFrame.repaint();
					break;

				default:
					break;
				}
        	}
        	if(menuState == 3) // SETS SETTINGS
        	{
        		draw.GoThroughWalls = WallsOption.isSelected();
        		draw.AutoMove = AutoOption.isSelected();
        		draw.foodSpawnProbability = option2.getValue();
        		option2label.setText("Chance of food is " + option2.getValue() + " to 1");
        		draw.tickUpdateInterval = (long) option3.getValue();
        		option3label.setText("Update interval is " + option3.getValue() + " ms");
        		draw.graphicsHigh = HDGraphics.isSelected();
        	}
        }
    }
    
    public void MenuInicialization() {
    	menuFrame.setLayout(null);
        menuFrame.getContentPane().setBackground(Color.WHITE);
        Font menuFont = new Font("Calibri", Font.PLAIN, 20);
        
        btnStart = new JButton("START GAME");
        btnStart.setForeground(Color.WHITE);
        btnStart.setFocusable(false);
        btnStart.setBounds(270, 50, 200, 40);
        btnStart.setBackground(Color.black);
        btnStart.setFont(menuFont);
        menuFrame.add(btnStart);
        
        btnHelp = new JButton("HELP");
        btnHelp.setForeground(Color.WHITE);
        btnHelp.setFocusable(false);
        btnHelp.setBounds(270, 100, 200, 40);
        btnHelp.setBackground(Color.black);
        btnHelp.setFont(menuFont);
        menuFrame.add(btnHelp);
        
        btnOptions = new JButton("OPTIONS");
        btnOptions.setForeground(Color.WHITE);
        btnOptions.setFocusable(false);
        btnOptions.setBounds(270, 150, 200, 40);
        btnOptions.setBackground(Color.black);
        btnOptions.setFont(menuFont);
        menuFrame.add(btnOptions);
        
        btnExit = new JButton("EXIT");
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusable(false);
        btnExit.setBounds(270, 200, 200, 40);
        btnExit.setBackground(Color.black);
        btnExit.setFont(menuFont);
        menuFrame.add(btnExit);
        
        btnMenu = new JButton("MENU");
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFocusable(false);
        btnMenu.setBounds(30, 30, 200, 40);
        btnMenu.setBackground(Color.black);
        btnMenu.setFont(menuFont);
        
        helpText = new JLabel("<html><p>Snake game controled by arrow keys made by Dejv... No right reserved, no copytight, please dont steal my program lol... Click Start to Start, Press escape to exit game</p></html>",SwingConstants.LEFT);
        helpText.setVerticalAlignment(SwingConstants.TOP);
        helpText.setBounds(250, 30, 200, 100);
        
        helpImage = new JLabel();
        helpImage.setIcon(new ImageIcon(new ImageIcon("bin/help.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        helpImage.setBounds(30, 70, 220, 220);
        
        menuImage = new JLabel();
        menuImage.setIcon(new ImageIcon(new ImageIcon("bin/menu.jpg").getImage().getScaledInstance(220, 220, Image.SCALE_DEFAULT)));
        menuImage.setBounds(30, 40, 220, 220);
        menuFrame.add(menuImage);
        
        WallsOption = new JCheckBox("Go trough walls");
        WallsOption.setBounds(250, 30, 150, 30);
        WallsOption.setBackground(Color.white);
        
        AutoOption = new JCheckBox("Automaticaly move");
        AutoOption.setBounds(250, 60, 150, 30);
        AutoOption.setBackground(Color.white);
        
        HDGraphics = new JCheckBox("HD Textures");
        HDGraphics.setBounds(250, 150, 150, 30);
        HDGraphics.setBackground(Color.white);
        
        option2 = new JSlider(1, 100, 20);
        option2.setValueIsAdjusting(true);
        option2.setBounds(250, 90, 100, 30);
        option2.setBackground(Color.white);
        option2label = new JLabel("FoodSpawnChance");
        option2label.setBounds(350, 90, 150, 30);
        
        option3 = new JSlider(100, 1000, 200);
        option3.setValueIsAdjusting(true);
        option3.setBounds(250, 120, 100, 30);
        option3.setBackground(Color.white);
        option3label = new JLabel("Update Interval");
        option3label.setBounds(350, 120, 150, 30);
        
        optionGridX = new JSlider(10, 40, 10);
        optionGridX.setValueIsAdjusting(true);
        optionGridX.setBounds(250, 180, 100, 30);
        optionGridX.setBackground(Color.white);
        optionGridXlabel = new JLabel("GridX");
        optionGridXlabel.setBounds(350, 180, 150, 30);
        
        optionGridY = new JSlider(10, 40, 10);
        optionGridY.setValueIsAdjusting(true);
        optionGridY.setBounds(250, 210, 100, 30);
        optionGridY.setBackground(Color.white);
        optionGridYlabel = new JLabel("GridY");
        optionGridYlabel.setBounds(350, 210, 150, 30);
        
        btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Switching to Game");
				switchToGame = true;
			}
		}); 
        
        btnHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Going to Help");
				menuState = 2;
			}
		});
        
        btnOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Going to Options");
				menuState = 3;
			}
		});
        
        btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Exiting");
				System.exit(0);
			}
		});
        
        btnMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Going to mainMenu");
				menuState = 1;
			}
		});
        
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()== KeyEvent.VK_RIGHT){draw.snakeDirection = 2;draw.keyPressed = true;}
        else if(e.getKeyCode()== KeyEvent.VK_LEFT){draw.snakeDirection = 4;draw.keyPressed = true;}
        else if(e.getKeyCode()== KeyEvent.VK_DOWN){draw.snakeDirection = 3;draw.keyPressed = true;}
        else if(e.getKeyCode()== KeyEvent.VK_UP){draw.snakeDirection = 1; draw.keyPressed = true;}
        else if(e.getKeyCode()== KeyEvent.VK_ESCAPE){draw.switchToMenu = true;}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}