package com.hufs.ice_back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hufs.ice_back.dto.request.SignUpRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "career")
    private String career;

    @Column(name = "position")
    private String position;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "team")
    private String team;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    public UserEntity(SignUpRequestDto dto) {
        
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.career = dto.getCareer();
        this.position = dto.getPosition();
        this.name = dto.getName();
        this.age = dto.getAge();
        this.sex = dto.getSex();
        this.team = dto.getTeam();
        this.height = dto.getHeight();
        this.weight = dto.getWeight();
    }
}
