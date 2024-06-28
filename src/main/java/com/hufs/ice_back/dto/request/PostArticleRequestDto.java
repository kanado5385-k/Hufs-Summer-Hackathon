package com.hufs.ice_back.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostArticleRequestDto {

    @NotBlank
    private String articleTitle;

    @NotBlank
    private String articleContent;

    private String credit;

    private String local;

    @NotBlank
    private String time;

    // position
    private String shortstop;
    private String firstBase;
    private String catcher;
    private String leftFielder;
    private String centerFielder;
    private String rightFielder;
    private String pitcher;
    private String secondBase;
    private String thirdBase;
}
