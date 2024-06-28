package com.hufs.ice_back.service;

import org.springframework.http.ResponseEntity;

import com.hufs.ice_back.dto.request.GetFilterArticleRequestDto;
import com.hufs.ice_back.dto.request.PostArticleRequestDto;
import com.hufs.ice_back.dto.response.GetArticleListResponseDto;
import com.hufs.ice_back.dto.response.GetArticleResponseDto;
import com.hufs.ice_back.dto.response.PostArticleResponseDto;

public interface ArticleService {

    ResponseEntity<? super PostArticleResponseDto> postArticle(PostArticleRequestDto dto, String email);
    ResponseEntity<? super GetArticleResponseDto> getArticle(Integer articleNum);
    ResponseEntity<? super GetArticleListResponseDto> getArticleList();
    ResponseEntity<? super GetArticleListResponseDto> getFilteredArticleList(GetFilterArticleRequestDto filterDto);
}