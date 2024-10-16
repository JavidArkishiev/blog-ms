package com.example.blogms.controller;

import com.example.blogms.dto.response.PostResponseDto;
import com.example.blogms.dto.response.BaseResponse;
import com.example.blogms.service.impl.SaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("saves")
@PreAuthorize("hasAuthority('USER')")
public class SaveController {
    private final SaveService saveService;

    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> addSave(@PathVariable Long postId) {
        saveService.addSave(postId);
        return BaseResponse.success("This post added save list");
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> deleteMySavesById(@PathVariable Long postId) {
        saveService.deleteMySavesById(postId);
        return BaseResponse.success("Deleted");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<PostResponseDto>> getMySaveList() {
        return BaseResponse.oK(saveService.getMySaveList());
    }

}
