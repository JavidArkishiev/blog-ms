package com.example.blogms.service;

import com.example.blogms.dto.response.PostResponseDto;

import java.util.List;

public interface SaveService {
    void addSave(Long postId);

    void deleteMySavesById(Long postId);

    List<PostResponseDto> getMySaveList(int page, int size);
}
