package Tests;

import Substring.Substring;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubstringUnitTest {
    Substring s;

    @Before
    public void setUp() throws Exception {
        s = new Substring();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldMyClassBeInitialized() {
        assertNotNull(s);
    }

    @Test
    public void checkOutput(){
        String a= "abcd";
        String b = "cdabcdab";
        Assert.assertEquals(s.substring(a,b),3);
    }

    @Test
    public void checkWrongOutput(){
        Substring s= new Substring();
        String a= "abc";
        String b = "cdabcdab";
        assertEquals(s.substring(a,b),-1);

    }


    @Test(timeout = 7000)
    public void shouldNotRumLongerThanTimeout() throws Exception {
        s.veryLongComputation();
    }

    @Test(expected = NullPointerException.class)
    public void whenExceptionThrown_thenExpectationSatisfied() {
        String a= null;
        String b = "b";
        Assert.assertNull("Null exception", s.substring(a, b));    }

}