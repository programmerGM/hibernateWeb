package com.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Classe abstrata para permitir utilização do DAO genérico.
 *
 * @author Mauricio Generoso
 * @since 09/08/2018
 * @version 0.1
 */
@MappedSuperclass
public abstract class BasicEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    /**
     * Método para inserir o id.
     *
     * @param id - Id a ser inserido.
     */
    public void setId(Long id) {
        this.id = id;
}

    /**
     * Método para retornar o id das entidades herdeiras dessa classe..
     *
     * @return id - Id da entidade.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    /**
     * equals()
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null) return false;
	if (getClass() != obj.getClass()) return false;
	BasicEntity other = (BasicEntity) obj;
	if (id == null) {
	    if (other.id != null) return false;
	} else if (!id.equals(other.id)) return false;
	return true;
    }
    
    
}
