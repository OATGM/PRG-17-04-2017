/*
 * Tento kód nepodléhá žádné licenci a klidně ho komerčně zneužijte
 */
package had;
import javax.swing.SwingUtilities;
/**
 *
 * @author OlíFajfr
 */
public class Hra{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
                
                    @Override
                    public void run()
                    {
                        new HlavniOkno("NEJLEPŠÍ HRA NA SVĚTĚ", 800, 800);
                    }
                
    });
    
}}
