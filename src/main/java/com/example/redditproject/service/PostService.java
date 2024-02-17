package com.example.redditproject.service;

import com.example.redditproject.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PostService {
  List<Post> getPosts();

  void addOrUpdate(Post post);

  Optional<Post> getPostById(Long postId);

  void upVote(Long postId, String username);

  void downVote(Long postId, String username);

  Page<Post> listTopTenAndSortedByVotes(int pageNumber);
}
