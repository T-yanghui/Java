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
    @RequestMapping(value = "/")
    public String test(){
        return "login";
    }
}
