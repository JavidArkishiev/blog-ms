package com.example.blogms.repository;

import com.example.blogms.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<List<Post>> findByEmail(String email, Pageable pageable);

    Optional<Post> findByEmailAndPostId(String email, Long postId);

}
