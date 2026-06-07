import java.util.Arrays;

public class Lesson1 {
    public static void main(String[] args) {
        int number = 1;
        printThreeWords();
        printSeparator();
        checkSumSign();
        printSeparator();
        printColor();
        printSeparator();
        compareNumbers();
        printSeparator();
        System.out.println(checkRangeNumbers(10, 20));
        printSeparator();
         String result = getTypeNumber(number);
        System.out.println("Число " + number + " — " + result);
        printSeparator();
        System.out.println(getTypeBooleanNumber(1));
        printSeparator();
        cloneString("Hello", 2);
        printSeparator();
        System.out.println(typeOfYear(2000));
        printSeparator();
        System.out.println(Arrays.toString(arrSwitchNumbers(new int[]{1, 0, 1, 0, 1}) ));
        printSeparator();
        System.out.println(Arrays.toString(arr1to100(new int[100]) ));
        printSeparator();
        System.out.println(Arrays.toString(arrUmnNa2(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1}) ));
        printSeparator();
        int[][] matrix = createDiagonalMatrix(5);
         for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        printSeparator();
        System.out.println(Arrays.toString(arrToInitialValue(6, 9)));
    }
    public static void printSeparator() {
    System.out.println("--------------------------------------------------\n"); 
   
}
    public static void printThreeWords(){
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }
    public static int checkSumSign(){
        int a = 1;
        int b = 2;
        if (a + b >= 0){
            System.out.println("Сумма положительная");
        }
        else{
            System.out.println("Сумма отрицательная");
        }
        return a + b;
    }
    public static int printColor(){
        int value = 10;
        if (value <= 0){
            System.out.println("Красный");
        }
        else if (value > 0 && value <= 100){
            System.out.println("Желтый");
        }
        else{
            System.out.println("Зеленый");
        }
        return value;
    }
    public static int compareNumbers(){
        int a = 1;
        int b = 2;
        if (a >= b){
            System.out.println("a >= b");
        }
        else{
            System.out.println("a < b");
        }
        return a - b;
    }
    public static boolean checkRangeNumbers(int a, int b){
        if(a+b >= 10 && a+b <= 20){
            return true;
        }
        else{
            return false;
        }
        
    }
    public static String getTypeNumber(int a){
        if (a>=0){
            return "положительное";
        }
        else{
            return "отрицательное";
        }

    }
    public static boolean getTypeBooleanNumber(int a){
        if (a>=0){
            return true;
        }
        else{
            return false;
        }
    }
    public static String cloneString(String a, int b){
        for (int i = 0; i < b; i++){
            System.out.println(a+" ");
        }
        return a;
    }
    public static boolean typeOfYear(int a){
        if (a%4 == 0 && a%100 != 0 || a%400 == 0){
            return true;
        }
        else{
            return false;
        }
    }
 public static int[] arrSwitchNumbers(int[] array) {
    for (int i = 0; i < array.length; i++) {
        if (array[i] == 0) {
            array[i] = 1; 
        } else {
            array[i] = 0; 
        }
    }
    return array;
}
public static int[] arr1to100(int[] array) {
    for (int i = 0; i < array.length; i++) {
        array[i] = i + 1;
    }
    return array;
}
public static int[] arrUmnNa2 (int[] array) {
    for (int i = 0; i < array.length; i++) {
        if (array[i] < 6) {
            array[i] = array[i] * 2;
        }
    }
    return array;
}
 public static int[][] createDiagonalMatrix(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            array[i][i] = 1;                
            array[i][size - 1 - i] = 1;     
        }

        return array;
    }
public static int[] arrToInitialValue(int len,int initialValue) {
    int[] array = new int[len];
    for (int i = 0; i < array.length; i++) {
        array[i] = initialValue;
    }
    return array;
}
}