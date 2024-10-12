package com.example.blogms.repository;

import com.example.blogms.entity.Like;
import com.example.blogms.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByPost_PostIdAndEmail(Long postId, String email);

    Optional<Like> findByPost_PostIdAndEmail(Long postId, String email);

    Long countByPost(Post post);

    Long countByPost_PostId(Long id);

}
