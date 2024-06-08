package miu.edu.cse.ums.service.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.cse.ums.dto.request.UserRequestDto;
import miu.edu.cse.ums.dto.response.UserResponseDto;
import miu.edu.cse.ums.model.User;
import miu.edu.cse.ums.repository.UserRepository;
import miu.edu.cse.ums.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<UserResponseDto> addUser(UserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);
        User savedUser = userRepository.save(user);
        UserResponseDto userResponseDto = modelMapper.map(savedUser, UserResponseDto.class);
        return Optional.of(userResponseDto);
    }

    @Override
    public Optional<UserResponseDto> updatePassword(String username, UserRequestDto userRequestDto) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent() && username.equals(userRequestDto.getUsername())) {
            modelMapper.map(userRequestDto, foundUser.get());
            User savedUser = userRepository.save(foundUser.get());
            UserResponseDto userResponseDto = modelMapper.map(savedUser, UserResponseDto.class);
            return Optional.of(userResponseDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserResponseDto> updateUsernameAndPassword(String username, UserRequestDto userRequestDto) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent()) {
            modelMapper.map(userRequestDto, foundUser.get());
            User savedUser = userRepository.save(foundUser.get());
            UserResponseDto userResponseDto = modelMapper.map(savedUser, UserResponseDto.class);
            return Optional.of(userResponseDto);
        }
//        if (foundUser.isPresent()) {
//            User user = foundUser.get();
//            user.setPassword(userRequestDto.getPassword());
//            user.setUsername(userRequestDto.getUsername());
//            User newUser = modelMapper.map(userRequestDto, User.class);
//            newUser.setUserId(foundUser.get().getUserId());
//            newUser.setUsername("testuser");
//            newUser.setPassword("testpassword");
//            User savedUser = userRepository.save(newUser);
//            UserResponseDto userResponseDto = modelMapper.map(savedUser, UserResponseDto.class);
//            return Optional.of(userResponseDto);
//        }
        return Optional.empty();
    }

    @Override
    public Optional<UserResponseDto> getUser(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent()) {
            UserResponseDto userResponseDto = modelMapper.map(foundUser.get(), UserResponseDto.class);
            return Optional.of(userResponseDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<UserResponseDto>> findAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return Optional.empty();
        }
        List<UserResponseDto> userResponseDtos = modelMapper.map(users, new TypeToken<List<UserResponseDto>>() {}.getType());
        return Optional.of(userResponseDtos);
    }

    @Override
    public Optional<UserResponseDto> deleteUser(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent()) {
            userRepository.delete(foundUser.get());
            UserResponseDto userResponseDto = modelMapper.map(foundUser.get(), UserResponseDto.class);
            return Optional.of(userResponseDto);
        }
        return Optional.empty();
//        userRepository.findByUsername(username)
//                .ifPresent(
//                        user -> {
//                            userRepository.delete(user);
//                        });
//        Optional<User> foundUser = userRepository.findByUsername(username);
//        if (foundUser.isPresent()) {
//            userRepository.delete(foundUser.get());
//            return true;
//        }
//        return false;
    }
}
