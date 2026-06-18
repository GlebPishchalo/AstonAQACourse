package Lesson_3.zadanie2;

public class Zadanie2 {
    public static void main(String[] args) {

        Shape circle = new Circle(5, "red", "black");
        Shape rectangle = new Rectangle(4, 6, "blue", "green");
        Shape triangle = new Triangle(3, 4, 5, "yellow", "black");

        circle.printInfo();
        rectangle.printInfo();
        triangle.printInfo();
    }
}