/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tigrwords;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Karel Břinda
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({tigrwords.WordSubTest.class})
public class TigrWordsTests {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
