package com.hufs.ice_back.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hufs.ice_back.common.ResponseCode;
import com.hufs.ice_back.common.ResponseMessage;

import lombok.Getter;

    @Getter
    public class CheckCertificationResponseDto extends ResponseDto {

        private CheckCertificationResponseDto(){
            super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        }

        public static ResponseEntity<CheckCertificationResponseDto> success() {
            CheckCertificationResponseDto responseBody = new CheckCertificationResponseDto();
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }

        public static ResponseEntity<ResponseDto> certificationFail(){
            ResponseDto responseBody = new ResponseDto(ResponseCode.VALIDATION_FAILED, ResponseMessage.VALIDATION_FAILED );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
        }
    }
