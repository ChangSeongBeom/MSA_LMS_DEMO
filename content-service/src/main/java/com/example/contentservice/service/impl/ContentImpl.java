package com.example.contentservice.service.impl;


import com.example.contentservice.CustomMapper;
import com.example.contentservice.model.dto.ContentDto;
import com.example.contentservice.model.dto.RequestContentDto;
import com.example.contentservice.model.dto.RequestRatingDto;
import com.example.contentservice.model.entity.Content;
import com.example.contentservice.model.repository.ContentRepository;
import com.example.contentservice.response.ResponseContent;
import com.example.contentservice.response.ResponseRating;
import com.example.contentservice.service.ContentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;


@Service
@AllArgsConstructor
public class ContentImpl implements ContentService {

    private ContentRepository contentRepository;
    CustomMapper mapper=new CustomMapper();
    @Override
    public ContentDto joinContent(RequestContentDto requestContentDto) {
        Date now= Calendar.getInstance().getTime();
        Long hitNum= 0L;
        Long likeNum=0L;
        Long rating=0L;
        Long ratingPerson=0L;
        Content content=Content.builder()
                .loginId(requestContentDto.getLoginId())
                .description(requestContentDto.getDescription())
                .contentLength(requestContentDto.getContentLength())
                .capaNum(requestContentDto.getCapaNum())
                .hitNum(hitNum)
                .likeNum(likeNum)
                .rating(rating)
                .creDtm(now)
                .ratingPerson(ratingPerson)
                .build();
        contentRepository.save(content);

        ContentDto contentDto= mapper.strictModelMapper().map(content,ContentDto.class);
        return contentDto;
    }

    @Override
    public ResponseContent responseJoinContent(ContentDto contentDto) {

        ResponseContent responseContent=mapper.strictModelMapper().map(contentDto,ResponseContent.class);
        return responseContent;
    }

    @Override
    public Iterable<Content> getContentsByAll() {
        return contentRepository.getContentsByAll();
    }

    @Override
    public ResponseRating updateRating(RequestRatingDto requestRatingDto) {
        /*콘텐츠 아이디, 평점
        콘텐츠 아이디가 가진 평점을 업데이트 해준다.
        * */
        try {

            //요청으로 온 값
            Long id=requestRatingDto.getId();
            double curRating=requestRatingDto.getRating();

            //요청으로 온 콘텐츠 객체
            Content content = contentRepository.getContent(requestRatingDto.getId());

            //하나의 콘텐츠의 평가인원, 점수
            double preRating=content.getRating();
            Long preRatingPerson=content.getRatingPerson();
            Long curRatingPerson=0L;
            double avgValue=0L;
            //초기 세팅시
            if (preRatingPerson==0){
                curRatingPerson=1L;
                avgValue=curRating;
            }else{
                curRatingPerson+=preRatingPerson+1;
                avgValue=(preRatingPerson * preRating + curRating) / (curRatingPerson);

            }

            Content responseContent=contentRepository.updateContent(id,curRatingPerson,avgValue);

            ResponseRating responseRating = mapper.strictModelMapper().map(responseContent,ResponseRating.class);
            return responseRating;

        }catch (Exception e){
            e.getMessage();
        }

        return null;
    }
}
