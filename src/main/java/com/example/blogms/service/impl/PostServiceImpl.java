package com.example.blogms.service.impl;

import com.example.blogms.client.UserClient;
import com.example.blogms.dto.request.PostRequestDto;
import com.example.blogms.dto.response.MyPostResponseDto;
import com.example.blogms.dto.response.PostResponseDto;
import com.example.blogms.dto.response.UserResponseDto;
import com.example.blogms.entity.Post;
import com.example.blogms.exception.ResourceFoundException;
import com.example.blogms.mapper.PostMapper;
import com.example.blogms.repository.LikeRepository;
import com.example.blogms.repository.PostRepository;
import com.example.blogms.service.JWTService;
import com.example.blogms.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final JWTService jwtService;
    private final LikeRepository likeRepository;
    private final UserClient userClient;

    public void createPost(PostRequestDto requestDto, HttpServletRequest servletRequest) {
        String token = servletRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        var email = jwtService.extractUsername(token);

        Post post = postMapper.mapToEntity(requestDto);
        post.setEmail(email);
        postRepository.save(post);

    }

    public List<MyPostResponseDto> getMyPost(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Post> postList = postRepository.findByEmail(email, pageable)
                .orElse(null);

        return postMapper.mapToMyPostListDto(postList).stream()
                .peek(post -> {
                    Long postId = post.getPostId();
                    boolean likeSuccess = likeRepository.findByPost_PostIdAndEmail(postId, email).isPresent();
                    Long likeCount = likeRepository.countByPost_PostId(postId);

                    post.setLikeSuccess(likeSuccess);
                    post.setLikeCount(likeCount);
                })
                .toList();
    }


    public void deleteMyPostById(Long postId) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        postRepository.findByEmailAndPostId(email, postId)
                .ifPresentOrElse(postRepository::delete, () -> {
                    throw new ResourceFoundException("Can not find your post");
                });

    }

    public List<PostResponseDto> getAllPost(int page, int size) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        List<Post> postList = postRepository.findAll(pageable).getContent();
        if (postList.isEmpty()) {
            return Collections.emptyList();
        }


        return postMapper.mapToListDto(postList).stream()
                .peek(post -> {
                    Long postId = post.getPostId();
                    boolean likeSuccess = likeRepository.findByPost_PostIdAndEmail(postId, email).isPresent();
                    Long likeCount = likeRepository.countByPost_PostId(postId);

                    post.setLikeSuccess(likeSuccess);
                    post.setLikeCount(likeCount);

                    UserResponseDto userProfileDetails = userClient.getUserProfileByEmail(post.getEmail()).getData();
                    post.setUserProfileDetails(userProfileDetails);
                })
                .toList();
    }

    @Override
    public PostResponseDto getPostById(Long postId) {

        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        Post postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceFoundException("Post can not found"));

        UserResponseDto userProfileDetails = userClient.getUserProfileByEmail(postEntity.getEmail()).getData();

        PostResponseDto postResponseDto = postMapper.mapToDto(postEntity);
        postResponseDto.setUserProfileDetails(userProfileDetails);

        boolean like = likeRepository.findByPost_PostIdAndEmail(postId, email).isPresent();
        Long longCount = likeRepository.countByPost_PostId(postId);

        postResponseDto.setLikeCount(longCount);
        postResponseDto.setLikeSuccess(like);

        return postResponseDto;


    }

    @Override
    public void updateMyPost(Long postId, PostRequestDto requestDto) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        Post oldPost = postRepository.findByEmailAndPostId(email, postId)
                .orElseThrow(() -> new ResourceFoundException("Can not find your post"));

        postRepository.save(postMapper.mapToEntity(requestDto, oldPost));
    }

    @Override
    public List<PostResponseDto> getPostsAndProfileByUserEmail(String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        UserResponseDto userProfileDetails = userClient.getUserProfileByEmail(email).getData();
        List<Post> postList = postRepository.findByEmail(email, pageable)
                .orElse(null);
        return postMapper.mapToListDto(postList).stream()
                .peek(post -> {
                    post.setUserProfileDetails(userProfileDetails);

                    Long postId = post.getPostId();
                    boolean likeSuccess = likeRepository.findByPost_PostIdAndEmail(postId, email).isPresent();
                    Long likeCount = likeRepository.countByPost_PostId(postId);

                    post.setLikeSuccess(likeSuccess);
                    post.setLikeCount(likeCount);
                }).toList();


    }
}
