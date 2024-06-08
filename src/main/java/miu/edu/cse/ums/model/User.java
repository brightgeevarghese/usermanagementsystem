package miu.edu.cse.ums.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(unique = true, nullable = false, updatable = true)
    private String username;
    @Size(min = 8, max = 16)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
