package com.example.pp_3_1_2_springboot.controller;

import com.example.pp_3_1_2_springboot.model.User;
import com.example.pp_3_1_2_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final String USERS_PAGE = "redirect:/users";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(@RequestParam(name = "count", defaultValue = "15") int count, Model model) {
        model.addAttribute("users", userService.listUsersCount(count));
        return "users";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.add(user);
        return USERS_PAGE;
    }

    @PostMapping("/delete")
    public String dellUser(@RequestParam(name = "deleteId") long deleteId, RedirectAttributes redirectAttributes) {
        if(!userService.dell(deleteId)) {
            redirectAttributes.addFlashAttribute("message", "ID not found!");
        }
        return USERS_PAGE;
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam long updateId,
                             @RequestParam String updateFirstName,
                             @RequestParam String updateLastName,
                             @RequestParam String updateEmail,
                             RedirectAttributes redirectAttributes) {
        if(!userService.update(updateId, updateFirstName, updateLastName, updateEmail)) {
            redirectAttributes.addFlashAttribute("message", "ID not found!");
        }
        return USERS_PAGE;
    }

    @GetMapping("/static/content/{action}")
    public String getStaticContent(@PathVariable String action) {
        return "content/" + action + "_user";
    }

}