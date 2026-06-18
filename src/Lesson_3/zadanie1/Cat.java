package Lesson_3.zadanie1;


public class Cat extends Animal {

    private static int catCount = 0;

    private final int maxRun = 200;
    private boolean satiety = false;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= maxRun) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    public void eat(Bowl bowl) {
        if (bowl.getFood() >= 10) { // кот ест фиксированно 10
            bowl.decreaseFood(10);
            satiety = true;
            System.out.println(name + " поел и сыт.");
        } else {
            System.out.println(name + " не стал есть — мало еды.");
        }
    }

    public boolean isSatiety() {
        return satiety;
    }

    public static int getCatCount() {
        return catCount;
    }
}