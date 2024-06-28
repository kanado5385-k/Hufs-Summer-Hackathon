package com.hufs.ice_back.service;

import org.springframework.http.ResponseEntity;

import com.hufs.ice_back.dto.request.CheckCertificationRequestDto;
import com.hufs.ice_back.dto.request.EmailCertificationRequestDto;
import com.hufs.ice_back.dto.request.GetUserListRequestDto;
import com.hufs.ice_back.dto.request.SignInRequestDto;
import com.hufs.ice_back.dto.request.SignUpRequestDto;
import com.hufs.ice_back.dto.response.CheckCertificationResponseDto;
import com.hufs.ice_back.dto.response.EmailCertificationResponseDto;
import com.hufs.ice_back.dto.response.SignInResponseDto;
import com.hufs.ice_back.dto.response.SignUpResponseDto;
import com.hufs.ice_back.dto.response.GetUserListResposeDto;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
    ResponseEntity<? super GetUserListResposeDto> getUserList(GetUserListRequestDto dto);


}

