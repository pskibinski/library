package com.crud.library.mapper;

import com.crud.library.domain.User;
import com.crud.library.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getAccCreated());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getAccCreated(),
                user.getBorrowedBooks().stream().map(b -> b.getId()).collect(Collectors.toList()));
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream().
                map(u -> new UserDto(u.getId(), u.getFirstName(), u.getLastName(), u.getAccCreated(),
                        u.getBorrowedBooks().stream().map(b -> b.getId()).collect(Collectors.toList()))).
                collect(Collectors.toList());
    }
}
