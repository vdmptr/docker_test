package vdm.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vdm.post.domen.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  @Query(value = "SELECT count(*) FROM vdm_posts.posts p WHERE p.author_id = :authorId",  nativeQuery = true)
  Long getNumberPostByAuthorId(Long authorId);
}
