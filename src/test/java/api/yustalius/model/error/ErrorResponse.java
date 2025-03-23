package api.yustalius.model.error;

import lombok.Data;

@Data
public class ErrorResponse {
    private String errorCode;
    private String message;
}
