package com.codingland.apiservice.user.presentation;

import com.codingland.apiservice.user.service.LoginUseCase;
import com.codingland.common.common.ApplicationResponse;
import com.codingland.domain.user.dto.response.LoginAddResponse;
import com.codingland.security.oauth.dto.request.KakaoLoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login/kakao")
    public ApplicationResponse<LoginAddResponse> kakaoLogin(@Valid @RequestBody KakaoLoginRequest kakaoLoginRequest) {
        LoginAddResponse response = loginUseCase.kakaoLogin(kakaoLoginRequest);
        return ApplicationResponse.ok(response);
    }
}
