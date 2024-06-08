package miu.edu.cse.ums.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "Blank-Null-Empty not allowed")
    private String username;
    @Size(min = 8, max = 16)
    private String password;
}
