package com.example.blogms.repository;

import com.example.blogms.entity.Save;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaveRepository extends JpaRepository<Save, Long> {

    boolean existsByPost_PostIdAndEmail(Long postId, String email);

    Optional<Save> findByEmailAndPost_PostId(String email, Long postId);

    List<Save> findByEmail(String email, Pageable pageable);

}
