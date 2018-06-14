/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.brinda.tigrwords;

import Jama.Matrix;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Karel Břinda
 */
public class Substitution {

    private List<Integer>[] substitution;
    private Matrix incidenceMatrix;
    private int alphabetCardinality;
    private int constantOfRecurrence;

    /**
     * 
     * @param substitution
     * @param constantOfRecurrence
     */
    public Substitution(List[] substitution) {
        this(substitution,0);
    }

    public Substitution(List[] substitution, int constantOfRecurrence) {
        this.substitution = substitution;
        this.constantOfRecurrence = constantOfRecurrence;
        createMatrixFromRules();
    }

    /**
     * 
     * @return
     */
    public List[] getSubstitution() {
        return substitution.clone();
    }

    /**
     * 
     * @return
     */
    public int getConstantOfRecurrence() {
        return constantOfRecurrence;
    }

    /**
     * 
     * @param word
     * @return
     */
    public List<Integer> apply(List<Integer> word) {
        LinkedList newWord = new LinkedList<Integer>();
        for (int i : word) {
            newWord.addAll(substitution[i]);
        }
        return newWord;
    }

    /**
     * 
     * @param letter
     * @return
     */
    public List<Integer> apply(int letter) {
        LinkedList<Integer> word = new LinkedList<Integer>(Arrays.asList(letter));
        return apply(word);
    }

    /**
     * 
     */
    public void print() {
        for (int i = 0; i < substitution.length; i++) {
            System.out.println(i + " => " + substitution[i]);
        }
    }

    /**
     * 
     * @param n
     * @return
     */
    public Substitution iterate(int n) {
        List[] sub = substitution.clone();

        for (int i = 0; i < substitution.length; i++) {
            for (int ii = 0; ii < n; ii++) {
                sub[i] = apply(sub[i]);
            }
        }

        return new Substitution(sub, constantOfRecurrence);
    }

    /**
     * 
     * @return
     */
    public Matrix getIncidenceMatrix() {
        return incidenceMatrix;
    }

    public int getAlphabetCardinality() {
        return alphabetCardinality;
    }

    private void createMatrixFromRules() {

        {   // cardinality
            int maxLetter = 0;
            maxLetter = substitution.length - 1;
            for (List<Integer> image : substitution) {
                for (int letter : image) {
                    maxLetter = Math.max(maxLetter, letter);
                }
            }
            alphabetCardinality = maxLetter + 1;
        }

        {   // incidence matrix
            double[][] matrix = new double[alphabetCardinality][alphabetCardinality];
            for (int i = 0; i < substitution.length; i++) {
                for (int letter : substitution[i]) {
                    matrix[letter][i] += 1;
                }
            }
            this.incidenceMatrix = new Matrix(matrix);
        }
    }

    private boolean isSubstitutionValid() {
        //TODO: all
        return true;
    }
}