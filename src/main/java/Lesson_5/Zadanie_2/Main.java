package Lesson_5.Zadanie_2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PhonesBook phoneBook = new PhonesBook();

        phoneBook.add("Иванов", "+375-44-123-45-67");
        phoneBook.add("Иванов", "+375-44-123-45-68");
        phoneBook.add("Иванов", "+375-44-123-45-69");
        phoneBook.add("Петров", "+375-44-987-65-43");
        phoneBook.add("Петров", "+375-44-987-65-44");
        phoneBook.add("Роналду", "+375-44-555-33-11");
        phoneBook.add("Месси", "+375-44-111-22-33");

        phoneBook.printAll();
        System.out.println();

        System.out.println(" Поиск по фамилии ");
        String surname = "Иванов";
        List<String> numbers = phoneBook.get(surname);
        System.out.println("Телефоны для фамилии '" + surname + "': " + numbers);
        System.out.println("Количество телефонов: " + numbers.size());
        System.out.println();

        surname = "Рональдиньо";
        numbers = phoneBook.get(surname);
        System.out.println("Телефоны для фамилии '" + surname + "': " + numbers);
        System.out.println();

        System.out.println(" Добавление нового номера ");
        phoneBook.add("Иванов", "+375-44-123-45-70");
        System.out.println("Добавлен новый телефон для Иванова");
        phoneBook.printAll();
        System.out.println();

        System.out.println(" Удаление номера ");
        boolean removed = phoneBook.removeNumber("Иванов", "+375-44-123-45-67");
        System.out.println("Номер удален: " + removed);
        phoneBook.printAll();
        System.out.println();

        System.out.println("Удаление записи по фамилии ");
        phoneBook.remove("Роналду");
        System.out.println("Запись для Роналду удалена");
        phoneBook.printAll();
    }
}