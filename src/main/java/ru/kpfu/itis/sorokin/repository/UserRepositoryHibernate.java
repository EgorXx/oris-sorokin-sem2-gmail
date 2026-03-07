package ru.kpfu.itis.sorokin.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.sorokin.model.User;


import java.util.List;

@Component
@Transactional
public class UserRepositoryHibernate {

    private final SessionFactory sessionFactory;

    public UserRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return getCurrentSession().createQuery("from User").list();
    }
}
