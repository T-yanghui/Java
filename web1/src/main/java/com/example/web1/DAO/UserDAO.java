package com.example.web1.DAO;

import com.example.web1.Utils.JDBCUtils;
import com.example.web1.Utils.UUID20;
import com.example.web1.moudle.Role;
import com.example.web1.moudle.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

/* 增删查用户*/
public final class UserDAO {


    public boolean addUser(User user) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "insert into user(user_id,user_name,password)values(?,?,?)";
            Object[] params = {user.getUser_id(),user.getUser_name(),user.getPassword()};
            int res = queryRunner.update(sql,params);
            return res > 0 ? true:false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("addUser-failed!");
        }
    }
    public boolean addFile(User user,String File_name){
        try{
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "insert into user_file (user_id,file_name,direction)values(?,?,?)";
            Object[] params = {user.getUser_id(),File_name, UUID20.getUUID()};
            int res = queryRunner.update(sql,params);
            return res > 0?true:false;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("addFile_failed!");
        }
    }

    public boolean deleteUser(User user) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "delete from user where user_name = ? ";
            Object[] params = {user.getUser_name()};
            int res = queryRunner.update(sql,params);
            return res > 0?true:false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("deleteUser-failed!");
        }
    }

    public User findUser(User user) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "select * from user where user_name = ?";
            Object[] params = {user.getUser_name()};
            User res = (User) queryRunner.query(sql, new BeanHandler(User.class),params);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("findUser-failed!");
        }
    }
        public List<User> getAll(){
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "select * from user";
            List<User> users = (List<User>)queryRunner.query(sql, new BeanListHandler(User.class));
            return users;
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("getAll()-failed!");
        }
    }
    public List<Role> getRoles(User user){
        try{
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "select r.* from role r,user_role ur where ur.user_id = ? and r.role_id = ur.role_id";
            Object[] params = {user.getUser_id()};
            List<Role> roles = (List<Role>) queryRunner.query(sql, new BeanListHandler(Role.class),params);
            return roles;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("getRoles_Failed!");
        }
    }
    public List<Map<String,Object>> getFiles(User user){
        try{
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "select file_name,direction from user_file where user_id = ?";
            Object[] params = {user.getUser_id()};
            List<Map<String,Object>> res = (List<Map<String, Object>>) queryRunner.query(sql,new MapListHandler(),params);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("getFiles_failed!");
        }
    }
    public void updateRole(User user,List<Role> roles){
        try{
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "delete from user_role where user_id = ?";
            queryRunner.update(sql,user.getUser_id());
            sql = "insert into user_role (user_id,role_id)values(?,?)";
            for(Role role : roles){
                queryRunner.update(sql,new Object[]{user.getUser_id(),role.getRole_id()});
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("updateRole_failed");
        }
    }
    public void updateUser(User user){
        try{
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "update user set password=? where user_name=?";
            Object[] params = {user.getPassword(),user.getUser_name()};
            queryRunner.update(sql,params);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("updateUser_failed!");
        }
    }
}