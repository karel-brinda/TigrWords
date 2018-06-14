/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programs;

import cz.brinda.tigrwords.*;
import java.util.Arrays;

/**
 *
 * @author Karel Břinda
 */
public class VerySimpleTribonacci {

    public static void main(String[] args) {
        {
            int d=3;
            WordSub word = new cz.brinda.tigrwords.WordSubDBonacci(d);
            //word.iterateSub(2);
            int[] prefix = word.getWordPrefix(5000);

            String pref = ("" + Arrays.toString(prefix)).replace("11, ", "[11]").replace("10, ", "[10]").replace(", ", "   ");

            for (int i = 0; i < pref.length(); i++) {
                if((i%200)==0){
              //      System.out.print("$ \\\\\n $"+d+"$ & $");
                }
//                if((i%500)==0){
//                    System.out.print("\\break\n");
//                }
                System.out.print(""+pref.charAt(i));
            }


            //System.out.println(word.getSub().getIncidenceMatrix());
//            (word.getSub().getIncidenceMatrix().times(word.getSub().getIncidenceMatrix())).print(5, 2);
        }
//        {
//            WordSub word = new cz.brinda.tigrwords.WordSubDBonacci(3);
//            word.iterateSub(1);
//            int[] prefix = word.getWordPrefix(100);
//
//            System.out.println(Arrays.toString(prefix));
//
//            //System.out.println(word.getSub().getIncidenceMatrix());
//            word.getSub().getIncidenceMatrix().print(5, 2);
//        }
    }
}
