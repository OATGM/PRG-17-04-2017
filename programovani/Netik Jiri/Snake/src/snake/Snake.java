/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import javax.swing.JFrame;

/**
 *
 * @author zavodnik
 */
public class Snake extends JFrame {

    public Snake(){
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        HerniPanel panel = new HerniPanel();
        this.add(panel);
        
        this.setResizable(false);
        this.pack();
    }
    public static void main(String[] args) {
        Snake hlavniOkno = new Snake();
        hlavniOkno.setVisible(true);
    }

}
