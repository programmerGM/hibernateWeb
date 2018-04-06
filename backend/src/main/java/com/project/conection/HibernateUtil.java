package com.project.conection;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Maurício Generoso
 * @since 09/03/2018
 * @version 0.1
 */
public class HibernateUtil {

	private final SessionFactory sessionFactory;

	/**
	 * Constructor.
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
	 * Method for to get connection with database.
	 *
	 * @return SessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
