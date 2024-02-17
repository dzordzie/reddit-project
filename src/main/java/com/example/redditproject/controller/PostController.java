package com.example.redditproject.controller;

import com.example.redditproject.service.PostService;
import com.example.redditproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
  private final PostService postService;
  private final UserService userService;

  @Autowired
  public PostController(PostService postService, UserService userService) {
    this.postService = postService;
    this.userService = userService;
  }

  @GetMapping("/")
  public String nextPage(Model model, @RequestParam(required = false, defaultValue = "0") String pageNumber, @RequestParam(required = false) String username) {
    model.addAttribute("postsOnPage", postService.listTopTenAndSortedByVotes(Integer.parseInt(pageNumber)));
    model.addAttribute("currentPage", Integer.parseInt(pageNumber));
    model.addAttribute("allPosts", postService.getPosts().size());
    model.addAttribute("username", username);
    return "index";
  }


  @GetMapping("/upVote")
  public String voteUp(@RequestParam Long postId, @RequestParam String username) {
    postService.upVote(postId, username);
    return "redirect:/?username=" + username;
  }

  @GetMapping("/downVote")
  public String voteDown(@RequestParam Long postId, @RequestParam String username) {
    postService.downVote(postId, username);
    return "redirect:/?username=" + username;
  }

}
