/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.brinda.tigrwords;

/**
 *
 * @author Karel Břinda
 */
public class WordSubDBonacci extends WordSubOndruvVypocetLinearniRekurence {

    public WordSubDBonacci(int d) {
          super (SubstitutionFactory.createDBonacci(d));
    }

  
}
