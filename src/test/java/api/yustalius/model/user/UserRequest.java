package api.yustalius.model.user;

import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private int age;
}
