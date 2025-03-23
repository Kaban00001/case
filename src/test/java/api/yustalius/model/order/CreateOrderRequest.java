package api.yustalius.model.order;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private int userId;
    private List<Product> products;

    @Data
    public static class Product {
        private int productId;
        private int quantity;
        private double price;
    }
}