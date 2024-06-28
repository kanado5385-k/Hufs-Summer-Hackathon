package com.hufs.ice_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hufs.ice_back.entity.PositionEntity;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Integer> {
    
    boolean existsByArticleNum(Integer articleNum); // 정확한 필드 이름을 사용
    PositionEntity findByArticleNum(Integer articleNum);   // 정확한 필드 이름을 사용
}
