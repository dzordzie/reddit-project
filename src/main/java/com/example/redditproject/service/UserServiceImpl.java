package com.example.redditproject.service;

import com.example.redditproject.model.User;
import com.example.redditproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void addOrUpdate(User user) {
    userRepository.save(user);
  }

  @Override
  public Optional<User> getUserByName(String username) {
    return userRepository.findUserByUserName(username);
  }

  @Override
  public List<User> getUsers() {
    return userRepository.findAll();
  }
}
