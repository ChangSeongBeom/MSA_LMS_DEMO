package com.example.reviewservice.model.repository;

import com.example.reviewservice.model.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
@Transactional
public class ReviewRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Review review){
        em.persist(review);
    }

    public Iterable<Review> getReview(Long id){
        return em.createQuery("select r from Review r where r.contentId=:id",Review.class)
                .setParameter("id",id)
                .getResultList();
    }
}
