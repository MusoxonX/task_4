package uz.pdp.task_4.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
