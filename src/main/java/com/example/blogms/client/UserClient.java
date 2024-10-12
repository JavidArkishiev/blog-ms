package com.example.blogms.client;

import com.example.blogms.dto.response.UserResponseDto;
import com.example.blogms.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-ms", url = "https://user-ms-68abe8e678b0.herokuapp.com", configuration = FeignClientInterceptor.class)
public interface UserClient {

    @GetMapping("/user/user-profile/{email}")
    BaseResponse<UserResponseDto> getUserProfileByEmail(@PathVariable String email);


}
