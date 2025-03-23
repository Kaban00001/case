package api.yustalius.model.auth;

import lombok.Data;

@Data
public class RegisterResponse {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
}
