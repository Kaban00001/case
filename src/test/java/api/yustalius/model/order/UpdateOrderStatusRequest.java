package api.yustalius.model.order;

import lombok.Data;

@Data
public class UpdateOrderStatusRequest {
    private String status;
}