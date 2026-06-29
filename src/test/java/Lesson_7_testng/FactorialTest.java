package Lesson_7_testng;

import org.testng.annotations.*;
import static org.testng.Assert.*;


public class FactorialTest {
    private Factorial factorial;
    
    @BeforeMethod
    public void setUp() {
        factorial = new Factorial();
    }
    
    @Test(description = "Тест факториала 0")
    public void testFactorialZero() {
        assertEquals(factorial.calculate(0), 1);
    }
    
    @Test(description = "Тест факториала 1")
    public void testFactorialOne() {
        assertEquals(factorial.calculate(1), 1);
    }
    
    @Test(description = "Тест факториала положительных чисел")
    public void testFactorialPositive() {
        assertEquals(factorial.calculate(2), 2);
        assertEquals(factorial.calculate(3), 6);
        assertEquals(factorial.calculate(4), 24);
        assertEquals(factorial.calculate(5), 120);
        assertEquals(factorial.calculate(10), 3628800);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class, description = "Тест факториала отрицательного числа")
    public void testFactorialNegative() {
        factorial.calculate(-1);
    }
    
    @DataProvider(name = "factorialData")
    public Object[][] factorialData() {
        return new Object[][] {
            {0, 1},
            {1, 1},
            {2, 2},
            {3, 6},
            {4, 24},
            {5, 120}
        };
    }
    
    @Test(dataProvider = "factorialData", description = "Параметризованный тест факториала")
    public void testFactorialParameterized(int input, long expected) {
        assertEquals(factorial.calculate(input), expected);
    }
}