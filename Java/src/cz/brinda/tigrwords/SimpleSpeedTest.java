/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.brinda.tigrwords;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Karel Břinda
 */
public class SimpleSpeedTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Date date = new Date();
//
//        long time1, time2, time3, time4;
//
//        WordSub word1 = WordFactory.createDBonacci(3);
//        WordSub word2 = WordFactory.createDBonacci(3);
//        WordSub word3 = WordFactory.createDBonacci(3);
//        WordSub word4 = WordFactory.createDBonacci(3);
//
//        word3.iterateSub(0);
//        word4.iterateSub(0);
//
//
////        word1.getSub().print();
////        word1.iterateSub(3);
////        word1.getSub().print();
//////        word2.getSub().print();
//////        word2.printWord();
////    }
//
//
//
//        int c = 10;
//        int letter;
//
//        {
//            date = new Date();
//            time1 = date.getTime();
//            word1 = WordFactory.createDBonacci(3);
//            word2 = WordFactory.createDBonacci(3);
//
//            for (int n = 1; n < 1000; n++) {
//                word1.iterationInit();
//                word2.iterationInit();
//
//                Set<Object> pvVectors = new TreeSet<Object>();
//
//                int[] currentPV = new int[3];
//                for (int i = 0; i < n; i++) {
//                    letter = word1.iterationGetNextLetter();
//                    currentPV[letter] += 1;
//                }
//
//                pvVectors.add(currentPV[0] + " " + currentPV[1] + " " + currentPV[2]);
//
//                for (int i = 0; i < n * c; i++) {
//                    currentPV[word1.iterationGetNextLetter()] += 1;
//                    currentPV[word2.iterationGetNextLetter()] -= 1;
//                    //System.out.println(currentPV.toString());
//                    pvVectors.add(currentPV[0] + " " + currentPV[1] + " " + currentPV[2]);
//                }
//
//                System.out.println("AC(" + n + ") = " + pvVectors.size() + " " + pvVectors);
//            }
//            date = new Date();
//            time2 = date.getTime();
//        }
//
//
//        {
//            date = new Date();
//            time3 = date.getTime();
//            for (int n = 1; n < 1000; n++) {
//                word3.iterationInit();
//                word4.iterationInit();
//
//                Set<Object> pvVectors = new TreeSet<Object>();
//
//                int[] currentPV = new int[3];
//                for (int i = 0; i < n; i++) {
//                    letter = word3.iterationGetNextLetter();
//                    currentPV[letter] += 1;
//                }
//
//                pvVectors.add(currentPV[0] + " " + currentPV[1] + " " + currentPV[2]);
//
//                for (int i = 0; i < n * c; i++) {
//                    currentPV[word3.iterationGetNextLetter()] += 1;
//                    currentPV[word4.iterationGetNextLetter()] -= 1;
//                    //System.out.println(currentPV.toString());
//                    pvVectors.add(currentPV[0] + " " + currentPV[1] + " " + currentPV[2]);
//                }
//
//                System.out.println("AC(" + n + ") = " + pvVectors.size() + " " + pvVectors);
//            }
//            date = new Date();
//            time4 = date.getTime();
//        }
//
//        System.out.println(time2 - time1);
//        System.out.println(time4 - time3);

    }
}