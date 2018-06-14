/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tigrwords;

import cz.brinda.tigrwords.WordSubDBonacci;
import cz.brinda.tigrwords.WordSub;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karel Břinda
 */
public class WordSubTest {

    public WordSubTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    /**
//     * Test of clone method, of class WordSub.
//     */
//    @Test
//    public void testClone() {
//        System.out.println("clone");
//        WordSub instance = null;
//        WordSub expResult = null;
//        WordSub result = instance.clone();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    /**
//     * Test of setSub method, of class WordSub.
//     */
//    @Test
//    public void testSetSub() {
//        System.out.println("setSub");
//        Substitution substitution = null;
//        WordSub instance = null;
//        instance.setSub(substitution);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    /**
//     * Test of getSub method, of class WordSub.
//     */
//    @Test
//    public void testGetSub() {
//        System.out.println("getSub");
//        WordSub instance = null;
//        Substitution expResult = null;
//        Substitution result = instance.getSub();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    /**
//     * Test of applySub method, of class WordSub.
//     */
//    @Test
//    public void testApplySub() {
//        System.out.println("applySub");
//        WordSub instance = null;
//        instance.applySub();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    /**
//     * Test of iterateSub method, of class WordSub.
//     */
//    @Test
//    public void testIterateSub() {
//        System.out.println("iterateSub");
//        int n = 0;
//        WordSub instance = null;
//        instance.iterateSub(n);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of getWordPrefix method, of class WordSub.
     */
    @Test
    public void testGetWordPrefix() {
        System.out.println("getWordPrefix");

        {
            WordSub instance = new WordSubDBonacci(2);
            int[] expResult = {0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1};
            int[] result = instance.getWordPrefix(expResult.length);
            assertEquals(Arrays.equals(expResult, result), true);
        }
        {

            WordSub instance = new WordSubDBonacci(3);
            int[] expResult = {0, 1, 0, 2, 0, 1, 0, 0, 1, 0, 2, 0, 1, 0};
            int[] result = instance.getWordPrefix(expResult.length);
            assertEquals(Arrays.equals(expResult, result), true);
        }
    }
//    /**
//     * Test of getRecurrenceLength method, of class WordSub.
//     */
//    @Test
//    public void testGetRecurrenceLength() {
//        System.out.println("getRecurrenceLength");
//        int n = 0;
//        WordSub instance = null;
//        int expResult = 0;
//        int result = instance.getRecurrenceLength(n);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    /**
//     * Test of getAbelianComplexityGraph method, of class WordSub.
//     */
//    @Test
//    public void testGetAbelianComplexityGraph() {
//        System.out.println("getAbelianComplexityGraph");
//        int n = 0;
//        WordSub instance = null;
//        AbelianComplexityGraph expResult = null;
//        AbelianComplexityGraph result = instance.getAbelianComplexityGraph(n);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
