package com.project.controls;

import java.util.List;

import com.project.dao.GenericDaoAbstract;
import com.project.entities.BasicEntity;

/**
 * Classe abstrata com métodos comuns e obrigatórios em todos os controles.
 *
 * @author Mauricio Generoso
 * @param <D> - Objeto dao.
 * @param <T> - Objeto manipulado pelo dao.
 * @since 09/03/2018
 * @version 0.1
 */
public abstract class Controls<D extends GenericDaoAbstract, T extends BasicEntity> {

    private final D dao;

    /**
     * Construtor.
     *
     * @param dao - Classe Dao do objeto que será manipulado.
     */
    public Controls(D dao) {
        this.dao = dao;
    }

    /**
     * Método para salvar o Objeto no banco de dados.
     *
     * @param t - Objeto a ser salvo.
     * @return boolean - True em caso de sucesso e false em caso de falha.
     */
    public boolean save(T t) {
        return getDao().save(t);
    }

    /**
     * Método para remover um objeto no banco de dados.
     *
     * @param t - Objeto a ser removido.
     * @return boolean - True em caso de sucesso e false em caso de falha.
     */
    public boolean delete(T t) {
        return getDao().delete(t);
    }

    /**
     * get para retornar o dao utilizado e poder ser utilizado pelas classes
     * herdeiras.
     *
     * @return GenericDao - Retornará a classe Dao herdada de GenericDao
     * instanciada no construtor.
     */
    protected D getDao() {
        return dao;
    }

    /**
     * Método para consultar o Objeto por Id.
     *
     * @param id - Id do Objeto a ser consultado
     * @return Retorna o Objeto consultado
     */
    public abstract T getById(Long id);

    /**
     * Método para retornar lista de todos os Objetos registrado.
     *
     * @return List - Retorna a lista dos Objetos
     */
    public abstract List<T> getAll();
}
