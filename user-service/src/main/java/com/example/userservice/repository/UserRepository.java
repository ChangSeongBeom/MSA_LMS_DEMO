package com.example.userservice.repository;

import com.example.userservice.domain.Company;
import com.example.userservice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepository {


    private final EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findById(String loginId){
        System.out.println("loginId" +loginId);
        return em.createQuery("select u from User u where u.loginId=:id",User.class)
                .setParameter("id",loginId)
                .getSingleResult();

    }

    public List<User> findAll(){
        return em.createQuery("select u from User u",User.class)
                .getResultList();
    }

//    public User findByEmail(String username){
//        return em.createQuery("select u from User u where u.name=:name",User.class)
//                .setParameter("name",username)
//                .getSingleResult();
//    }

    public List<Company> listUser(){
        return em.createQuery("select c from Company c join fetch c.user",Company.class)
                .getResultList();
    }

}
