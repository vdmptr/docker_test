package vdm.post.service;

import vdm.post.domen.Post;

public interface PostService {

  Post createPost(Long authorId, String text);

  Post findPost(Long postId);

  void deletePost(Long postId);

  Post updatePost(Long postId, String text);

  Long getNumberOfPostByUserId(Long authorId);
}
