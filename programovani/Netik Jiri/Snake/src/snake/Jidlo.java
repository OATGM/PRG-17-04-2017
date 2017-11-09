/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.Random;

public class Jidlo {

    private int x;
    private int y;
    private int fruit;
    private Random kod;
    private Image jidloObr;
    private HerniPanel panel;

    public Jidlo(HerniPanel panel) {    
        this.kod = new Random();

        fruit = kod.nextInt(2);
        if (fruit == 0) {
            ImageIcon ii = new ImageIcon(this.getClass().getResource("Jablko.png"));
            jidloObr = ii.getImage();
        }
        if (fruit == 1) {
            ImageIcon ii = new ImageIcon(this.getClass().getResource("Banan.png"));
            jidloObr = ii.getImage();
        }
        if (fruit == 2) {
            ImageIcon ii = new ImageIcon(this.getClass().getResource("Pomeranc.png"));
            jidloObr = ii.getImage();
        }
        x = kod.nextInt(panel.getSirka() - jidloObr.getWidth(panel));
        y = kod.nextInt(panel.getVyska() - jidloObr.getHeight(panel));
    }
    public Rectangle getOkreje() {
        Rectangle r = new Rectangle(x, y, jidloObr.getWidth(panel), jidloObr.getHeight(panel));
        return r;
    }
    public void vykresliSe(Graphics g) {
        g.drawImage(jidloObr, x, y, null);
    }
    
    public void provedPohyb() {    // přemístí a změní ovoce
        this.kod = new Random();
        x = 0;
        y = 0;
        x = x + kod.nextInt(449);
        y = y + kod.nextInt(449);
        fruit = kod.nextInt(3);
        if (fruit == 0) {
            ImageIcon ii = new ImageIcon(this.getClass().getResource("Jablko.png"));
            jidloObr = ii.getImage();
        }
        if (fruit == 1) {
            ImageIcon ii = new ImageIcon(this.getClass().getResource("Banan.png"));
            jidloObr = ii.getImage();
        }
        if (fruit == 2) {
            ImageIcon ii = new ImageIcon(this.getClass().getResource("Pomeranc.png"));
            jidloObr = ii.getImage();
        }
    }

}
