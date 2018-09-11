package com.revature.repository;

import com.revature.domain.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@EnableTransactionManagement
public class GenreRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Genre> getGenres() {
        Session s = sessionFactory.getCurrentSession();
        return s.getNamedQuery("getGenres").list();
    }

    public Genre getGenreById(int id) {
        Session s = sessionFactory.getCurrentSession();
        return (Genre) s.get(Genre.class, id);
    }
}
