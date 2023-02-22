package com.example.reviewservice.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long id;

    private String loginId;

    private Long contentId;

    private String review;
    private Date creDtm;

    @Builder
    public Review(String loginId,Long contentId,Date creDtm,String review){
        this.loginId=loginId;
        this.contentId=contentId;
        this.review=review;
        this.creDtm=creDtm;
    }
}
