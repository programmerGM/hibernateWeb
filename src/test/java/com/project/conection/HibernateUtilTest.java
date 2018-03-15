package com.project.conection;

import org.junit.Test;

import com.project.conection.HibernateUtil;

/**
 * Testa a conexão com o banco de dados.
 *
 * @author Mauricio Generoso
 * @since 09/03/2018
 * @version 0.1
 */
public class HibernateUtilTest {

    /**
     * Testa a conexão com o banco de dados.
     * 
     * @throws RuntimeException
     */
    @Test
    public void connection() throws RuntimeException {
	if (!new HibernateUtil().getSessionFactory()
		.isOpen()) { throw new RuntimeException("Erro ao conectar com o banco de dados."); }
    }

}
