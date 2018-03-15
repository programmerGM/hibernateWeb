package com.project.dao;

import com.project.entities.Group;
import com.project.entities.Student;

/**
 * GroupDao.
 * 
 * @author Maurício Generoso
 * @since 15/03/2018
 * @version 0.1
 */
public class GroupDao extends GenericDaoAbstract<Group> {

    /**
     * Find group by name.
     *
     * @param name - Group name.
     * @return Group.
     */
    public Group getByName(String name) {
	Session session = getSessionFactory().openSession();
	session.beginTransaction();
	Group group = null;
	try {
	    Query query = session.createNamedQuery(Student.class.getSimpleName() + ".getByName");
	    query.setParameter("nameGroup", name);
	    group = (Student) query.getSingleResult();
	    session.getTransaction().commit();
	} catch (Exception e) {
	    this.error("GroupDao.getByName() - " + e.getMessage());
	    session.getTransaction().rollback();
	} finally {
	    session.close();
	}
	return group;
    }
    
}
