package com.example.web1.DAO;

import com.example.web1.Utils.JDBCUtils;
import com.example.web1.moudle.Privilege;
import com.example.web1.moudle.Role;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public final class RoleDAO {

    /* 增删查角色*/
    public boolean addRole(Role role) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "insert into role(role_id,role_name,description)values(?,?,?)";
            Object[] params = {role.getRole_id(),role.getRole_name(),role.getDescription()};
            int res = queryRunner.update(sql,params);
            return res > 0?true:false;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("addRole-failed!");
        }
    }

    public boolean deleteRole(Role role) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "delete from role where role_id = ? ";
            Object[] params = {role.getRole_id()};
            int res = queryRunner.update(sql,params);
            return res > 0?true:false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("deleteRole-failed!");
        }
    }

    public Role findRole(Role role) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "select * from role where role_id = ?";
            Object[] params = {role.getRole_id()};
            Role res = (Role) queryRunner.query(sql, new BeanHandler(Role.class),params);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("findRole-failed!");
        }
    }
    public List<Role> getAll(){
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "select * from role";
            List<Role> roles = (List<Role>)queryRunner.query(sql, new BeanListHandler(Role.class));
            return roles;
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("getAll()-failed!");
        }
    }


    public void updatePrivilege(Role role,List<Privilege> privileges) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "delete from role_privilege where role_id = ?";
            queryRunner.update(sql, role.getRole_id());

            sql = "insert into role_privilege (role_id,privilege_id)values(?,?) ";
            for (Privilege privilege:privileges) {
                queryRunner.update(sql,new Object[]{role.getRole_id(),privilege.getPrivilege_id()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("updatePrivilege_failed!");
        }
    }
}
