package Lesson_7_testng;

import org.testng.annotations.*;
import static org.testng.Assert.*;


public class ComparatorTest {
    private Comparator comparator;
    
    @BeforeMethod
    public void setUp() {
        comparator = new Comparator();
    }
    
    @Test(description = "Тест сравнения чисел - первый больше второго")
    public void testFirstGreater() {
        assertEquals(comparator.compare(5, 3), 1);
        assertEquals(comparator.compare(0, -1), 1);
    }
    
    @Test(description = "Тест сравнения чисел - первый меньше второго")
    public void testFirstLess() {
        assertEquals(comparator.compare(3, 5), -1);
        assertEquals(comparator.compare(-1, 0), -1);
    }
    
    @Test(description = "Тест сравнения чисел - числа равны")
    public void testEqual() {
        assertEquals(comparator.compare(5, 5), 0);
        assertEquals(comparator.compare(-1, -1), 0);
        assertEquals(comparator.compare(0, 0), 0);
    }
    
    @Test(description = "Тест сравнения чисел - строковое представление")
    public void testCompareAsString() {
        assertEquals(comparator.compareAsString(5, 3), "5 больше 3");
        assertEquals(comparator.compareAsString(3, 5), "3 меньше 5");
        assertEquals(comparator.compareAsString(5, 5), "5 равно 5");
    }
}