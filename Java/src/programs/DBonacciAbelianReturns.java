/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programs;

import cz.brinda.tigrwords.ParikhVector;
import cz.brinda.tigrwords.ParikhVectorsList;
import cz.brinda.tigrwords.WordSubDBonacci;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author Karel Břinda
 */
public class DBonacciAbelianReturns {
    
    public static void main(String[] args) {

        int d = 7;
        int nMax = 10;

        WordSubDBonacci wordSub = new WordSubDBonacci(d);
        wordSub.iterateSub(2);

        int legracniMultiplikacniKonstantaJistoty = 10;

        int maxDelkaPrefixu = (wordSub.getPrefixLength(wordSub.getPrefixLength(nMax))+nMax)*legracniMultiplikacniKonstantaJistoty;
        int[] prefix = wordSub.getWordPrefix(maxDelkaPrefixu);

        for (int n = 1; n <= nMax; n++) {
            int delkaPrefixuKProjiti = wordSub.getPrefixLength(wordSub.getPrefixLength(n))*legracniMultiplikacniKonstantaJistoty;
            //System.out.println("delka " + delkaPrefixuKProjiti);
            LinkedList<ParikhVector> listParikhVectors = ParikhVectorsList.getListParikhVectors(wordSub, n);

            for (ParikhVector parikhVectorToCompare : listParikhVectors) {
                LinkedList<Integer> indexyNavratu = new LinkedList<Integer>();
                long[] currentParikhVector = new long[d];

                for (int i = 0; i < n; i++) {
                    currentParikhVector[prefix[i]]++;
                }

                for (int i = 0; i < delkaPrefixuKProjiti; i++) {
                    if (Arrays.equals(currentParikhVector, parikhVectorToCompare.entries)) {
                        indexyNavratu.add(i);
                    }

                    currentParikhVector[prefix[i]]--;
                    currentParikhVector[prefix[i + n]]++;
                    //System.out.println(""+currentParikhVector[0]+" "+currentParikhVector[1]);
                }

                Object[] poleIndexuNavratu = indexyNavratu.toArray();
                HashSet navratovaAbelovskaSlova = new HashSet();
                
                for (int i = 0; i < indexyNavratu.size() - 1; i++) {
                    String navratoveAbSlovo = "";
                    for (int ii = (Integer) poleIndexuNavratu[i]; ii < (Integer) poleIndexuNavratu[i + 1]; ii++) {
                        navratoveAbSlovo += ""+prefix[ii];
                    }
                    navratovaAbelovskaSlova.add(navratoveAbSlovo);
                }

                String navratovaAbelovskaSlovaUprava = "" + navratovaAbelovskaSlova;
                for (int i=0;i<d;i++){
                    navratovaAbelovskaSlovaUprava=navratovaAbelovskaSlovaUprava.replace(""+i,""+i+"\\allowbreak");
                }
                System.out.print("        $" + n + "$ & $" + parikhVectorToCompare + "$ & $");
                System.out.println(navratovaAbelovskaSlova.size() + "$ & $"  +  (navratovaAbelovskaSlovaUprava).replace("[", "").replace("\\allowbreak]", "").replace("\\allowbreak, ", "$, $") + "$ \\\\ ");
            }
        }


//        for(int d=2;d<10;d++) {
//            
//        }

    }
}
