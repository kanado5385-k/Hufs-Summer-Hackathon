package com.hufs.ice_back.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hufs.ice_back.dto.request.CheckCertificationRequestDto;
import com.hufs.ice_back.dto.request.EmailCertificationRequestDto;
import com.hufs.ice_back.dto.request.GetUserListRequestDto;
import com.hufs.ice_back.dto.response.GetUserListResposeDto;
import com.hufs.ice_back.dto.request.SignInRequestDto;
import com.hufs.ice_back.dto.request.SignUpRequestDto;
import com.hufs.ice_back.dto.response.CheckCertificationResponseDto;
import com.hufs.ice_back.dto.response.EmailCertificationResponseDto;
import com.hufs.ice_back.dto.response.SignInResponseDto;
import com.hufs.ice_back.dto.response.SignUpResponseDto;
import com.hufs.ice_back.service.AuthService;



import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestBody){
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
    
        return response;
    }

    @PostMapping("/sign-in") //메소드의 반환 타입과 값을 response로 선언
    public ResponseEntity<? super SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestBody){
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification (
            @RequestBody @Valid EmailCertificationRequestDto requestBody
    ) {
        ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
        return response;
    }

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification (
            @RequestBody @Valid CheckCertificationRequestDto requestBody
    ) {
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);
        return response;
    }
    
    @GetMapping("/name") //메소드의 반환 타입과 값을 response로 선언
    public ResponseEntity<? super GetUserListResposeDto> getUserList(@RequestBody @Valid GetUserListRequestDto requestBody){
        ResponseEntity<? super GetUserListResposeDto> response = authService.getUserList(requestBody);
        return response;
    }
}
