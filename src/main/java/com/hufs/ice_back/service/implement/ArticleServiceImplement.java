package com.hufs.ice_back.service.implement;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hufs.ice_back.dto.request.GetFilterArticleRequestDto;
import com.hufs.ice_back.dto.request.PostArticleRequestDto;
import com.hufs.ice_back.dto.response.GetArticleListResponseDto;
import com.hufs.ice_back.dto.response.GetArticleResponseDto;
import com.hufs.ice_back.dto.response.PostArticleResponseDto;
import com.hufs.ice_back.dto.response.ResponseDto;
import com.hufs.ice_back.entity.ArticleEntity;
import com.hufs.ice_back.entity.PositionEntity;
import com.hufs.ice_back.repository.ArticleRepository;
import com.hufs.ice_back.repository.PositionRepository;
import com.hufs.ice_back.repository.UserRepository;
import com.hufs.ice_back.service.ArticleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImplement implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final PositionRepository positionRepository;
    

    @Override
    public ResponseEntity<? super PostArticleResponseDto> postArticle(PostArticleRequestDto dto, String email){
        try{
            // 사용자 계정이 존재하는지 확인하는 코드
            boolean existedEmail = userRepository.existsByEmail(email);
            if (!existedEmail) return PostArticleResponseDto.notExistUser();
            ArticleEntity articleEntity = new ArticleEntity(dto, email);
            articleRepository.save(articleEntity);
            PositionEntity postitionEntity = new PositionEntity(dto, articleEntity.getArticleNum());
            positionRepository.save(postitionEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostArticleResponseDto.success();
    }

    @Override
        public ResponseEntity<? super GetArticleResponseDto> getArticle(Integer articleNum) {
            ArticleEntity articleEntity = null;
            PositionEntity positionEntity = null;

            try {
                articleEntity = articleRepository.findByArticleNum(articleNum);
                positionEntity = positionRepository.findByArticleNum(articleNum);
                if (articleEntity == null) return GetArticleResponseDto.noExistArticle();
            } catch (Exception exception){
                exception.printStackTrace();
                return ResponseDto.databaseError();
            }
            return GetArticleResponseDto.success(articleEntity, positionEntity);
        }

    @Override
    public ResponseEntity<? super GetArticleListResponseDto> getArticleList() {
        List<ArticleEntity> articleEntities;
        try {
            articleEntities = articleRepository.findAll();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetArticleListResponseDto.success(articleEntities, positionRepository);
    }

    @Override
    public ResponseEntity<? super GetArticleListResponseDto> getFilteredArticleList(GetFilterArticleRequestDto filterDto) {
        List<ArticleEntity> articleEntities;
        List<ArticleEntity> filteredArticles;
        try {
            // JSON의 time 필드를 리스트로 사용
            List<String> times = filterDto.getTime();

            // JSON의 position 필드를 파싱하여 리스트로 변환
            List<String> positions = Arrays.stream(filterDto.getPosition().split(","))
                                           .map(String::trim)
                                           .collect(Collectors.toList());

            // 로컬과 시간 리스트를 기반으로 게시글 찾기
            articleEntities = articleRepository.findByLocalAndTimes(filterDto.getLocal(), times);

            // 필터링된 게시글 중 주어진 포지션이 하나라도 "없음"인 게시글 찾기
            filteredArticles = articleEntities.stream()
                .filter(article -> {
                    PositionEntity position = positionRepository.findByArticleNum(article.getArticleNum());
                    if (position == null) {
                        return false;
                    }
                    // 포지션이 하나라도 "없음"인지 확인
                    return positions.stream().anyMatch(pos -> {
                        switch (pos) {
                            case "catcher": return "없음".equals(position.getCatcher());
                            case "leftFielder": return "없음".equals(position.getLeftFielder());
                            case "secondBase": return "없음".equals(position.getSecondBase());
                            // 필요한 경우 다른 포지션들도 추가
                            case "shortstop": return "없음".equals(position.getShortstop());
                            case "firstBase": return "없음".equals(position.getFirstBase());
                            case "centerFielder": return "없음".equals(position.getCenterFielder());
                            case "rightFielder": return "없음".equals(position.getRightFielder());
                            case "pitcher": return "없음".equals(position.getPitcher());
                            case "thirdBase": return "없음".equals(position.getThirdBase());
                            default: return false;
                        }
                    });
                })
                .collect(Collectors.toList());

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetArticleListResponseDto.success(filteredArticles, positionRepository);
    }
}
