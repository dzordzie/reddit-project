package com.example.redditproject.service;

import com.example.redditproject.model.Post;
import com.example.redditproject.model.User;
import com.example.redditproject.repository.PostRepository;
import com.example.redditproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Autowired
  public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
  }

  @Override
  public List<Post> getPosts() {
    return postRepository.findAll();
  }

  @Override
  public void addOrUpdate(Post post) {
    postRepository.save(post);
  }

  @Override
  public Optional<Post> getPostById(Long postId) {
    return postRepository.findById(postId);
  }

  @Override
  public void upVote(Long postId, String username) {
    Optional<User> user = userRepository.findUserByUserName(username);
    if (user.isPresent() && user.get().isCanUpVote()) {
      User u = user.get();
      Optional<Post> post = postRepository.findById(postId);
      if (post.isPresent()) {
        Post p = post.get();
        int newVoteTotal = p.getVotes() + 1;
        p.setVotes(newVoteTotal);
        postRepository.save(p);
      }
      u.setCanUpVote(false);
      userRepository.save(u);
    }
  }

  @Override
  public void downVote(Long postId, String username) {
    Optional<User> user = userRepository.findUserByUserName(username);
    if (user.isPresent() && user.get().isCanDownVote()) {
      User u = user.get();
      Optional<Post> post = postRepository.findById(postId);
      if (post.isPresent()) {
        Post p = post.get();
        int newVoteTotal = p.getVotes() - 1;
        p.setVotes(newVoteTotal);
        postRepository.save(p);
      }
      u.setCanDownVote(false);
      userRepository.save(u);
    }

  }

  @Override
  public Page<Post> listTopTenAndSortedByVotes(int pageNumber) {
    PageRequest page = PageRequest.of(pageNumber, 10, Sort.by("votes").descending());
    Page<Post> topTen = postRepository.findAll(page);
    return topTen;
  }

}
