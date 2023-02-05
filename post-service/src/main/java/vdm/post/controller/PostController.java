package vdm.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vdm.post.controller.api.PostApi;
import vdm.post.domen.Post;
import vdm.post.domen.dto.PostMessageDto;
import vdm.post.facade.PostFacade;

@RestController
@RequiredArgsConstructor
public class PostController implements PostApi {

  private final PostFacade postFacade;

  @PostMapping("/posts")
  public Post createUser(@RequestBody PostMessageDto postDto) {
    return postFacade.createPost(postDto.getAuthorId(), postDto.getText());
  }

  @GetMapping("/posts/{id}")
  public Post findPost(@PathVariable Long id) {
    return postFacade.findPost(id);
  }

  @DeleteMapping("/posts/{id}")
  public void deletePost(@PathVariable Long id) {
    postFacade.deletePost(id);
  }

  @PutMapping("/posts/{id}")
  public Post updateUser(@PathVariable Long id, @RequestBody PostMessageDto postDto) {
    return postFacade.updatePost(id, postDto.getText());
  }
}
