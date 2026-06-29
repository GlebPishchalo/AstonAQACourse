package Lesson_7_junit_5;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Тест сложения двух чисел")
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(0, calculator.add(-1, 1));
        assertEquals(-5, calculator.add(-2, -3));
    }
    
    @Test
    @DisplayName("Тест вычитания двух чисел")
    void testSubtract() {
        assertEquals(3, calculator.subtract(5, 2));
        assertEquals(-2, calculator.subtract(1, 3));
        assertEquals(0, calculator.subtract(5, 5));
    }
    
    @Test
    @DisplayName("Тест умножения двух чисел")
    void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(0, calculator.multiply(0, 5));
        assertEquals(-6, calculator.multiply(-2, 3));
    }
    
    @Test
    @DisplayName("Тест деления двух чисел")
    void testDivide() {
        assertEquals(3, calculator.divide(6, 2));
        assertEquals(0, calculator.divide(0, 5));
        assertEquals(-2, calculator.divide(-6, 3));
    }
    
    @Test
    @DisplayName("Тест деления на ноль - ожидается исключение")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
    }
}