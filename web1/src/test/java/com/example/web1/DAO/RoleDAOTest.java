package com.example.web1.DAO;

import com.example.web1.moudle.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleDAOTest {

    @Test
    void addRole() {
        Role.RoleBuilder builder = Role.RoleBuilder.aRole();
        Role role = builder.withRole_id("13141517").withRole_name("user").withDescription("普通用户").build();
        new RoleDAO().addRole(role);
    }

    @Test
    void deleteRole() {
    }

    @Test
    void findRole() {
    }

    @Test
    void getAll() {
    }
}