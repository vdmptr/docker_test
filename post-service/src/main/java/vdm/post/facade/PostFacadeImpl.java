package vdm.post.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vdm.post.domen.Post;
import vdm.post.domen.dto.UserDto;
import vdm.post.service.UserWebClientServiceImpl;
import vdm.post.service.PostService;

@Component
@RequiredArgsConstructor
public class PostFacadeImpl implements PostFacade {

  private final PostService postService;

  private final UserWebClientServiceImpl userService;

  public Post createPost(Long authorId, String text) {
    Post savedPost = postService.createPost(authorId, text);
    Long numberOfPost = postService.getNumberOfPostByUserId(authorId);
    userService.updateUser(new UserDto(authorId, null, numberOfPost.toString()));
    return savedPost;
  }

  public Post findPost(Long postId) {
    return postService.findPost(postId);
  }

  @Transactional
  public void deletePost(Long postId) {
    Post savedPost = postService.findPost(postId);
    Long authorId = savedPost.getAuthorId();
    postService.deletePost(postId);
    Long numberOfPost = postService.getNumberOfPostByUserId(authorId);
    userService.updateUser(new UserDto(authorId, null, numberOfPost.toString()));
  }

  public Post updatePost(Long postId, String text) {
    return postService.updatePost(postId, text);
  }
}
