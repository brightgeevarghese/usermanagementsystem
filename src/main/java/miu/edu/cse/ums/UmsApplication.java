package miu.edu.cse.ums;

import lombok.RequiredArgsConstructor;
import miu.edu.cse.ums.dto.request.UserRequestDto;
import miu.edu.cse.ums.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class UmsApplication implements CommandLineRunner {

    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(UmsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        UserRequestDto userRequestDto = new UserRequestDto();
//        userRequestDto.setUsername("admin");
//        userRequestDto.setPassword("12345678");
//        System.out.println(
//                userService.addUser(userRequestDto) + " is saved."
//        );
//        UserRequestDto userRequestDto2 = new UserRequestDto("user1", "password1");
//        System.out.println(
//                userService.updateUsernameAndPassword("admin", userRequestDto2)
//        );
    }
}
