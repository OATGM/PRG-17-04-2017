/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package had;



import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics2D;


/**
 *
 * @author zavodnik
 */
public class Snake extends Hrac {
     public enum smer
    {
        nahoru, dolu, doleva, doprava
    }   
    
    
    private List<Hrac> telo;
    private smer smer;
    private Color barvatela;

    public Snake(int souradnicex, int souradnicey, int delkahada, Color barva)
    {
        super(souradnicex, souradnicey, barva, delkahada);
        telo = new ArrayList<>();
        setSmer(smer.doprava);
        
    }
    @Override
    public void draw(Graphics2D grafika)
    {
        grafika.setColor(getBarva());
        grafika.fillRect(getSouradnicex(), getSouradnicey(), getDelkahada(), getDelkahada());
        telo.stream().forEach((obj) -> {
            obj.draw(grafika);
        });
    }
    
    public void rozsirtelo()
    {
        telo.add(0, new Hrac(getSouradnicex(), getSouradnicey(), getBarvatela(), getDelkahada()));
        pohnihlavou();
    }
    public void pohni()
    {
        pohnihlavou();
        pohnitelem();
    }
    
    private void pohnihlavou()
    {
        switch(getSmer())
        {
            case doleva:
                setSouradnicex(getSouradnicex() - getDelkahada());
                break;
            case doprava:
                setSouradnicex(getSouradnicex() + getDelkahada());
                break;
            case nahoru:
                setSouradnicey(getSouradnicey() - getDelkahada());
                break;
            case dolu:
                setSouradnicey(getSouradnicey() + getDelkahada());
                break;
             
        }       
    }
    
    private void pohnitelem()
    {
        int x = getSouradnicex();
        int y = getSouradnicey();
        int z;
        for(Hrac obj: telo)
        {
            z = getSouradnicex();
            obj.setSouradnicex(x);
            x = z;
            z = obj.getSouradnicey();
            obj.setSouradnicey(y);
            y=z;
            
        }
        
    }
    /**
     * @return the telo
     */
    public List<Hrac> getTelo() {
        return telo;
    }

    /**
     * @param telo the telo to set
     */
    public void setTelo(List<Hrac> telo) {
        this.telo = telo;
    }

    /**
     * @return the smer
     */
    public smer getSmer() {
        return smer;
    }

    /**
     * @param smer the smer to set
     */
    public void setSmer(smer smer) {
        this.smer = smer;
    }

    /**
     * @return the barvatela
     */
    public Color getBarvatela() {
        return barvatela;
    }

    /**
     * @param barvatela the barvatela to set
     */
    public void setBarvatela(Color barvatela) {
        this.barvatela = barvatela;
    }
    
   
}
