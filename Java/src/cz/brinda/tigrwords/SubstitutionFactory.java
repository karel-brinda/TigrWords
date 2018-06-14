/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.brinda.tigrwords;

import java.util.LinkedList;
import java.util.Arrays;


/**
 *
 * @author Karel Břinda
 */
public class SubstitutionFactory {

    /**
     * 
     * @param d
     * @return
     */
    public static Substitution createDBonacci(int d) {
        LinkedList[] dbonacci;

        dbonacci = new LinkedList[d];
        for (int i = 0; i < d - 1; i++) {
            dbonacci[i] = new LinkedList<Integer>(Arrays.asList(0, i + 1));
        }

        dbonacci[d - 1] = new LinkedList<Integer>(Arrays.asList(0));

        //FIXME: konstanta pro d-bonacciho
        return new Substitution(dbonacci);
    }

    public static Substitution createOndrovaSpecialni(int d, int t) {
        LinkedList[] ondrova;

        ondrova = new LinkedList[d];
        
        ondrova[0] = new LinkedList<Integer>(Arrays.asList(1));
        for (int i = 0; i < t; i++) {
            ondrova[0].add(0, 0);
        }

        
        for (int letter = 1; letter < d - 1; letter++) {
            ondrova[letter] = new LinkedList<Integer>(Arrays.asList(letter + 1));
        }

        ondrova[d - 1] = new LinkedList<Integer>(Arrays.asList(1));
        for (int i = 0; i < t-1; i++) {
            ondrova[d-1].add(0, 0);
        }

        //FIXME: konstanta pro Ondru
        return new Substitution(ondrova);
    }
}
