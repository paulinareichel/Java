package Tests;
import Solution.MyException;
import Solution.Solution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class SolutionTest {
    List<Integer> myList;
    Solution s;

    @Before
    public void setUp() {
        myList = new ArrayList<>();
        s = new Solution();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldMyClassBeInitialized() {
        assertNotNull(myList);
        assertNotNull(s);
    }

    @Test(timeout = 7000)
    public void shouldNotRumLongerThanTimeout() throws Exception {
        s.veryLongComputation();
    }

    @Test
    public void checkCorrectValue() throws MyException {
        Collections.addAll(myList, 6, 1, -1);
        assertEquals(s.solution(myList), 2);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ToLargeValue_thenExpectationSatisfied() throws MyException {
        Collections.addAll(myList, 6, 1001, -1);
        s.solution(myList);    }

    @Test
    public void checkCorrectValueOne() throws MyException {
        Collections.addAll(myList, -5, 0, -1);
        assertEquals(s.solution(myList), 1);
    }

    @Test
    public void checkCorrectValueNext() throws MyException {
        Collections.addAll(myList, 1, 2, 3);
        assertEquals(s.solution(myList), 4);    }

    @Test
    public void shouldHaveProperErrorMessage() throws MyException {
        for (int i = 0; i <= 1E5; i++)
            myList.add(5);
        try {
            s.solution(myList);
        } catch (MyException anIndexOutOfBoundsException) {
            assertThat("Index out of bound", myList.size(), is(not(myList.size()<1E5)));  } }
    @Test
    public void shouldHaveErrorMessage() throws MyException {
        Collections.addAll(myList, 6, 1001, -1);
        try {
            s.solution(myList);
        } catch (IllegalArgumentException anIndexOutOfBoundsException) {
            assertThat("Index out of bound", myList.get(2), is(1001));  } }
}