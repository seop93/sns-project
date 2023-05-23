package com.practice.snsproject.controller.api;


import com.practice.snsproject.domain.dto.request.UserJoinRequest;
import com.practice.snsproject.domain.dto.response.Response;
import com.practice.snsproject.domain.dto.response.UserJoinResponse;
import com.practice.snsproject.domain.entity.User;
import com.practice.snsproject.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@RestController
public class UserApiController {

    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "사용자가 입력한 아이디와 비밀번호로 회원가입을 한다")
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request){
        User savedUser = userService.join(request.getUserName(), request.getPassword());
        return Response.success(new UserJoinResponse(savedUser.getId(), savedUser.getUserName()));
    }
}
