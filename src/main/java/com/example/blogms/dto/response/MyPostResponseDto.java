package com.example.blogms.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MyPostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private boolean likeSuccess;
    private Long likeCount;
    @JsonFormat(pattern = "d, MMMM yyyy")
    private LocalDateTime createdAt;
}
