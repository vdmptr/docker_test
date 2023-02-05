package vdm.post.domen;


import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@Table(schema = "vdm_posts", name = "posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id", nullable = false)
  private Long id;

  @Column(name = "author_id", nullable = false)
  Long authorId;

  @Column(name = "text")
  String text;

  @CreationTimestamp
  @Column(name = "posted_at", nullable = false)
  LocalDate postedAt;

}
