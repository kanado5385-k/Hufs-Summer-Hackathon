package com.hufs.ice_back.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hufs.ice_back.common.ResponseCode;
import com.hufs.ice_back.common.ResponseMessage;
import com.hufs.ice_back.dto.object.ArticleListItem;
import com.hufs.ice_back.entity.ArticleEntity;
import com.hufs.ice_back.repository.PositionRepository;

import lombok.Getter;

@Getter
public class GetArticleListResponseDto extends ResponseDto{

    private List<ArticleListItem> articleList;

    private GetArticleListResponseDto(List<ArticleEntity> articleEntities, PositionRepository positionRepository){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.articleList = ArticleListItem.getList(articleEntities, positionRepository);
    }

    public static ResponseEntity<GetArticleListResponseDto> success(List<ArticleEntity> articleEntities, PositionRepository positionRepository){
        GetArticleListResponseDto result = new GetArticleListResponseDto(articleEntities, positionRepository);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
