package api.yustalius.model.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private int orderId;
    private int userId;
    private List<Product> products;
    private String timestamp;
    private String status;

    @Data
    public static class Product {
        private int productId;
        private int quantity;
        private double price;
    }
}