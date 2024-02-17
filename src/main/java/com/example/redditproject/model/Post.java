package com.example.redditproject.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int votes;
  private String postTitle;
  private String postPath;
  private LocalDate localDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;


  public Post(String postTitle, String postPath, User user) {
    votes = 0;
    this.postTitle = postTitle;
    this.postPath = postPath;
    this.user = user;
    localDate = LocalDate.now();
  }

  public Post() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long postId) {
    this.id = postId;
  }

  public int getVotes() {
    return votes;
  }

  public void setVotes(int votes) {
    this.votes = votes;
  }

  public String getPostTitle() {
    return postTitle;
  }

  public void setPostTitle(String postTitle) {
    this.postTitle = postTitle;
  }

  public String getPostPath() {
    return postPath;
  }

  public void setPostPath(String postPath) {
    this.postPath = postPath;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
