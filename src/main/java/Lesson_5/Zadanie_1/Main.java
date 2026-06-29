package Lesson_5.Zadanie_1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        students.add(new Student("Иванов Иван", "Группа А", 2,
                Arrays.asList(4, 5, 3, 4, 5)));
        students.add(new Student("Петров Петр", "Группа Б", 2,
                Arrays.asList(2, 3, 2, 4, 3)));
        students.add(new Student("Сидоров Сидор", "Группа А", 1,
                Arrays.asList(5, 5, 4, 5, 5)));
        students.add(new Student("Роналду Кристиану", "Группа В", 2,
                Arrays.asList(2, 2, 1, 3, 2)));
        students.add(new Student("Лионель Месси", "Группа Б", 3,
                Arrays.asList(4, 4, 3, 4, 4)));

        System.out.println("  Исходный список студентов  ");
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println();


        Student.printStudents(students, 1);
        System.out.println();

        System.out.println("  Удаление студентов со средним баллом < 3  ");
        Student.removeStudentsWithLowAverage(students);
        System.out.println();

        System.out.println("Перевод студентов на следующий курс  ");
        Student.promoteStudents(students);
        System.out.println();

        System.out.println("Обновленный список студентов  ");
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println();

        Student.printStudents(students, 2);
        System.out.println();
        Student.printStudents(students, 3);
    }
}
