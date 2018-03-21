package com.project.conection;

import org.junit.Test;

import com.project.conection.HibernateUtil;

/**
 * Test conection with database Mysql.
 *
 * @author Mauricio Generoso.
 * @since 09/03/2018.
 * @version 0.1
 */
public class HibernateUtilTest {

    /**
     * Test connection.
     * 
     * @throws RuntimeException
     */
    @Test
    public void connection() throws RuntimeException {
	if (!new HibernateUtil().getSessionFactory()
		.isOpen()) { throw new RuntimeException("Connection database failed."); }
    }

}
