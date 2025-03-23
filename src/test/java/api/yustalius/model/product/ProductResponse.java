package api.yustalius.model.product;

import lombok.Data;

@Data
public class ProductResponse{
    private int id;
    private String productName;
    private String description;
    private int price;
}
