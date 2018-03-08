package com.project.connect;

import javax.security.auth.login.Configuration;

import org.

import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Maurício Generoso
 * @since 07/03/2018
 * @version 0.1
 */
public class HibernateUtil {

    private final SessionFactory sessionFactory;

    /**
     * Construtor.
     */
    public HibernateUtil() {
	try {
	    sessionFactory = new Configuration().configure().buildSessionFactory();
	} catch (HibernateException ex) {
	    System.err.println("Initial SessionFactory creation failed." + ex);
	    throw new ExceptionInInitializerError(ex);
	}
    }

    /**
     * Método para obter conexão(sessão) com o banco de dados.
     *
     * @return SessionFactory
     */
    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

}
