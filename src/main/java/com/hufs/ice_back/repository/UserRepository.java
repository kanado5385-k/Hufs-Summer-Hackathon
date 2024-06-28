package com.hufs.ice_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hufs.ice_back.entity.UserEntity;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByEmail(String email);
    boolean existsByName(String name);

    UserEntity findByEmail(String email);
    //UserEntity findByName(String name);

    //List<UserEntity> findByName(String name);
    
}