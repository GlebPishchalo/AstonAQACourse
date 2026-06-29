package Lesson_5.Zadanie_2;

import java.util.*;

public class PhonesBook {
    private Map<String, List<String>>  PhonesBook;

    public  PhonesBook() {
        this.PhonesBook = new HashMap<>();
    }

    public void add(String surname, String phoneNumber) {
        if ( PhonesBook.containsKey(surname)) {
            List<String> numbers =  PhonesBook.get(surname);
            if (!numbers.contains(phoneNumber)) {
                numbers.add(phoneNumber);
            }
        } else {
            List<String> numbers = new ArrayList<>();
            numbers.add(phoneNumber);
             PhonesBook.put(surname, numbers);
        }
    }

    public List<String> get(String surname) {
        return  PhonesBook.getOrDefault(surname, new ArrayList<>());
    }

    public Map<String, List<String>> getAll() {
        return  PhonesBook;
    }

    public boolean removeNumber(String surname, String phoneNumber) {
        if ( PhonesBook.containsKey(surname)) {
            List<String> numbers =  PhonesBook.get(surname);
            boolean removed = numbers.remove(phoneNumber);
            if (numbers.isEmpty()) {
                 PhonesBook.remove(surname);
            }
            return removed;
        }
        return false;
    }

    public boolean remove(String surname) {
        return  PhonesBook.remove(surname) != null;
    }

    public void printAll() {
        if ( PhonesBook.isEmpty()) {
            System.out.println("Телефонный справочник пуст.");
            return;
        }
        System.out.println("=== Телефонный справочник ===");
        for (Map.Entry<String, List<String>> entry :  PhonesBook.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
        }
        System.out.println("==============================");
    }
}