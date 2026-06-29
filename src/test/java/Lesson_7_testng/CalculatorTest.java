package Lesson_7_testng;

import org.testng.annotations.*;
import static org.testng.Assert.*;


public class CalculatorTest {
    private Calculator calculator;
    
    @BeforeMethod
    public void setUp() {
        calculator = new Calculator();
    }
    
    @Test(description = "Тест сложения двух чисел")
    public void testAdd() {
        assertEquals(calculator.add(2, 3), 5);
        assertEquals(calculator.add(-1, 1), 0);
        assertEquals(calculator.add(-2, -3), -5);
    }
    
    @Test(description = "Тест вычитания двух чисел")
    public void testSubtract() {
        assertEquals(calculator.subtract(5, 2), 3);
        assertEquals(calculator.subtract(1, 3), -2);
        assertEquals(calculator.subtract(5, 5), 0);
    }
    
    @Test(description = "Тест умножения двух чисел")
    public void testMultiply() {
        assertEquals(calculator.multiply(2, 3), 6);
        assertEquals(calculator.multiply(0, 5), 0);
        assertEquals(calculator.multiply(-2, 3), -6);
    }
    
    @Test(description = "Тест деления двух чисел")
    public void testDivide() {
        assertEquals(calculator.divide(6, 2), 3);
        assertEquals(calculator.divide(0, 5), 0);
        assertEquals(calculator.divide(-6, 3), -2);
    }
    
    @Test(expectedExceptions = ArithmeticException.class, description = "Тест деления на ноль")
    public void testDivideByZero() {
        calculator.divide(5, 0);
    }
}