/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.brinda.tigrwords;

import java.util.LinkedList;

/**
 *
 * @author Karel Břinda
 */
public class AbelianComplexityGraph {

    private ParikhVector initialParikhVector;
    private ParikhVector currentParikhVector;
    private LinkedList<ParikhVector> listOfParikhVectors = new LinkedList<ParikhVector>();

//    AbelianComplexityGraph(int[] entries) {
//        this.initialParikhVector = new ParikhVector(entries);
//    }
//
    AbelianComplexityGraph(ParikhVector initialParikhVector) {
        this.initialParikhVector = initialParikhVector;
        currentParikhVector = initialParikhVector;

        for (int entry = 0; entry < initialParikhVector.entries.length; entry++) {
            initialParikhVector.neighbors[entry][entry] = initialParikhVector;
        }

        listOfParikhVectors.add(initialParikhVector);
    }

    /**
     * 
     * @param previousLetter
     * @param nextLetter
     */
    public void nextParikhVector(int previousLetter, int nextLetter) {
        if (currentParikhVector.neighbors[previousLetter][nextLetter] == null) {
            ParikhVector newParikhVector = new ParikhVector(currentParikhVector.entries.clone()); // obezlicka
            newParikhVector.entries[previousLetter]--;
            newParikhVector.entries[nextLetter]++;
            addParikhVector(newParikhVector);
        } else {
            // OK
        }

        if (currentParikhVector.neighbors[previousLetter][nextLetter] == null) {
            System.out.println("Prusvih " + previousLetter + " " + nextLetter);
            currentParikhVector.print();
            System.exit(-1);
        }
        currentParikhVector = currentParikhVector.neighbors[previousLetter][nextLetter];
    }

    private void addParikhVector(ParikhVector newParikhVector) {
        int entry1, entry2;
        long diff = 0;

        eachParikhVector:
        for (ParikhVector parikhVector : listOfParikhVectors) {
            //each Parikh vector

            entry1 = entry2 = -1;

            for (int i = 0; i < parikhVector.entries.length; i++) {
                diff = parikhVector.entries[i] - newParikhVector.entries[i];

                if (diff < -1 || 1 < diff) {
                    // diff is too great or too less
                    continue eachParikhVector;
                } else {
                    // diff is -1, 0, or 1
                    if (diff == 1) {
                        // diff is 1
                        if (entry2 == -1) {
                            // diff is 1 for the FIRST time
                            entry2 = i;
                        } else {
                            // diff is 1 for the second time
                            continue eachParikhVector;
                        }
                    } else {
                        // diff is 0 or -1
                        if (diff == -1) {
                            // diff is -1
                            if (entry1 == -1) {
                                // diff is -1 for the FIRST time
                                entry1 = i;
                            } else {
                                // diff is -1 for the second time
                                continue eachParikhVector;
                            }

                        } else {
                            // diff je 0
                        }
                    }
                }
            }

            if (entry1 != -1 && entry2 != -1) {
                newParikhVector.neighbors[entry1][entry2] = parikhVector;
                parikhVector.neighbors[entry2][entry1] = newParikhVector;
                for (int entry = 0; entry < newParikhVector.entries.length; entry++) {
                    newParikhVector.neighbors[entry][entry] = newParikhVector;
                }
            }

        }

        listOfParikhVectors.add(newParikhVector);
    }

    /**
     * 
     */
    public void printParikhVectors() {
        printParikhVectors(false);
    }

    /**
     * 
     * @param debug
     */
    public void printParikhVectors(boolean debug) {
        System.out.println("====================");
        System.out.println("== PARIKH VECTORS ==");
        System.out.println("====================");
        for (ParikhVector parikhVector : listOfParikhVectors) {
            parikhVector.print(debug);
        }
        System.out.println("====================");
    }
    
    @Override
    public String toString() {
        String toReturn="";
        for (ParikhVector parikhVector : listOfParikhVectors) {
            toReturn += parikhVector+",";
        }
        return toReturn.substring(0, toReturn.length() - 1);
    }

    /**
     * 
     * @return
     */
    public int getAbelianComplexity() {
        return listOfParikhVectors.size();
    }
    
    public LinkedList<ParikhVector> getParikhVectors(){
        return listOfParikhVectors;
    }
}
