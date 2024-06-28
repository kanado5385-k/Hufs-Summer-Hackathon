package com.hufs.ice_back.dto.response;

import lombok.Getter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hufs.ice_back.common.ResponseCode;
import com.hufs.ice_back.common.ResponseMessage;
import com.hufs.ice_back.dto.UserList;
import com.hufs.ice_back.entity.UserEntity;


@Getter
public class GetUserListResposeDto extends ResponseDto{

    private List<UserList> userList;

        private GetUserListResposeDto(List<UserEntity> userEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userList = UserList.getList(userEntities);
    }

    public static ResponseEntity<GetUserListResposeDto> success(List<UserEntity> userEntities){
        GetUserListResposeDto result = new GetUserListResposeDto(userEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
}
