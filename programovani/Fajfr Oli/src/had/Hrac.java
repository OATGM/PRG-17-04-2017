/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package had;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 *
 * @author zavodnik
 */
public class Hrac {
    private int souradnicex;
    private int souradnicey;
    public Color barva;
    public int delkahada;
    
    public Hrac(int souradnicex, int souradnicey, Color barva, int delkahada)
    {
        setSouradnicex(souradnicex);
        setSouradnicey(souradnicey);
        setBarva(barva);
        setDelkahada(delkahada);
    }
    public void draw(Graphics2D grafika)
    {
        grafika.setColor(barva);
        grafika.fillRect(souradnicex, souradnicey, delkahada, delkahada);
    }

    /**
     * @return the souradnicex
     */
    public int getSouradnicex() {
        return souradnicex;
    }

    /**
     * @param souradnicex the souradnicex to set
     */
    public void setSouradnicex(int souradnicex) {
        this.souradnicex = souradnicex;
    }

    /**
     * @return the souradnicey
     */
    public int getSouradnicey() {
        return souradnicey;
    }

    /**
     * @param souradnicey the souradnicey to set
     */
    public void setSouradnicey(int souradnicey) {
        this.souradnicey = souradnicey;
    }

    /**
     * @return the color
     */
    public Color getBarva() {
        return barva;
    }

    /**
     * @param barva
     * @param color the color to set
     */
    public void setBarva(Color barva) {
        this.barva = barva;
    }

    /**
     * @return the delkahada
     */
    public int getDelkahada() {
        return delkahada;
    }

    /**
     * @param delkahada the delkahada to set
     */
    public void setDelkahada(int delkahada) {
        this.delkahada = delkahada;
    }
}
