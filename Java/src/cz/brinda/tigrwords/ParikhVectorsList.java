/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.brinda.tigrwords;

import cz.brinda.tigrwords.AbelianComplexityGraph;
import cz.brinda.tigrwords.ParikhVector;
import cz.brinda.tigrwords.WordSub;
import cz.brinda.tigrwords.WordSubDBonacci;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Karel Břinda
 */
public class ParikhVectorsList {

    public static LinkedList<ParikhVector> getListParikhVectors(WordSub wordSub, int n) {
        AbelianComplexityGraph graph = wordSub.getAbelianComplexityGraph(n);
        return graph.getParikhVectors();
    }

    public static void createFileListParikhVectors(WordSub wordSub, String nameOfFile, int minN, int maxN) throws IOException {
        wordSub.iterateSub(2);
        FileWriter output = new FileWriter(nameOfFile);

        output.write("#iterationLengths=" + wordSub.getIterationLengths() + "\n");

        for (int n = minN; n <= maxN; n++) {
            AbelianComplexityGraph graph = wordSub.getAbelianComplexityGraph(n);
            output.write("" + graph + "\n");
        }

        output.close();
    }

    public static void createFileListParikhVectors(WordSub wordSub, String nameOfFile, int maxN) throws IOException {
        createFileListParikhVectors(wordSub, nameOfFile, 1, maxN);
    }

    public static void main(String[] args) throws IOException {
//        for (int d = 2; d <= 10; d++) {
//            for (int t = 2; t <= 10; t++) {
//                System.out.println("" + d + " " + t);
//                createFileListParikhVectors(new WordSubOndrova(d, t), "slova_s_afinni_komplexitou_d=" + d + "_t=" + t + ".txt");
//            }
//        }

        for (int d = 8; d <= 8; d++) {
            System.out.println("" + d);
            createFileListParikhVectors(new WordSubDBonacci(d), "dbonacci_d=" + d + ".txt", 15000);
        }
    }
}
