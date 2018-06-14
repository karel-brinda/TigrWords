/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.brinda.tigrwords;

import Jama.Matrix;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Karel Břinda
 */
public class WordSub extends Word implements Cloneable {

    private Substitution substitution;
    private ArrayList<Long> iterationLengths;

    /**
     *
     * @param sub
     */
    public WordSub(Substitution sub) {
        substitution = sub;

        LinkedList<Long> tmpIterationList = new LinkedList<Long>();
        tmpIterationList.add(1L);

        Matrix currentPowerMatrix = substitution.getIncidenceMatrix().copy();

        Matrix leftMatrix = new Matrix(1, substitution.getAlphabetCardinality());
        for (int i = 0; i < substitution.getAlphabetCardinality(); i++) {
            leftMatrix.set(0, i, 1);
        }

        Matrix rightMatrix = new Matrix(substitution.getAlphabetCardinality(), 1);
        rightMatrix.set(0, 0, 1);

        while (tmpIterationList.size() < 100) {
            //currentPowerMatrix.print(4,4);

            Matrix matrixProduct = leftMatrix.times(currentPowerMatrix).times(rightMatrix);
            tmpIterationList.add((new Double(matrixProduct.get(0, 0))).longValue());

            currentPowerMatrix = currentPowerMatrix.times(substitution.getIncidenceMatrix().copy());
        }

        iterationLengths = new ArrayList<Long>(tmpIterationList);
        //System.out.println(iterationLengths.toString());
    }

    @Override
    public WordSub clone() {
        WordSub newWordSub = new WordSub(substitution);

        for (int letter : this.word) {
            newWordSub.word.add(letter);
        }

        return newWordSub;
    }

    /**
     *
     * @param substitution
     */
    public void setSub(Substitution substitution) {
        this.substitution = substitution;
    }

    /**
     *
     * @return
     */
    public Substitution getSub() {
        return substitution;
    }

    /**
     *
     */
    public void applySub() {
        word = substitution.apply(word);
    }

    public void applySub(int number) {
        for (int i = 0; i < number; i++) {
            word = substitution.apply(word);
        }
    }

    /**
     *
     * @param n
     */
    public void iterateSub(int n) {
        substitution = substitution.iterate(n);
    }

    public ArrayList<Long> getIterationLengths() {
        return iterationLengths;
    }

    /**
     *
     * @param length
     * @return
     */
    public int[] getWordPrefix(int length) {
        Iterator prefIterator = new LetterStream();

        int[] prefix = new int[length];
        for (int i = 0; i < length; i++) {
            prefix[i] = (Integer) prefIterator.next();
        }
        return prefix;
    }

    /**
     * Returns length of a prefix containing all factors of length n.
     *
     * @param n
     * @return length of a prefix containg all factors of length n
     */
    public int getPrefixLength(int n) {
        if (substitution.getConstantOfRecurrence() == 0) {
            throw new UnsupportedOperationException("Either constantOfRecurrence must be positive or getPrefixLength must be overrided");
        } else {
            return substitution.getConstantOfRecurrence() * n;
        }
    }

    /**
     *
     * @param n
     * @return
     */
    public AbelianComplexityGraph getAbelianComplexityGraph(int n) {
        LetterStream stream1 = new LetterStream();
        LetterStream stream2 = new LetterStream();

        //int max_i = getRecurrenceLength(n);
        long pv[] = new long[getSub().getSubstitution().length];

        for (int i = 0; i < n; i++) {
            pv[stream2.next()]++;
        }

        ParikhVector poc = new ParikhVector(pv);
        AbelianComplexityGraph abelianComplexityGraph = new AbelianComplexityGraph(poc);

        for (int i = 0; i < getPrefixLength(n); i++) {
            abelianComplexityGraph.nextParikhVector(stream1.next(), stream2.next());
        }

        return abelianComplexityGraph;
    }

    class LetterStream implements Iterator {

        final int NUMBER_OF_LEVELS = 50;
        private List<Integer>[] levels = new LinkedList[NUMBER_OF_LEVELS];
        private Iterator<Integer>[] levelsIterators = new Iterator[NUMBER_OF_LEVELS];

        public LetterStream() {
            for (int i = 0; i < NUMBER_OF_LEVELS; i++) {
                levels[i] = new LinkedList<Integer>();
            }

            for (int letter : word) {
                levels[0].add(letter);
            }

            for (int i = 0; i < NUMBER_OF_LEVELS; i++) {
                levelsIterators[i] = levels[i].iterator();
            }

        }

        @Override
        public Integer next() {
            if (levelsIterators[NUMBER_OF_LEVELS - 1].hasNext() == false) {
                synchronize();
            }
            return levelsIterators[NUMBER_OF_LEVELS - 1].next();
        }

        @Override
        public boolean hasNext() {
            // TODO: vylepsit
            return true;
        }

        @Override
        public void remove() {
        }

        private void synchronize() {
            //find the first non-empty
            int currentLevel = NUMBER_OF_LEVELS - 1;

            if (levelsIterators[NUMBER_OF_LEVELS - 1].hasNext() == false) {
                while (levelsIterators[currentLevel].hasNext() == false) {
                    currentLevel--;
                }

                //back-propagation
                // currentLevel - last non-empty level
                while (currentLevel < NUMBER_OF_LEVELS - 1) {
                    levels[currentLevel + 1] = substitution.apply(levelsIterators[currentLevel].next());
                    levelsIterators[currentLevel + 1] = levels[currentLevel + 1].iterator();
                    currentLevel++;
                }
            }
        }

        public void printInnerState() {
            //TODO: all
            System.out.println("=================================");
            System.out.println("== INNER STATE OF LetterStream ==");
            System.out.println("=================================");
            for (Iterator levelIterator : levelsIterators) {
                //TODO: opravovat
                //Iterator levelIteratorCopy = levelIterator.clone();
            }
            System.out.println("=================================");

        }
//        public void print() {
//            System.out.println("====================");
//            System.out.println("== PARIKH VECTORS ==");
//            System.out.println("====================");
//            for (ParikhVector parikhVector : listOfParikhVectors) {
//                parikhVector.print(debug);
//            }
//            System.out.println("====================");
//
//        }
    }
}
