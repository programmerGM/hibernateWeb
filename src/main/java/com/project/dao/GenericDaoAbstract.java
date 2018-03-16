package com.project.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.project.conection.HibernateUtil;
import com.project.entities.BasicEntity;

/**
 * Classe abstrata genérica para armazenar/remover/consultar no banco de dados.
 * O único método abstrato é o consulta por nome, pois nem todos os Dao's
 * utilizarão este método, logo, os Dao's que o utilizam deverão fazer
 * "sobreescrita de método".
 *
 * @author Mauricio Generoso
 * @param <T> - Classe genérica que fará a persistência
 * @since 09/03/2018
 * @version 0.1
 */
public abstract class GenericDaoAbstract<T extends BasicEntity> {

    private final SessionFactory sessionFactory;

    /**
     * Construtor.
     */
    public GenericDaoAbstract() {
	this.sessionFactory = new HibernateUtil().getSessionFactory();
    }
		
    /**
     * Retorna a fábrica de sessão.
     *
     * @return SessionFactory
     */
    protected SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    /**
     * Método para dar insert e update no Objeto.
     *
     * @param t - Objeto a set alterado ou inserido.
     * @return boolean - True em caso de sucesso e false em caso de problemas.
     */
    public boolean save(T t) {
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	try {
	    if (t.getId() == null) {
		session.persist(t); // insert
	    } else {
		if (!session.contains(t)) {
		    if (session.get(t.getClass(), t.getId()) == null) { throw new Exception(
			    "Erro ao atualizar objeto da classe " + t.getClass()); }
		}
		session.merge(t); // update
	    }
	    session.getTransaction().commit();
	} catch (Exception e) {
	    this.error("GenericDaoAbstract.save() - " + e.getMessage());
	    session.getTransaction().rollback();
	    return false;
	} finally {
	    session.close();
	}
	return true;
    }

    /**
     * Método para remover um Objeto.
     *
     * @param t - Objeto a ser removido.
     * @return boolean - True em caso de sucesso e false em caso de problemas.
     */
    public boolean delete(T t) {
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	try {
	    session.delete(t);
	    session.getTransaction().commit();
	} catch (Exception e) {
	    this.error("GenericDaoAbstract.delete() - " + e.getMessage());
	    session.getTransaction().rollback();
	    return false;
	} finally {
	    session.close();
	}
	return true;
    }

    /**
     * Consulta um Objeto por id.
     *
     * @param clazz - Classe do Objeto a ser consutlado
     * @param id - Id do Objeto a ser consultado
     * @return T - Objeto consultado
     */
    public T getById(Class<T> clazz, Long id) {
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	T obj = null;
	try {
	    obj = (T) session.get(clazz, id);
	} catch (Exception e) {
	    this.error("GenericDaoAbstract.getForId() - " + e.getMessage());
	} finally {
	    session.getTransaction().commit();
	    session.close();
	}
	return obj;
    }

    /**
     * Método para retornar todos os registros. É necessário que a entidade
     * consultada tenha uma consulta nomeada com o nome da classe, +
     * ".searchAll". Por exemplo uma entidade Usuario:
     * "Usuario.searchAll".
     *
     * @param className Nome da classe buscada
     * @return List - Lista com todos os objetos consultados.
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll(String className) {
	Session session = sessionFactory.openSession();
	//session.beginTransaction();
	List<T> list = null;
	try {
	    Query query = session.createNamedQuery(className + ".searchAll");
	    list = (List<T>) query.getResultList();
	} catch (Exception e) {
	    this.error("GenericDaoAbstract.getAll() - " + className + " - " + e.getMessage());
	} finally {
	    //session.getTransaction().commit();
	    session.close();
	}
	return list;
    }

    /**
     * Método para mostrar o erro.
     *
     * @param message - menasgem a ser enviada por e-mail
     */
    protected void error(String message) {
	System.out.println("============= Erro =============");
	System.out.println(message);
	System.out.println("============= Erro =============");
    }

}
