package com.example.contentservice.model.repository;

import com.example.contentservice.model.entity.Content;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;


@Repository
@RequiredArgsConstructor
@Transactional
public class ContentRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Content content){

        em.persist(content);

    }

    public Iterable<Content> getContentsByAll (){
        return em.createQuery("select c from Content c",Content.class)
                .getResultList();

    }

    public Content getContent(Long id){
        return em.createQuery("select c from Content c where c.id=:id",Content.class)
                .setParameter("id",id)
                .getSingleResult();
    }




    public Content updateContent(Long id, Long curRatingPerson, double avgValue) {
        Content content= em.find(Content.class,id);

        content.setRating(curRatingPerson,avgValue);
        em.merge(content);
        return content;
    }
}
