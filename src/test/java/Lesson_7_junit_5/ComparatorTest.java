package Lesson_7_junit_5;



import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class ComparatorTest {
    private Comparator comparator;
    
    @BeforeEach
    void setUp() {
        comparator = new Comparator();
    }
    
    @Test
    @DisplayName("Тест сравнения чисел - первый больше второго")
    void testFirstGreater() {
        assertEquals(1, comparator.compare(5, 3));
        assertEquals(1, comparator.compare(0, -1));
    }
    
    @Test
    @DisplayName("Тест сравнения чисел - первый меньше второго")
    void testFirstLess() {
        assertEquals(-1, comparator.compare(3, 5));
        assertEquals(-1, comparator.compare(-1, 0));
    }
    
    @Test
    @DisplayName("Тест сравнения чисел - числа равны")
    void testEqual() {
        assertEquals(0, comparator.compare(5, 5));
        assertEquals(0, comparator.compare(-1, -1));
        assertEquals(0, comparator.compare(0, 0));
    }
    
    @Test
    @DisplayName("Тест сравнения чисел - строковое представление")
    void testCompareAsString() {
        assertEquals("5 больше 3", comparator.compareAsString(5, 3));
        assertEquals("3 меньше 5", comparator.compareAsString(3, 5));
        assertEquals("5 равно 5", comparator.compareAsString(5, 5));
    }
}