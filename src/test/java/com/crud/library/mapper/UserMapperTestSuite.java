package com.crud.library.mapper;

import com.crud.library.domain.User;
import com.crud.library.domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTestSuite {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testMapToUserDto() {
        //Given
        User user = new User(1, "John", "Smith", LocalDate.now());

        //When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals(1, userDto.getId());
        assertEquals("John", userDto.getFirstName());
        assertEquals("Smith", userDto.getLastName());
    }

    @Test
    public void testMapToUser() {
        //Given
        UserDto userDto = new UserDto(1, "John", "Smith", LocalDate.now());

        //When
        User user = userMapper.mapToUser(userDto);

        //Then
        assertEquals(1, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals(LocalDate.now(), user.getAccCreated());
    }

    @Test
    public void testMapToUserDtoList() {
        //Given
        User user1 = new User(1, "John", "Smith", LocalDate.now());
        User user2 = new User(2, "Harry", "Potter", LocalDate.now().minusDays(10));
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        //When
        List<UserDto> list = userMapper.mapToUserDtoList(users);

        //Then
        assertEquals(2, list.size());
        assertEquals("Smith", list.get(0).getLastName());
        assertEquals("Potter", list.get(1).getLastName());
    }
}
