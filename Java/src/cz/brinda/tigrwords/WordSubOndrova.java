/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.brinda.tigrwords;

/**
 *
 * @author Karel Břinda
 */
public class WordSubOndrova extends WordSubOndruvVypocetLinearniRekurence {

    public WordSubOndrova(int d, int t) {
        super(SubstitutionFactory.createOndrovaSpecialni(d, t));
    }
}
