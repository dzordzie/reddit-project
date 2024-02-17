package com.example.redditproject.controller;

import com.example.redditproject.model.Post;
import com.example.redditproject.model.User;
import com.example.redditproject.service.PostService;
import com.example.redditproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class UserController {

  private final UserService userService;
  private final PostService postService;


  @Autowired
  public UserController(UserService userService, PostService postService) {
    this.userService = userService;
    this.postService = postService;
  }

  @GetMapping("/newUser")
  public String newUserMapping() {
    return "newUser";
  }

  @PostMapping("/newUser")
  public String addNewUser(@RequestParam String username, @RequestParam String password) {
    userService.addOrUpdate(new User(username, password));
    return "redirect:/";
  }

  @GetMapping("/login")
  public String loginMapping() {
    return "login";
  }

  @PostMapping("/login")
  public String loginPost(@RequestParam String username) {
    return "redirect:/?username=" + username;
  }

  @GetMapping("/submit")
  public String submitMapping(Model model, @RequestParam String username) {
    model.addAttribute("username", username);
    return "submit";
  }

  @PostMapping("/submit")
  public String submitPost(@RequestParam String title, @RequestParam String url, @RequestParam String username) {
    Optional<User> foundUser = userService.getUserByName(username);
    if (foundUser.isPresent()) {
      postService.addOrUpdate(new Post(title, url, foundUser.get()));
      return "redirect:/?username=" + username;
    }
    return "redirect:/submit";
  }

}
