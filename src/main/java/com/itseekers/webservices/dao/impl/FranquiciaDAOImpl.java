/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dao.impl;

import com.itseekers.webservices.dao.FranquiciaDAO;
import com.itseekers.webservices.dto.Franquicia;
import java.util.List;
import javax.ws.rs.Path;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cheve3601
 */
@Repository
@Transactional
public class FranquiciaDAOImpl implements FranquiciaDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Franquicia getFranquicia(long idFranquicica) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Franquicia franquicia = (Franquicia) session.get(Franquicia.class, idFranquicica);
            if (franquicia != null) {
                return franquicia;
            }
            return null;
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        } 
    }

    @Override
    public List<Franquicia> getFranquicias() {
     
        Session session = null;
        try {
            session = sessionFactory.openSession();
            //List<Franquicia> franquicias = session.createCriteria(Franquicia.class).list();
            Criteria franq = session.createCriteria(Franquicia.class);
            franq.add(Restrictions.gt("id", 1L));
            List<Franquicia> franquicias = franq.list();
            return franquicias;
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }
}
