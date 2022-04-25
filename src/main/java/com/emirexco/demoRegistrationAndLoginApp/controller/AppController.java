package com.emirexco.demoRegistrationAndLoginApp.controller;

import com.emirexco.demoRegistrationAndLoginApp.model.User;
import com.emirexco.demoRegistrationAndLoginApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/home")
    public String viewHomePage(){
        return "home";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "registration_successful";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("usersList", users);
        return "list_users";
    }



}
