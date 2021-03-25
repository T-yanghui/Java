package com.example.web1.Service;

import com.example.web1.Utils.UUID20;
import com.example.web1.moudle.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void addUser() {
        User.UserBuilder builder = User.UserBuilder.anUser();
        User user = builder.withUser_id(UUID20.getUUID()).withUser_name("Yu").withPassword("123456").build();
        UserService userService = new UserService();
        userService.addUser(user);
    }

    @Test
    void addFile() {
    }

    @Test
    void deleteUser() {
        User.UserBuilder builder = User.UserBuilder.anUser();
        User user = builder.withUser_id(UUID20.getUUID()).withUser_name("Yu").withPassword("123456").build();
        UserService userService = new UserService();
        userService.deleteUser(user);
    }

    @Test
    void findUser() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getRoles() {
    }

    @Test
    void getFiles() {
    }

    @Test
    void updateRole() {
    }

    @Test
    void testFindUser() {
        User.UserBuilder builder = User.UserBuilder.anUser();
        User user = builder.withUser_id(UUID20.getUUID()).withUser_name("Yu").withPassword("123456").build();
        UserService userService = new UserService();
        System.out.println(userService.findUser(user));
    }
}