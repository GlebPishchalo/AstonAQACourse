package Lesson_7_junit_5;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class FactorialTest {
    private Factorial factorial;
    
    @BeforeEach
    void setUp() {
        factorial = new Factorial();
    }
    
    @Test
    @DisplayName("Тест факториала 0")
    void testFactorialZero() {
        assertEquals(1, factorial.calculate(0));
    }
    
    @Test
    @DisplayName("Тест факториала 1")
    void testFactorialOne() {
        assertEquals(1, factorial.calculate(1));
    }
    
    @Test
    @DisplayName("Тест факториала положительных чисел")
    void testFactorialPositive() {
        assertEquals(2, factorial.calculate(2));
        assertEquals(6, factorial.calculate(3));
        assertEquals(24, factorial.calculate(4));
        assertEquals(120, factorial.calculate(5));
        assertEquals(3628800, factorial.calculate(10));
    }
    
    @Test
    @DisplayName("Тест факториала отрицательного числа - ожидается исключение")
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> factorial.calculate(-1));
    }
}