package com.example.redditproject.service;

import com.example.redditproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
  void addOrUpdate(User user);

  Optional<User> getUserByName(String username);

  List<User> getUsers();
}
