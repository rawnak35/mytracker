package com.finTrack.mytracker.mapper;


import com.finTrack.mytracker.dto.UserDto;
import com.finTrack.mytracker.entity.User;

public class UserMapper {

    public static User mapToUser(UserDto userDto) {
        return new User(null, userDto.getUsername(), userDto.getFirstName(), userDto.getLastName(), userDto.getBirthDate(), userDto.getCurrency());
    }

    public static UserDto mapToUserDto(User user) {
        return new UserDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getCurrency());
    }
}
