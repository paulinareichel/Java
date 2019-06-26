package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AnotherSolutionTest.class,
        SubstringUnitTest.class,
        MatrixTest.class,
        SolutionTest.class,
        BubbleSortTest.class
})
public class MyTestSuite {
}
