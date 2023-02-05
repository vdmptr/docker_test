package vdm.post.facade;

import vdm.post.domen.Post;

public interface PostFacade {

  Post createPost(Long authorId, String text);

  Post findPost(Long postId);

  void deletePost(Long postId);

  Post updatePost(Long postId, String text);
}
