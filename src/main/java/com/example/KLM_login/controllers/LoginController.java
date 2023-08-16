package com.example.KLM_login.controllers;

import ch.qos.logback.core.model.Model;
import com.example.KLM_login.models.User;
import com.example.KLM_login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.Map;
import java.util.Objects;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping("/")
    public String index(){

        String username = "admin";

        User existingUser = this.userRepository.findByUsername(username);
        if (existingUser == null) {
            String checkIn = "INSERT INTO user(password, username) VALUES ('admin', 'admin')";
            jdbcTemplate.execute(checkIn);
        }

        String sql = "UPDATE user SET password = ? WHERE username = ?";
        jdbcTemplate.update(sql, "admin", "admin");

        return "index";
    }

@PostMapping("/login")
public String login(@RequestParam String username, @RequestParam String password, Model model) {
    User user = this.userRepository.findByUsernameAndPassword(username, password);

    if(user == null){
        return "index";
    }
    return "home";
    }

@PostMapping("/changePassword")
public String changePassword(@RequestParam String newPassword, @RequestParam String newPasswordCheck, Model model){
        if (!Objects.equals(newPassword, newPasswordCheck)){
            return "wrong";
        }
    String username = "admin";

    String sql = "UPDATE user SET password = ? WHERE username = ?";
    jdbcTemplate.update(sql, newPassword, username);

    return "index";
    }
}
