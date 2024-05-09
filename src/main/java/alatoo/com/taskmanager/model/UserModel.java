package alatoo.com.taskmanager.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserModel {
    Long id;
    @NotEmpty(message = "Username cannot be empty")
    String username;
    @NotEmpty(message = "Password cannot be empty")
    String password;
    String email;
}
