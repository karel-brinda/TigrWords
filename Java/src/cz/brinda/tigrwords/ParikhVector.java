/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.brinda.tigrwords;

/**
 *
 * @author Karel Břinda
 */
public class ParikhVector {

    public long[] entries;
    public ParikhVector[][] neighbors;

    ParikhVector(long[] entries) {
        this.entries = entries;
        this.neighbors = new ParikhVector[entries.length][entries.length];
    }

    public void print() {
        print(false);
    }

    public void print(boolean debug) {
        for (int i = 0; i < entries.length; i++) {
            System.out.print(entries[i] + " ");
        }

        if (debug) {
            System.out.print("    " + this);
        }
        System.out.println();

        if (debug) {
            for (int i = 0; i < entries.length; i++) {
                for (int ii = 0; ii < entries.length; ii++) {
                    System.out.print(neighbors[i][ii]);
                    System.out.print(" ");
                }
                System.out.println(" ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (long entry : entries) {
            toReturn += entry + ",";
        }
        return "(" + toReturn.substring(0, toReturn.length() - 1) + ")";
    }
}