package com.finTrack.mytracker.service;

import com.finTrack.mytracker.dto.UserDto;

import java.util.List;

public interface UserService {
   UserDto createUser(UserDto userDto);
   UserDto getUserByUsername(String username);
   UserDto getUserById(Long id);
   List<UserDto> getAllUsers();
   UserDto updateUser(String username, UserDto updatedUser);
   void deleteUser(Long id);
}
