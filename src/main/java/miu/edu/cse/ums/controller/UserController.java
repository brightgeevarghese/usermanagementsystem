package miu.edu.cse.ums.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import miu.edu.cse.ums.dto.request.UserRequestDto;
import miu.edu.cse.ums.dto.response.UserResponseDto;
import miu.edu.cse.ums.exception.user.UserNotFoundException;
import miu.edu.cse.ums.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        Optional<UserResponseDto> responseDto = userService.addUser(userRequestDto);
        if (responseDto.isPresent()) {
            return ResponseEntity.ok(responseDto.get());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        Optional<List<UserResponseDto>> responseDtos = userService.findAllUsers();
        if (responseDtos.isPresent()) {
            return ResponseEntity.ok(responseDtos.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String username) {
        Optional<UserResponseDto> responseDto = userService.getUser(username);
        if (responseDto.isPresent()) {
            return ResponseEntity.ok(responseDto.get());
        }
        throw new UserNotFoundException(username + " not found");
//        return ResponseEntity.notFound().build();
    }

    //partial update: use @PatchMapping
    @PatchMapping("/{username}")
    public ResponseEntity<UserResponseDto> updatePassword(@PathVariable("username") String userName, @RequestBody UserRequestDto userRequestDto) {
        Optional<UserResponseDto> responseDto = userService.updatePassword(userName, userRequestDto);
        if (responseDto.isPresent()) {
            return ResponseEntity.ok(responseDto.get());
        }
        return ResponseEntity.notFound().build();
    }
    //full update: use @PutMapping
    @PutMapping("/{username}")
    public ResponseEntity<UserResponseDto> updateUsernameAndPassword(
            @PathVariable("username") String userName,
            @RequestBody UserRequestDto userRequestDto
    ) {
        Optional<UserResponseDto> responseDto = userService.updateUsernameAndPassword(userName, userRequestDto);
        if (responseDto.isPresent()) {
            return ResponseEntity.ok(responseDto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/username")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        Optional<UserResponseDto> responseDto = userService.deleteUser(username);
        if (responseDto.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
    }
    //Check it
//    @DeleteMapping("/{username}")
//    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable String username) {
//        Optional<UserResponseDto> responseDto = userService.deleteUser(username);
//        if (responseDto.isPresent()) {
//            return new ResponseEntity<>(responseDto.get(), HttpStatus.ACCEPTED);
//        }
//        return ResponseEntity.notFound().build();
//    }
}
