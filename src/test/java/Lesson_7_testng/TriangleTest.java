package Lesson_7_testng;

import org.testng.annotations.*;
import static org.testng.Assert.*;


public class TriangleTest {
    private Triangle triangle;
    
    @BeforeMethod
    public void setUp() {
        triangle = new Triangle();
    }
    
    @Test(description = "Тест площади треугольника с положительными значениями")
    public void testCalculateArea() {
        assertEquals(triangle.calculate(5, 4), 10.0);
        assertEquals(triangle.calculate(5, 3), 7.5);
        assertEquals(triangle.calculate(10, 5), 25.0);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class, description = "Тест площади треугольника с основанием 0")
    public void testZeroBase() {
        triangle.calculate(0, 5);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class, description = "Тест площади треугольника с высотой 0")
    public void testZeroHeight() {
        triangle.calculate(5, 0);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class, description = "Тест площади треугольника с отрицательными значениями")
    public void testNegativeValues() {
        triangle.calculate(-5, 4);
    }
}