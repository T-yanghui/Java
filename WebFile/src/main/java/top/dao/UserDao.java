package top.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import top.controller.MyException;
import top.entity.User;

@Repository
public final class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user){
        String sql = "insert into user_info(username,phonenumber,password)values(?,?,?)";
        Object[] params = {user.getUsername(),user.getPhonenumber(),user.getPassword()};
//        try{
            jdbcTemplate.update(sql,params);
//        }catch (RuntimeException e){jdbc:mariadb://192.168.4.1:3306/webfilejdbc:mariadb://192.168.4.1:3306/webfilejdbc:mariadb://192.168.4.1:3306/webfile
//            throw new MyException("Save failed");
//        }
    }
}
