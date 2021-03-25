package com.example.web1.DAO;

import com.example.web1.Utils.JDBCUtils;
import com.example.web1.moudle.Privilege;
import com.example.web1.moudle.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public final class PrivilegeDAO {

    /* 增删查权限*/
        public boolean addPrivilege(Privilege privilege) {
            try {
                QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
                String sql = "insert into privilege(privilege_id,privilege_name,description)values(?,?,?)";
                Object[] params = {privilege.getPrivilege_id(),privilege.getPrivilege_name(),privilege.getDescription()};
                int res = queryRunner.update(sql,params);
                return res > 0?true:false;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("addPrivilege-failed!");
            }
        }

        public boolean deletePrivilege(Privilege privilege) {
            try {
                QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
                String sql = "delete from privilege where privilege_id = ? ";
                Object[] params = {privilege.getPrivilege_id()};
                int res = queryRunner.update(sql,params);
                return res > 0?true:false;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("deletePrivilege-failed!");
            }
        }

        public Privilege findPrivilege(Privilege privilege) {
            try {
                QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
                String sql = "select * from privilege where privilege_id = ?";
                Object[] params = {privilege.getPrivilege_id()};
                Privilege res = (Privilege) queryRunner.query(sql, new BeanHandler(Privilege.class),params);
                return res;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("findPrivilege-failed!");
            }
        }
        public List<Privilege> getAll() {
            try {
                QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
                String sql = "select * from privilege";
                List<Privilege> privileges = (List<Privilege>) queryRunner.query(sql, new BeanListHandler(Privilege.class));
                return privileges;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("getAll()-failed!");
            }
        }
}
