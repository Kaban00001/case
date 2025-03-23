package api.yustalius.model.user;

import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
}

