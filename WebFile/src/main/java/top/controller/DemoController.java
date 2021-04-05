package top.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.entity.User;
import top.service.UserService;

@Controller
public class DemoController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/test")
    public String test(){
        System.out.println("DemoController");
        User user =new User();
        user.setUsername("123");
        user.setPhonenumber("123");
        user.setPassword("123a");
        userService.saveUser(user);
        return "index";
    }
}
