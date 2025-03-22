package todo.model;

import lombok.Data;

@Data
public class TodoResponse {
    private String title;
    private String description;
    private String date;
    private String time;
    private boolean checked;
    private int id;
    private int userId;
}
