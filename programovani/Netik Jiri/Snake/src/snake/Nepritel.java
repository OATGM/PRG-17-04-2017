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

public class Nepritel {

    private HerniPanel panel;
    private final Image EnemyObr;
    private int x;
    private int y;
    private int px;
    private int py;

    public Nepritel(HerniPanel panel) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("Enemy.png"));
        EnemyObr = ii.getImage();

        this.panel = panel;
        this.x = 200;
        this.y = 200;
        this.px = 1;
        this.py = 1;
    }
    public Rectangle getOkreje() {
        Rectangle r = new Rectangle(x, y, EnemyObr.getWidth(panel), EnemyObr.getHeight(panel));
        return r;
    }

    public void vykresliSe(Graphics g) {
        g.drawImage(EnemyObr, x, y, null);
    }

    public void provedPohyb() {
        if (panel.getHad().getX() > this.x) {
            x += px;
        }
        if (panel.getHad().getX() < this.x) {
            x -= px;
        }
        if (panel.getHad().getY() > this.y) {
            y += py;
        }
        if (panel.getHad().getY() < this.y) {
            y -= py;
        }
        
    }

}
