package com.example.blogms.service;

public interface LikeService {
    void createLike(Long postId);

    void deleteLike(Long postId);
}
