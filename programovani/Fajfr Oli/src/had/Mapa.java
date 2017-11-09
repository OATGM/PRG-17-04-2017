/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package had;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author zavodnik
 */
public class Mapa extends Hrac {
    
        private final int maximalkax;
        private final int maximalkay;
        private Random random;

    public Mapa(int maximalkax , int maximalkay , Color barva , int delkahada ) {
        super(0, 0, barva, delkahada);
        random = new Random();
        this.maximalkax = maximalkax;
        this.maximalkay = maximalkay;
    }
    public void jablicko()
    {
      setSouradnicex(350);
      setSouradnicey(320);
    
    }
       
}
