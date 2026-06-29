package Lesson_7_junit_5;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class TriangleTest {
    private Triangle triangle;
    
    @BeforeEach
    void setUp() {
        triangle = new Triangle();
    }
    
    @Test
    @DisplayName("Тест площади треугольника с положительными значениями")
    void testCalculateArea() {
        assertEquals(10.0, triangle.calculate(5, 4));
        assertEquals(7.5, triangle.calculate(5, 3));
        assertEquals(25.0, triangle.calculate(10, 5));
    }
    
    @Test
    @DisplayName("Тест площади треугольника с основанием 0")
    void testZeroBase() {
        assertThrows(IllegalArgumentException.class, () -> triangle.calculate(0, 5));
    }
    
    @Test
    @DisplayName("Тест площади треугольника с высотой 0")
    void testZeroHeight() {
        assertThrows(IllegalArgumentException.class, () -> triangle.calculate(5, 0));
    }
    
    @Test
    @DisplayName("Тест площади треугольника с отрицательными значениями")
    void testNegativeValues() {
        assertThrows(IllegalArgumentException.class, () -> triangle.calculate(-5, 4));
        assertThrows(IllegalArgumentException.class, () -> triangle.calculate(5, -4));
        assertThrows(IllegalArgumentException.class, () -> triangle.calculate(-5, -4));
    }
}