package com.hufs.ice_back.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hufs.ice_back.entity.ArticleEntity;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    
    boolean existsByArticleNum(Integer articleNum); // 정확한 필드 이름을 사용
    ArticleEntity findByArticleNum(Integer articleNum);   // 정확한 필드 이름을 사용
    @Query("SELECT a FROM article a WHERE a.local = :local AND a.time IN :times")
    List<ArticleEntity> findByLocalAndTimes(@Param("local") String local, @Param("times") List<String> times);
}
