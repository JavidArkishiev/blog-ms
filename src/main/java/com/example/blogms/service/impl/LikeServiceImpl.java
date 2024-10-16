package com.example.blogms.service.impl;

import com.example.blogms.entity.Like;
import com.example.blogms.entity.Post;
import com.example.blogms.exception.ResourceExistException;
import com.example.blogms.exception.ResourceFoundException;
import com.example.blogms.repository.LikeRepository;
import com.example.blogms.repository.PostRepository;
import com.example.blogms.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public void createLike(Long postId) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (likeRepository.existsByPost_PostIdAndEmail(postId, email)) {
            throw new ResourceExistException("You already liked this post");

        }
        Post postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceFoundException("Post not found"));

        Like like = new Like();
        like.setEmail(email);
        like.setPost(postEntity);
        likeRepository.save(like);
    }

    @Override
    public void deleteLike(Long postId) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        likeRepository.findByPost_PostIdAndEmail(postId, email)
                .ifPresentOrElse(likeRepository::delete, () -> {
                    throw new ResourceExistException("Like not found");
                });

    }
}
