public class Lesson_2 {
    public static void main(String[] args) {

        Product[] productsArray = new Product[5];

        productsArray[0] = new Product(
                "Samsung S25 Ultra",
                "01.02.2025",
                "Samsung Corp.",
                "Korea",
                5599,
                true
        );

        productsArray[1] = new Product(
                "iPhone 17",
                "10.09.2025",
                "Apple Inc.",
                "USA",
                6666,
                false
        );

        productsArray[2] = new Product(
                "Xiaomi Poco x3",
                "15.01.2025",
                "Xiaomi inc.",
                "China",
                3333,
                true
        );

        productsArray[3] = new Product(
                "Google Pixel 6",
                "20.04.2023",
                "Google inc.",
                "USA",
                3000,
                false
        );

        productsArray[4] = new Product(
                "Samsung a52",
                "05.05.2025",
                "Samsung Corp.",
                "Korea",
                1234,
                true
        );

        for (Product product : productsArray) {
            product.printInfo();
        }

        Park park = new Park();

        Park.Attraction attraction1 =
                park.new Attraction("Super8", "10:00-22:00", 15);

        

        attraction1.printInfo();
      
    }
}