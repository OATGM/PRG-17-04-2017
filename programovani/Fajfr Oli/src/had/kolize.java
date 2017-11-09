/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package had;

/**
 *
 * @author zavodnik
 */
public class kolize {
    public kolize(){};
    public static boolean checknisrazku(Snake had, int maximalkax, int maximalkay)
    {
    for (Hrac obj: had.getTelo())
    {
        if( had.getSouradnicex()==obj.getSouradnicex() && had.getSouradnicey()== obj.getSouradnicey())
            return true;
    }
        if(had.getSouradnicex() >= maximalkax)
            return true;
        if(had.getSouradnicex() < 0)
            return true;
        if(had.getSouradnicey() >= maximalkay)
            return true;
        if(had.getSouradnicey() < 0)
            return true;
        
        return false;
    }
    public static boolean checknibonus(Snake had, Mapa bonus)
    {
        if (had.getSouradnicex() == bonus.getSouradnicex() && had.getSouradnicey() == had.getSouradnicey())
        return true;
        else return false; 
    }
}
