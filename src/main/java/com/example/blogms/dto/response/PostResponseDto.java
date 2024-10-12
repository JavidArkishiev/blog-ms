package com.example.blogms.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;

    @JsonIgnore
    private String email;
    private boolean likeSuccess;
    private Long likeCount;

    @JsonFormat(pattern = "d, MMMM yyyy")
    private LocalDateTime createdAt;
    UserResponseDto userProfileDetails;


}
