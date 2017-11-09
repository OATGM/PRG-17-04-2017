/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author zavodnik
 */
public class HerniPanel extends JPanel implements ActionListener {

    private int sirkaPanelu = 500; // šířka panelu
    private int vyskaPanelu = 500; // výška panelu
    private int score = 0;         // score
    private int lup = 1;           // později vysvětlím
    private boolean hrajeSe;
    private Timer casovac;
    private Had had;               // reference na jinou třídu
    private Jidlo jidlo;           // reference na jinou třídu
    private Nepritel nep;          // reference na jinou třídu

    public HerniPanel() {
        init();
        start();
    }

    private void init() {
        this.setPreferredSize(new Dimension(sirkaPanelu, vyskaPanelu)); // tvorba hracího panelu
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        this.setFocusable(true);
    }

    private void start() {         // start
        hrajeSe = true;
        had = new Had(this);     
        jidlo = new Jidlo(this);
        nep = new Nepritel(this);
        this.addKeyListener(had);
        this.casovac = new Timer(10, this);
        casovac.start();
    }

    private void vypisScore(Graphics g) {
        g.drawString(String.valueOf("score : " + score), 10, 30);  //vypíše score

    }

    private void vypisKonec(Graphics g) {
        String textKonec = "G A M E   O V E R"; // text, který se vypíše při skončení hry
        Font pismo = new Font(Font.MONOSPACED, Font.BOLD, 28);
        g.setFont(pismo);
        FontMetrics fm = g.getFontMetrics(pismo);
        int sirkaTextu = fm.stringWidth(textKonec);

        g.setColor(Color.red);  // tento text bude červeně
        g.drawString(textKonec, (this.getWidth() - sirkaTextu) / 2, this.getHeight() / 2);
        casovac.stop();
        System.out.println("Vaše score je: " + score); // v "Output" vám napíše vaše konečné score
    }

    @Override
    public void paintComponent(Graphics g) {          // vše potřebné vykreslí na hracím poli
        if (hrajeSe) {

            super.paintComponent(g);
            had.vykresliSe(g);
            jidlo.vykresliSe(g);
            nep.vykresliSe(g);
            this.vypisScore(g);
            if (lup < 0) {
                this.vypisKonec(g);
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        had.provedPohyb();                       // provede pohyb hada a nepřítele
        nep.provedPohyb();

        this.trefa();
        this.trefa2();

        this.repaint();
    }

    public int getSirka() {
        return sirkaPanelu;
    }

    public int getVyska() {
        return vyskaPanelu;
    }

    public Had getHad() {
        return had;
    }

    private void trefa() {
        if (had.getOkreje().intersects(jidlo.getOkreje())) {
            jidlo.provedPohyb();
            score++;
        }
    }

    private void trefa2() {
        if (had.getOkreje().intersects(nep.getOkreje())) {
            lup--;
        }
    }

}
