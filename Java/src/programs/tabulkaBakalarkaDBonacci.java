/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programs;

import cz.brinda.tigrwords.AbelianComplexityGraph;
import cz.brinda.tigrwords.WordSub;
import cz.brinda.tigrwords.WordSubDBonacci;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Karel Břinda
 */
/**
 *
 * @author Karel Břinda
 */
public class tabulkaBakalarkaDBonacci {

    public static void main(String[] args) throws IOException {

        int d=12;
        
        WordSub a = new WordSubDBonacci(d);
        a.iterateSub(3);


        for (int n = 1; n <= 1000; n++) {
            AbelianComplexityGraph graph = a.getAbelianComplexityGraph(n);
            //System.out.println("AC(" + n + ") = " + graph.getAbelianComplexity());
            //System.out.println(""+graph);
            System.out.print(n + " & " + (graph.getAbelianComplexity()) + " & ");
//            graph.printParikhVectors();

            if (n % 8 == 0) {
                System.out.println(" \\\\ ");
            }

        }

    }
}
