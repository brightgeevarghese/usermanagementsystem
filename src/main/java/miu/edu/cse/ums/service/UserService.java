package miu.edu.cse.ums.service;

import miu.edu.cse.ums.dto.request.UserRequestDto;
import miu.edu.cse.ums.dto.response.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //CRUD
    Optional<UserResponseDto> addUser(UserRequestDto userRequestDto);
    Optional<UserResponseDto> updatePassword(String username, UserRequestDto userRequestDto);
    Optional<UserResponseDto> updateUsernameAndPassword(String username, UserRequestDto userRequestDto);
    Optional<UserResponseDto> getUser(String username);
    Optional<List<UserResponseDto>> findAllUsers();
    Optional<UserResponseDto> deleteUser(String username);
}
