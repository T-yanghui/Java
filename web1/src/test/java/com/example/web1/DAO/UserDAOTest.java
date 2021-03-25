package com.example.web1.DAO;

import com.example.web1.moudle.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {


    @Test
    void addUser() {

    }


    @Test
    void deleteUser() {

    }

    @Test
    void findUser() {
     User.UserBuilder builder = User.UserBuilder.anUser();
     User user = builder.withUser_id("2").build();
     User res = new UserDAO().findUser(user);
     System.out.println(user);
     System.out.println(res);
     System.out.println(res.getClass());
     System.out.println(res.toString());
    }

    @Test
    void getAll() {
      System.out.println(new UserDAO().getAll());
    }

    @Test
    void addFile() {
        User.UserBuilder builder = User.UserBuilder.anUser();
        User user = builder.withUser_id("1").build();
        System.out.println(new UserDAO().addFile(user,"Economics"));
    }

    @Test
    void getFiles() {
        User.UserBuilder builder = User.UserBuilder.anUser();
        User user = builder.withUser_id("1").build();
        List<Map<String,Object>> res = new UserDAO().getFiles(user);
        System.out.println("File_name        direction");
        for(Map map : res){
            System.out.print(map.get("File_name"));
            System.out.print("\t\t");
            System.out.println(map.get("direction"));
        }
    }
}