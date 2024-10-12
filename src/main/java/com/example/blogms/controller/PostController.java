package com.example.blogms.controller;

import com.example.blogms.dto.request.PostRequestDto;
import com.example.blogms.dto.response.MyPostResponseDto;
import com.example.blogms.dto.response.PostResponseDto;
import com.example.blogms.response.BaseResponse;
import com.example.blogms.service.impl.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class PostController {
    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<String> createPost(@Valid @RequestBody PostRequestDto requestDto,
                                           HttpServletRequest servletRequest) {
        postService.createPost(requestDto, servletRequest);
        return BaseResponse.success("New post created");
    }

    @GetMapping("my-post")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<MyPostResponseDto>> getMyPost(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "8") int size) {
        return BaseResponse.oK(postService.getMyPost(page, size));
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> deleteMyPost(@PathVariable Long postId) {
        postService.deleteMyPostById(postId);
        return BaseResponse.success("Your post deleted");
    }

    @GetMapping("home")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<PostResponseDto>> getAllPost(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int size) {
        return BaseResponse.oK(postService.getAllPost(page, size));
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<PostResponseDto> getPostById(@PathVariable Long postId) {
        return BaseResponse.oK(postService.getPostById(postId));
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> updateMyPost(@PathVariable Long postId,
                                             @Valid @RequestBody PostRequestDto requestDto) {
        postService.updateMyPost(postId, requestDto);
        return BaseResponse.success("Your post update successfully");
    }

    @GetMapping("users/{email}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<PostResponseDto>> getPostsAndProfileByUserEmail(@PathVariable String email,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "8") int size) {
        return BaseResponse.oK(postService.getPostsAndProfileByUserEmail(email, page, size));
    }


}
