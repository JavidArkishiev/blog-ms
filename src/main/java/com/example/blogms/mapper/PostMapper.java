package com.example.blogms.mapper;

import com.example.blogms.dto.request.PostRequestDto;
import com.example.blogms.dto.response.MyPostResponseDto;
import com.example.blogms.dto.response.PostResponseDto;
import com.example.blogms.dto.response.UserResponseDto;
import com.example.blogms.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PostMapper {
    Post mapToEntity(PostRequestDto requestDto);

    Post mapToEntity(PostRequestDto requestDto, @MappingTarget Post oldPost);

    PostResponseDto mapToDto(Post post);


    @Mapping(target = "surName", source = "lastName")
    @Mapping(source = "firstName", target = "name")
    List<PostResponseDto> mapToListDto(List<Post> postList);

    List<MyPostResponseDto> mapToMyPostListDto(List<Post> postList);
}
