package api.demowebshop.model;

import lombok.Data;

@Data
public class AddToCartResponse {
    private boolean success;
    private String message;
    private String updatetopcartsectionhtml;
    private String updateflyoutcartsectionhtml;
}
