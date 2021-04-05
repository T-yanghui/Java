package top.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.dao.UserDao;
import top.entity.User;
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public void saveUser(User user){
        userDao.save(user);
    }
}
