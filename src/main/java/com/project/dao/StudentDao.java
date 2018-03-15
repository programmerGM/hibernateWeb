package com.project.dao;

import javax.persistence.Query;

import org.hibernate.Session;

import com.project.entities.Student;

/**
 * Classe DAO do estudante.
 * 
 * @author Mauricio Generoso.
 * @since 14/03/2018
 * @version 0.1
 */
public class StudentDao extends GenericDaoAbstract<Student> {

    /**
     * Consulta estudante pelo nome.
     *
     * @param name - Nome do estudante.
     * @return Student.
     */
    public Student getByName(String name) {
	Session session = getSessionFactory().openSession();
	session.beginTransaction();
	Student st = null;
	try {
	    Query query = session.createNamedQuery(Student.class.getSimpleName() + ".getByName");
	    query.setParameter("nameStudent", name);
	    st = (Student) query.getSingleResult();
	    session.getTransaction().commit();
	} catch (Exception e) {
	    this.error("StudentDao.getByName() - " + e.getMessage());
	    session.getTransaction().rollback();
	} finally {
	    session.close();
	}
	return st;
    }

}
