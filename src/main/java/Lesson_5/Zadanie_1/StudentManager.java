package Lesson_5.Zadanie_1;

import java.util.*;

class Student {
    private String name;
    private String group;
    private int course;
    private List<Integer> grades; 

   
    public Student(String name, String group, int course, List<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = new ArrayList<>(grades);
    }


    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public List<Integer> getGrades() {
        return grades;
    }


    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }


    public void setCourse(int course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return String.format("Student{name='%s', group='%s', course=%d, avgGrade=%.2f}",
                name, group, course, getAverageGrade());
    }

 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return course == student.course &&
                Objects.equals(name, student.name) &&
                Objects.equals(group, student.group) &&
                Objects.equals(grades, student.grades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, group, course, grades);
    }




  
    public static void removeStudentsWithLowAverage(Set<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageGrade() < 3.0) {
                iterator.remove();
                System.out.println("Удален студент: " + student.getName() + " (средний балл: " + student.getAverageGrade() + ")");
            }
        }
    }

    public static void promoteStudents(Set<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3.0) {
                int newCourse = student.getCourse() + 1;
                student.setCourse(newCourse);
                System.out.println("Студент " + student.getName() + " переведен на " + newCourse + " курс");
            }
        }
    }
       public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты на " + course + " курсе:");
        boolean found = false;
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println("  - " + student.getName() +
                        " (группа: " + student.getGroup() + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("  Студентов на " + course + " курсе не найдено.");
        }
    }
}