/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package had;

import javax.swing.JFrame;
import java.awt.Dimension;


/**
 *
 * @author zavodnik
 */
public class HlavniOkno extends JFrame {
    
    public HlavniOkno(String titulek, int sirka, int delka)
    {
        setTitle(titulek);
        setSize(new Dimension(sirka + 3, delka + 3 ));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setVisible(true);
        createBufferStrategy(2);
        
        add( new hernipole(sirka, delka, getBufferStrategy()));
        
}}
