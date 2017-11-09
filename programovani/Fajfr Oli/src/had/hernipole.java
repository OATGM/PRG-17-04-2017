/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package had;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import had.Snake.smer;
import static had.Snake.smer.nahoru;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JPanel;




/**
 *
 * @author zavodnik
 */
public class hernipole extends JPanel implements Runnable{
    private final int SIRKA;
    private final int DELKA;
    private Snake had;
    private Mapa bonus;
    private boolean jehra;
    private final BufferStrategy bufr;
    private final int FRAME_DELAY = 98;
    private long cyklus;
    
    public hernipole(int sirka, int delka, BufferStrategy bufr)
    {
        addKeyListener(new TAdapter());
        this.bufr = bufr;
        SIRKA = sirka;
        DELKA = delka;
        setFocusable(true);
        setIgnoreRepaint(true);
        
        gameInit();
    }
    private void gameInit()
    {
        jehra = true;
        had = new Snake(50, 50, 10, Color.BLACK);
        bonus = new Mapa(800, 800, Color.BLACK, 100);
        bonus.jablicko();
        Thread animace= new Thread((Runnable) this, "NEJLEPŠÍ HRA NA SVĚTĚ");
        animace.start();            
    }
    
    public void run()
    {
        cyklus = System.currentTimeMillis();
        while (jehra)
        {
        Logika();
        GUI();
        FrameRate();
        }
        gameOver();
    }
    
    private void FrameRate()
    {
        cyklus += FRAME_DELAY;
        long rozdil = cyklus - System.currentTimeMillis();
        
        try
        {
            Thread.sleep(Math.max(0, rozdil) );
        }
              
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        cyklus = System.currentTimeMillis();
    }
    
    private void GUI()
    {
        Graphics2D grafika2 = (Graphics2D)bufr.getDrawGraphics();
        grafika2.setColor(Color.CYAN);
        grafika2.fillRect(0, 0, SIRKA, DELKA);
        had.draw(grafika2);
        bufr.show();
        Toolkit.getDefaultToolkit();
        
    }
    
    public void Logika()
    {   
        if( kolize.checknisrazku(had, DELKA, SIRKA))
        {
            jehra = false;
        }
        else if (kolize.checknibonus(had, bonus))
        {
            had.rozsirtelo();
            bonus.jablicko();
        }
        else
            had.pohni();
    }
    
    private void gameOver()
    {
        Graphics2D grafika2 = (Graphics2D)bufr.getDrawGraphics();
        String zprava = "TVOJE DUŠE BUDE NAVŽDY ZATRACENA";
        String skore = "Dosáhl jsi velikosti " + had.getTelo().size() + ". I s malým kašpárkem se dá hrát velké divadlo! :)";
        Font font = new Font("Arial", Font.BOLD, 25);        
        grafika2.setColor(Color.WHITE);
        grafika2.setFont(font);
        grafika2.drawString(skore, SIRKA, SIRKA);
    
    }
    
    private class TAdapter extends KeyAdapter
    { 
        @Override
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_W && had.getSmer()!= smer.dolu)
                had.setSmer(smer.nahoru);
            if(key == KeyEvent.VK_S && had.getSmer()!= smer.nahoru)
                had.setSmer(smer.dolu);
            if(key == KeyEvent.VK_A && had.getSmer()!= smer.doprava)
                had.setSmer(smer.doleva);
            if(key == KeyEvent.VK_D && had.getSmer()!= smer.doleva)
                had.setSmer(smer.doprava);
        }
       
    
    }

    
}
