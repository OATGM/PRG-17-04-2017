/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author zavodnik
 */
public class Had implements KeyListener {
    private HerniPanel panel;
    private final Image hlavaObr;
    private int x;
    private int y;
    private int px;
    private int py;
    
    public Had(HerniPanel panel) {            // konstruktor hada
        ImageIcon ii = new ImageIcon(this.getClass().getResource("Hlava.png")); // obrázek hada
        hlavaObr = ii.getImage();
        
        this.panel = panel;
        this.x = 50;
        this.y = 50;
        this.px = 1;
        this.py = 0;
    }
    public void vykresliSe(Graphics g) {
        g.drawImage(hlavaObr, x, y, null);
    }
    public Rectangle getOkreje() {
        Rectangle r = new Rectangle(x, y, hlavaObr.getWidth(panel), hlavaObr.getHeight(panel));
        return r;
    }
    public void provedPohyb() {
        x += px;
        y += py;
        if (x < 0) {
            x = panel.getSirka() - 1;
        }
        if (x > panel.getSirka()) {
            x = 1;
        }
        if (y < 0) {
            y = panel.getVyska() - 1;
        }
        if (y > panel.getVyska()) {
            y = 1;
        }
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    @Override
    public void keyPressed(KeyEvent ke) {    // ovládání hada
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            py = -1;
            px = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            py = 1;
            px = 0;
        }
        if (key == KeyEvent.VK_LEFT) {
            px = -1;
            py = 0;
        }
        if (key == KeyEvent.VK_RIGHT) { 
            px = 1;
            py = 0;
        }
        if (key == KeyEvent.VK_SPACE) {    // zrychlení hada
            if (px == 1 ) {
            px = px * 2;
            }
            if (px == -1 ) {
            px = px * 2;
            }
            if (py == 1 ) {
            py = py * 2;
            }
            if (py == -1 ) {
            py = py * 2;
            }
            
            
        }
    }
    @Override
    public void keyReleased(KeyEvent ke) {
        if (px == 3) {
            px = px / 2;
        }
        if (px == -3) {
            px = px / 2;
        }
        if (py == 3) {
            py = py / 2;
        }
        if (py == -3) {
            py = py / 2;
        }
    }
    @Override
    public void keyTyped(KeyEvent ke) {
        
    } 
    
}
