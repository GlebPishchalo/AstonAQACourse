public class Product {
    private String name;
    private String productionDate;
    private String manufacturer;
    private String country;
    private double price;
    private boolean reserved;

    public Product(String name, String productionDate, String manufacturer,
                   String country, double price, boolean reserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.reserved = reserved;
    }

    public void printInfo() {
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна происхождения: " + country);
        System.out.println("Цена: " + price);
        System.out.println("Забронирован: " + reserved);
        System.out.println();
    }
}