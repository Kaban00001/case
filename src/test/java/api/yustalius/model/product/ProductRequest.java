package api.yustalius.model.product;

import lombok.Data;

@Data
public class ProductRequest {
    private String productName;
    private String description;
    private int price;
}
