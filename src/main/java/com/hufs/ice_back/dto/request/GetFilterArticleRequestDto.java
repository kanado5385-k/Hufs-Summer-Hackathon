package com.hufs.ice_back.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetFilterArticleRequestDto {
    private String local;
    private List<String> time; // 시간 범위는 파싱이 필요
    private String position;
}
