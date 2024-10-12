package com.example.blogms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

    @NotBlank(message = "title can not be null")
    private String title;

    @NotBlank(message = "content can not be null")
    @Size(min = 20, max = 500, message = "content size must between 20 and 500")
    private String content;

}
