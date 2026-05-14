package Basic;

import java.util.List;
import java.util.stream.Collectors;

public class Problem11 {
    /***
     * P8 — Product catalogue — discount price list
     * A real-world e-commerce pattern: filter in-stock products above a price threshold, apply a discount, format as a string.
     *
     * Threshold value  is 10000 , if the value of product is greter than 10k apply 20% discount
     *record Product(String name, double price, boolean inStock) {}
     *
     * List.of( new Product("Laptop", 85000, true), new Product("Mouse", 1200, true), new Product("Monitor", 32000, false), new Product("Keyboard", 4500, true), new Product("Webcam", 3800, false) )
     */


    public static void main(String[] args) {

        record Product(String name, double price, boolean inStock) {
        }

        List<Product> productList = List.of(
                new Product("Laptop", 85000, true),
                new Product("Mouse", 1200, true),
                new Product("Monitor", 32000, false),
                new Product("Keyboard", 4500, true),
                new Product("Webcam", 3800, false)
        );

        List<String> discountedProducts = productList.stream()
                .filter(product ->
                        product.inStock() && product.price() > 100)
                .map(product -> {
                    double discountedPrice =
                            product.price() * 0.80; // 20% discount

                    return product.name() +
                            " -> ₹" + discountedPrice;
                })
                .collect(Collectors.toList());

        discountedProducts.forEach(System.out::println);
    }
}