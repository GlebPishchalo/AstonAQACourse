package Lesson_3.zadanie1;
public class Zadanie {
    public static void main(String[] args) {

        Dog dog = new Dog("Бобик");
        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Барсик");

        dog.run(150);
        dog.swim(5);

        cat1.run(100);
        cat1.swim(10);

        Bowl bowl = new Bowl(15);

        Cat[] cats = {cat1, cat2};

        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        bowl.info();

        for (Cat cat : cats) {
            System.out.println(cat.isSatiety()
                    ? cat.name + " сыт"
                    : cat.name + " голоден");
        }

        System.out.println("Котов: " + Cat.getCatCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Животных: " + Animal.getAnimalCount());
    }
}