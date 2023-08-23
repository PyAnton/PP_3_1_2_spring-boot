package com.example.pp_3_1_2_springboot.dao;

import com.example.pp_3_1_2_springboot.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void add(User user) {
        getEntityManager().persist(user);
    }

    @Override
    public void dell(long id) {
        getEntityManager()
                .createQuery("delete from User where id=: id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void update(long id, String firstName, String lastName, String email) {
        getEntityManager()
                .createQuery("update User SET firstName =: firstName, lastName =: lastName," +
                        " email =: email where id=: id")

                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .setParameter("email", email)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<User> listUsers() {
        return getEntityManager().createQuery("From User").getResultList();
    }

}
