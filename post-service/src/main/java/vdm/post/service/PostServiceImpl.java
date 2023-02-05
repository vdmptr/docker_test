package vdm.post.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vdm.post.controller.exception.PersistenceException;
import vdm.post.domen.Post;
import vdm.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  public Post createPost(Long authorId, String text) {
    Post post = new Post();
    post.setAuthorId(authorId);
    post.setText(text);
    post.setPostedAt(LocalDate.now());

    return postRepository.save(post);
  }

  public Post findPost(Long postId) {

    return postRepository.findById(postId)
        .orElseThrow(() -> new PersistenceException("Post doesn’t exist with given id"));
  }

  public void deletePost(Long postId) {
    postRepository.deleteById(postId);

  }

  public Post updatePost(Long postId, String text) {
    Post post = postRepository.findById(postId)
        .map(it -> {
          it.setText(text);
          return it;
        })
        .orElseThrow(() -> new PersistenceException("Post doesn’t exist with given id"));

    return postRepository.save(post);
  }

  public Long getNumberOfPostByUserId(Long authorId) {

    return postRepository.getNumberPostByAuthorId(authorId);
  }
}
