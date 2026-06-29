package Lesson_4;

public class Lesson4 {

    public static void sumArray(String[][] array)
            throws MyArraySizeException, MyArrayDataException {

        if (array.length != 4) {
            throw new MyArraySizeException("size");
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("size");
            }
        }

        int sum = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i + " " + j);
                }
            }
        }

        System.out.println(sum);
    }

    public static void main(String[] args) {

        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            sumArray(array);
        } catch (MyArraySizeException e) {
            System.out.println("Неверный размер");
        } catch (MyArrayDataException e) {
            System.out.println("Данные неверны " + e.getMessage());
        }

        try {
            int[] a = {1, 2, 3};
            System.out.println(a[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Выход за границы");
        }
    }
}

class MyArraySizeException extends Exception {
    public MyArraySizeException(String m) {
        super(m);
    }
}

class MyArrayDataException extends Exception {
    public MyArrayDataException(String m) {
        super(m);
    }
}