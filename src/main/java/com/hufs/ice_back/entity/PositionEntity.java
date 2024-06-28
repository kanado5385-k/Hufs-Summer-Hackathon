package com.hufs.ice_back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.hufs.ice_back.dto.request.PostArticleRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "position")
public class PositionEntity {
    @Id
    @JoinColumn(name = "article_num")
    private int articleNum;
    
    @Column(name = "shortstop")
    private String shortstop;

    @Column(name = "first_base")
    private String firstBase;

    @Column(name = "catcher")
    private String catcher;

    @Column(name = "left_fielder")
    private String leftFielder;

    @Column(name = "center_fielder")
    private String centerFielder;

    @Column(name = "right_fielder")
    private String rightFielder;

    @Column(name = "pitcher")
    private String pitcher;

    @Column(name = "second_base")
    private String secondBase;

    @Column(name = "third_base")
    private String thirdBase;
    

    public PositionEntity(PostArticleRequestDto dto, int numberOfArticle){
        this.articleNum = numberOfArticle;
        this.shortstop = dto.getShortstop();
        this.firstBase = dto.getFirstBase();
        this.catcher = dto.getCatcher();
        this.leftFielder = dto.getLeftFielder();
        this.centerFielder = dto.getCenterFielder();
        this.rightFielder = dto.getRightFielder();
        this.pitcher = dto.getPitcher();
        this.secondBase = dto.getSecondBase();
        this.thirdBase = dto.getThirdBase();
    }
}
