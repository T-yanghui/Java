package top.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

    @RequestMapping("/Demo")
    public String index(){
        System.out.println("DemoController");
        return "index";
    }
}
