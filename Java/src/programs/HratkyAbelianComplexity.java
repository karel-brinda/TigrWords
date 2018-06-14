/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programs;

import cz.brinda.tigrwords.AbelianComplexityGraph;
import cz.brinda.tigrwords.WordSub;
import cz.brinda.tigrwords.WordSubDBonacci;

/**
 *
 * @author Karel Břinda
 */
public class HratkyAbelianComplexity {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        WordSub word = new WordSubDBonacci(10);
        word.iterateSub(2);
        int[] prefix = word.getWordPrefix(1000000);

        for (int letter : prefix) {
            System.out.print(letter + " ");
        }

        for (int n=10;n<15;n++){
            AbelianComplexityGraph graph = word.getAbelianComplexityGraph(n);
            System.out.println("AC(" + n + ") = " + graph.getAbelianComplexity());
        }
    }
}
