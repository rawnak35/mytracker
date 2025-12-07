package com.finTrack.mytracker.service.impl;

import com.finTrack.mytracker.dto.UserDto;
import com.finTrack.mytracker.entity.User;
import com.finTrack.mytracker.exception.ResourceAlreadyExistsException;
import com.finTrack.mytracker.exception.ResourceNotFoundException;
import com.finTrack.mytracker.mapper.UserMapper;
import com.finTrack.mytracker.repository.UserRepository;
import com.finTrack.mytracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User existUser = userRepository.findByUsername(userDto.getUsername());
        if (existUser != null) {
            throw new ResourceAlreadyExistsException("username already in use.");
        }
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User does not exist with given username : " + username);
        }
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not exist with given username : " + id));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(String username, UserDto updatedUserDto) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User does not exist with given username : " + username);
        }
        user.setFirstName(updatedUserDto.getFirstName());
        user.setLastName(updatedUserDto.getLastName());
        user.setBirthDate(updatedUserDto.getBirthDate());
        user.setCurrency(updatedUserDto.getCurrency());
        return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id : " + id));
        userRepository.delete(user);
    }
}
