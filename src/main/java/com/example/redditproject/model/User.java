package com.example.redditproject.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String userName;
  private String userPassword;
  private boolean canUpVote;
  private boolean canDownVote;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private List<Post> posts;


  public User() {
  }

  public User(String userName, String userPassword) {
    this.userName = userName;
    this.userPassword = userPassword;
    canUpVote = true;
    canDownVote = true;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public boolean isCanUpVote() {
    return canUpVote;
  }

  public void setCanUpVote(boolean canUpVote) {
    this.canUpVote = canUpVote;
  }

  public boolean isCanDownVote() {
    return canDownVote;
  }

  public void setCanDownVote(boolean canDownVote) {
    this.canDownVote = canDownVote;
  }
}
