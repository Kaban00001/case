package todo.model;

import lombok.Data;

@Data
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private User user;

    @Data
    public static class User {
        private String email;
        private int id;
    }
}
