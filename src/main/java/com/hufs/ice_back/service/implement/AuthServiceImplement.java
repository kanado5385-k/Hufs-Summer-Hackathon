package com.hufs.ice_back.service.implement;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hufs.ice_back.common.CertificationNumber;
import com.hufs.ice_back.dto.request.CheckCertificationRequestDto;
import com.hufs.ice_back.dto.request.EmailCertificationRequestDto;
import com.hufs.ice_back.dto.request.GetUserListRequestDto;
import com.hufs.ice_back.dto.request.SignInRequestDto;
import com.hufs.ice_back.dto.request.SignUpRequestDto;
import com.hufs.ice_back.dto.response.CheckCertificationResponseDto;
import com.hufs.ice_back.dto.response.EmailCertificationResponseDto;
import com.hufs.ice_back.dto.response.GetUserListResposeDto;
import com.hufs.ice_back.dto.response.ResponseDto;
import com.hufs.ice_back.dto.response.SignInResponseDto;
import com.hufs.ice_back.dto.response.SignUpResponseDto;
import com.hufs.ice_back.entity.CertificationEntity;
import com.hufs.ice_back.entity.UserEntity;
import com.hufs.ice_back.provider.EmailProvider;
import com.hufs.ice_back.provider.JwtProvider;
import com.hufs.ice_back.repository.CertificationRepository;
import com.hufs.ice_back.repository.UserListRepository;
import com.hufs.ice_back.repository.UserRepository;
import com.hufs.ice_back.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;;
    private final JwtProvider jwtProvider;
    private final CertificationRepository certificationRepository;
    private final EmailProvider emailProvider;
    private final UserListRepository userListRepository;
   
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        try{

            String email = dto.getEmail(); //이메일 중복을 확인해주는 코드
            boolean existsByEmail = userRepository.existsByEmail(email);
            if (existsByEmail) return SignUpResponseDto.duplicateEmail();

            String password = dto.getPassword(); //비밀번호를 암호화
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto); //dto데이터를 entity에 삽입
            System.out.println("정상");
            userRepository.save(userEntity); //entity를 repository를 통해 db에 저장

        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();

        }

        return SignUpResponseDto.success();
    }

       @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String token = null; //토큰 선업/초기화
        try{

            String email = dto.getEmail();  //요청으로 받은 이메일 존재 확인
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return SignInResponseDto.signInFail();

            String password = dto.getPassword(); //요청 받은 비번과 해당 유저(이메일)의 비번 일치하는지 확인
            String encodedPassword = userEntity.getPassword();
            // System.out.println("Encoded Password from DB: " + encodedPassword);
            // System.out.println("Input Password: " + password);

            boolean isMatched = passwordEncoder.matches(password, encodedPassword); //입력 받은 비번과 db에 있는 암호화된 비번 확인;
            if(!isMatched) {
                // System.out.println("비밀번호가 일치하지않습니다.");
                return SignInResponseDto.signInFail();
            }

            token = jwtProvider.create(email); //토큰 생성
            System.out.println(token);

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
        
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            // String userId = dto.getId();
            String email = dto.getEmail();

            // 존재하는 이메일인지 확인.
            boolean isExistEmail = userRepository.existsByEmail(email);
            if (isExistEmail) return EmailCertificationResponseDto.duplicateId();

            // 전에 이 이메일로 인증번호를 받은적 있으면 그 이메일 삭제
            CertificationEntity certificationEntity1 = certificationRepository.findByUserEmail(email);
            if (certificationEntity1 != null){
                certificationRepository.delete(certificationEntity1);
            }

            // 인증번호 생성
            String certificationNumber = CertificationNumber.getCertificationNumber();

            // 이메일 전송 성공 여부 확인
            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            // 데이터베이스 저장.
            CertificationEntity certificationEntity = new CertificationEntity(email, certificationNumber);
            certificationRepository.save(certificationEntity);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {

        try {
            // String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();


            CertificationEntity certificationEntity = certificationRepository.findByUserEmail(email);
            // 인증 메일을 보내지 않은 경우.
            if (certificationEntity == null) {
                return CheckCertificationResponseDto.certificationFail();
            }

            boolean isMatched = certificationEntity.getUserEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);

            if (!isMatched) {
                return CheckCertificationResponseDto.certificationFail();
            }


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return CheckCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetUserListResposeDto> getUserList(GetUserListRequestDto dto) {
        List<UserEntity> userListEntities = new ArrayList<>();
        String name = dto.getName();
        try{

            boolean existedUser = userRepository.existsByName(name);
            if (!existedUser) return GetUserListResposeDto.noExistUser();
            userListEntities = userListRepository.findByNameOrderByAgeDesc(name);
        }catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserListResposeDto.success(userListEntities);
    }


}
