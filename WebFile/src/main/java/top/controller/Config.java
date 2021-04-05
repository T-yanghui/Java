package top.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.entity.User;

@Configuration
public class Config {
    @Bean
    public User user(){
        return new User();
    }
}
