package com.hufs.ice_back.dto.object;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.hufs.ice_back.entity.ArticleEntity;
import com.hufs.ice_back.entity.PositionEntity;
import com.hufs.ice_back.repository.PositionRepository;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListItem {

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

    public ArticleListItem(ArticleEntity articleListViewEntity, PositionEntity positionEntity) {
        // article entity properties
        // this.articleNum = articleEntity.getArticleNum();
        this.userEmail = articleListViewEntity.getUserEmail();
        this.articleTitle = articleListViewEntity.getArticleTitle();
        this.articleContent = articleListViewEntity.getArticleContent();
        this.credit = articleListViewEntity.getCredit();
        this.local = articleListViewEntity.getLocal();
        this.time = articleListViewEntity.getTime();
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

    public static List<ArticleListItem> getList(List<ArticleEntity> articleListViewEntities, PositionRepository positionRepository) {
        List<ArticleListItem> list = new ArrayList<>();
        for (ArticleEntity articleListViewEntity : articleListViewEntities){
            PositionEntity positionEntity = positionRepository.findByArticleNum(articleListViewEntity.getArticleNum());
            ArticleListItem articleListItem = new ArticleListItem(articleListViewEntity, positionEntity);
            list.add(articleListItem);
        }
        return list;
    }
}
