package com.project.taskmanager.controller;

import com.project.taskmanager.entity.User;
import com.project.taskmanager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class LoginController {



    @Autowired
    private UserService userService;

    @GetMapping("/loginUser")
    public String login(){
        return "login";
    }


    @PostMapping("/AuthenticateUser")
    public String authenticateUser(@RequestParam(name = "username") String username,
                                   @RequestParam(name = "password") String password,
                                           Model model) {


        User user = userService.authenticate(username, password);

        if (user != null) {
            // Authentication successful, redirect to a secure area
            model.addAttribute("user",user);
            return "TaskFrontUI";
        } else {
            // Authentication failed, add an error message to the model
            model.addAttribute("errorMessage", "Invalid username or password ... Try Again!");
            return "login"; // Re-render the login page with error
        }
    }


    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/signupUser")
    public String signupUser(@RequestParam(name = "username") String username,
                             @RequestParam(name = "password") String password,
                             Model model){
        if (userService.userExists(username)) {
            // User already exists, add an error message to the model
            model.addAttribute("errorMessage", "Username already taken. Please choose a different username.");
            return "signup"; // Re-render the signup page with error
        } else {
            // Create new user
            userService.createUser(username, password);
            // Redirect to login page or secure area after successful signup
            return "redirect:/loginUser";
        }

    }
}
