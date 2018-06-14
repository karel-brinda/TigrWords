/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programs.abelianComplexityGUI2;

import cz.brinda.tigrwords.WordSub;
import cz.brinda.tigrwords.WordSubDBonacci;
import java.util.List;

/**
 *
 * @author Karel Břinda
 */
public class VypocetniVlakno extends Thread {

    WordSub wordSub;
    List<Integer> ns;
    int d;
    boolean skoncit = false;

    public VypocetniVlakno(int d, List<Integer> ns) {
        super();
        this.wordSub = new WordSubDBonacci(d);
        this.ns = ns;
        this.d = d;
    }

    @Override
    public void run() {

        for (int n : ns) {
            //System.out.println("Chci vlozit do db " + n);
            if (skoncit == false) {
                RidiciVlakno.vlozDoDatabaze(d, n, "" + wordSub.getAbelianComplexityGraph(n));
            }
            yield();
        }
    }
    
    public void ukoncit(){
        skoncit=true;
    }
}
