/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.brinda.tigrwords;

/**
 *
 * @author Karel Břinda
 */
public class WordSubOndruvVypocetLinearniRekurence extends WordSub {

    public WordSubOndruvVypocetLinearniRekurence(Substitution substitution) {
        super(substitution);
    }

    @Override
    public int getPrefixLength(int n) {
        int d = getSub().getAlphabetCardinality();
        int j = 0;
        while (n > getIterationLengths().get(j)) {
            j++;
        }
        return (int) (getIterationLengths().get(j + d - 1) + getIterationLengths().get(j));
    }
}
