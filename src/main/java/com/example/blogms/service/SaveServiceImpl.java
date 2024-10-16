package com.example.blogms.service;

import com.example.blogms.client.UserClient;
import com.example.blogms.dto.response.PostResponseDto;
import com.example.blogms.dto.response.UserResponseDto;
import com.example.blogms.entity.Post;
import com.example.blogms.entity.Save;
import com.example.blogms.exception.ResourceExistException;
import com.example.blogms.exception.ResourceFoundException;
import com.example.blogms.mapper.PostMapper;
import com.example.blogms.repository.LikeRepository;
import com.example.blogms.repository.PostRepository;
import com.example.blogms.repository.SaveRepository;
import com.example.blogms.service.impl.SaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveServiceImpl implements SaveService {
    private final SaveRepository saveRepository;
    private final PostRepository postRepository;
    private final UserClient userClient;
    private final PostMapper postMapper;
    private final LikeRepository likeRepository;

    @Override
    public void addSave(Long postId) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (saveRepository.existsByPost_PostIdAndEmail(postId, email)) {
            throw new ResourceExistException("You have already added save this post");
        }

        var postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceFoundException("Post can not found"));

        Save saveEntity = new Save();
        saveEntity.setPost(postEntity);
        saveEntity.setEmail(email);
        saveRepository.save(saveEntity);

    }

    @Override
    public void deleteMySavesById(Long postId) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        saveRepository.findByEmailAndPost_PostId(email, postId)
                .ifPresentOrElse(saveRepository::delete, () -> {
                    throw new ResourceExistException("Can not find your saved post");
                });
    }

    @Override
    public List<PostResponseDto> getMySaveList() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Save> saveList = saveRepository.findByEmail(email);
        if (saveList.isEmpty()) {
            return Collections.emptyList();
        }
        return saveList.stream()
                .map(save -> {
                    Long postId = save.getPost().getPostId();
                    UserResponseDto userProfileDetails = userClient.getUserProfileByEmail(save.getPost()
                            .getEmail()).getData();
                    Post postEntity = postRepository.findById(postId)
                            .orElse(null);

                    boolean likeSuccess = likeRepository.findByPost_PostIdAndEmail(postId, email).isPresent();
                    Long likeCount = likeRepository.countByPost_PostId(postId);

                    PostResponseDto postResponseDto = postMapper.mapToDto(postEntity);
                    postResponseDto.setUserProfileDetails(userProfileDetails);
                    postResponseDto.setLikeCount(likeCount);
                    postResponseDto.setLikeSuccess(likeSuccess);

                    return postResponseDto;
                })
                .toList();
    }
}
