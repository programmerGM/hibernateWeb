package com.project.dao;

import javax.persistence.Query;

import org.hibernate.Session;

import com.project.entities.Group;
import com.project.entities.Student;

/**
 * GroupDao.
 * 
 * @author Maurício Generoso.
 * @since 15/03/2018.
 * @version 0.1
 */
public class GroupDao extends GenericDaoAbstract<Group> {

    /**
     * Get group by name.
     *
     * @param name - Group name.
     * @return Group.
     */
    public Group getByName(String name) {
	Session session = getSessionFactory().openSession();
	session.beginTransaction();
	Group group = null;
	try {
	    Query query = session.createNamedQuery(Group.class.getSimpleName() + ".getByName");
	    query.setParameter("nameGroup", name);
	    group = (Group) query.getSingleResult();
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
