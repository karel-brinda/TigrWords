/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programs;

import cz.brinda.tigrwords.Word;
import cz.brinda.tigrwords.WordSubDBonacci;
import java.util.Random;

/**
 *
 * @author Karel Břinda
 */
public class TestAutomatu {

    public static void main(String[] args) {

        int maxn = 4;
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            Word w = new Word();
            for (int ii = maxn; ii >= 0; ii--) {

                WordSubDBonacci wrd = new WordSubDBonacci(5);
                wrd.applySub(ii);

                if (rand.nextBoolean()==true) {
                    w.extend(wrd);
                }
            }
            System.out.println(w);
        }
    }
}