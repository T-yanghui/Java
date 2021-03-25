package com.example.web1.Service;

import com.example.web1.DAO.UserDAO;
import com.example.web1.moudle.Role;
import com.example.web1.moudle.User;

import java.util.List;
import java.util.Map;

public class UserService {
    UserDAO userDAO = new UserDAO();

        //添加用户
    public void addUser(User user){
        userDAO.addUser(user);
    }

        //添加文件
    public void addFile(User user,String File_name){
        userDAO.addFile(user,File_name);
    }

        //用户名删除用户
    public void deleteUser(User user){
        userDAO.deleteUser(user);
    }

        //根据用户名查找用户
    public  User findUser(User user){
        return userDAO.findUser(user);
    }

        //查找所有用户
    public List<User> getAll(){
        return userDAO.getAll();
    }

        //获取用户所有角色
    public List<Role> getRoles(User user){
        return userDAO.getRoles(user);
    }

        //获取用户所有文件
    public List<Map<String,Object>> getFiles(User user){
        return userDAO.getFiles(user);
    }

        //更新用户角色
    public void updateRole(User user,List<Role> roles){
        userDAO.updateRole(user,roles);
    }

        //更新用户
    public void updateUser(User user){userDAO.updateUser(user);}
}
