package com.hufs.ice_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hufs.ice_back.entity.UserEntity;

@Repository
public interface UserListRepository extends JpaRepository<UserEntity, String>{

    List<UserEntity> findByNameOrderByAgeDesc(String name);
    
}