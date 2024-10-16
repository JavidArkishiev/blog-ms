package com.example.blogms.controller;

import com.example.blogms.dto.response.BaseResponse;
import com.example.blogms.service.impl.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<String> createLike(@PathVariable Long postId) {
        likeService.createLike(postId);
        return BaseResponse.success("Ok");
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> deleteLike(@PathVariable Long postId) {
        likeService.deleteLike(postId);
        return BaseResponse.success("Ok");

    }
}
