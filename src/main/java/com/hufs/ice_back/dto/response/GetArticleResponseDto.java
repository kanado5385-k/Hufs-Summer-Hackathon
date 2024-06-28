package com.hufs.ice_back.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hufs.ice_back.common.ResponseCode;
import com.hufs.ice_back.common.ResponseMessage;
import com.hufs.ice_back.entity.ArticleEntity;
import com.hufs.ice_back.entity.PositionEntity;

import lombok.Getter;

@Getter
public class GetArticleResponseDto extends ResponseDto {
    // article entity properties
    private String userEmail;
    private String articleTitle;
    private String articleContent;
    private String credit;
    private String local;
    private String time;

    // position entity properties
    private String shortstop;
    private String firstBase;
    private String catcher;
    private String leftFielder;
    private String centerFielder;
    private String pitcher;
    private String secondBase;
    private String rightFielder;
    private String thirdBase;


    private GetArticleResponseDto(ArticleEntity articleEntity, PositionEntity positionEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        // article entity properties
        // this.articleNum = articleEntity.getArticleNum();
        this.userEmail = articleEntity.getUserEmail();
        this.articleTitle = articleEntity.getArticleTitle();
        this.articleContent = articleEntity.getArticleContent();
        this.credit = articleEntity.getCredit();
        this.local = articleEntity.getLocal();
        this.time = articleEntity.getTime();
        // position entity properties
        this.shortstop = positionEntity.getShortstop();
        this.firstBase = positionEntity.getFirstBase();
        this.catcher = positionEntity.getCatcher();
        this.leftFielder = positionEntity.getLeftFielder();
        this.centerFielder = positionEntity.getCenterFielder();
        this.rightFielder = positionEntity.getRightFielder();
        this.pitcher = positionEntity.getPitcher();
        this.secondBase = positionEntity.getSecondBase();
        this.thirdBase = positionEntity.getThirdBase();
        
    }

    public static ResponseEntity<GetArticleResponseDto> success(ArticleEntity articleEntity, PositionEntity postitionEntity){
        GetArticleResponseDto result = new GetArticleResponseDto(articleEntity, postitionEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistArticle(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_ARTICLE, ResponseMessage.NOT_EXISTED_ARTICLE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
}
