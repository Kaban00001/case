package todo.model;

import lombok.Data;

@Data
public class TodoRequest {
    private String title;
    private String description;
    private String date;
    private String time;
    private boolean checked;
}
