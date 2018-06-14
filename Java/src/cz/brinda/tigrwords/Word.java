/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.brinda.tigrwords;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Karel Břinda
 */
public class Word implements Cloneable {

    /**
     *
     */
    public List<Integer> word = new LinkedList<Integer>();

    /**
     *
     */
    public Word() {
        word.add(0);
    }

    /**
     *
     */
    public Word(List<Integer> initialLetters) {
        word.addAll(initialLetters);
    }

    /**
     *
     * @return
     */
    public List<Integer> getWord() {
        return word;
    }

    /**
     *
     * @param word
     */
    public void setWord(List<Integer> word) {
        this.word = word;
    }

    /**
     *
     */
    public void printWord() {
        System.out.println(word);
    }

    @Override
    public Word clone() {
        Word newWord = new Word();
        for (int letter : this.word) {
            newWord.word.add(letter);
        }
        return newWord;
    }

    @Override
    public String toString() {
        return "" + this.word;
    }

    public void extend(Word word) {
        this.word.addAll(word.word);
    }

    public void extend(int letter) {
        this.word.add(letter);
    }
}
