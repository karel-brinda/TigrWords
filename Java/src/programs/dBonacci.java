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
public class dBonacci {

    public static void main(String[] args) throws IOException {
        for (int d = 4; d <= 4; d++) {

            WordSub a = new WordSubDBonacci(d);
            a.iterateSub(3);

            FileWriter output = new FileWriter("dbonacci_d=" + d + ".txt");

            System.out.println("" + d);

            for (int n = 1; n < 50000; n++) {
                AbelianComplexityGraph graph = a.getAbelianComplexityGraph(n);
                //System.out.println("AC(" + n + ") = " + graph.getAbelianComplexity());
                //System.out.println(""+graph);
                output.write("" + graph + "\n");
//                    graph.printParikhVectors();

                if (n % 100 == 0) {
                    System.out.println(d + " " + n);
                }

            }

            output.close();
            //for (int i = 0; i < 100; i++) {
            //    System.out.println(i + " " + a.getPrefixLength(i));
            //}
        }
    }
}
