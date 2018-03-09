package com.project.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class Student.
 * 
 * @author Mauricio Generoso.
 * @since 09/03/2018
 * @version 0.1
 */
@Entity
@Table(name = "student")
public class Student extends BasicEntity {

    private static final long serialVersionUID = 5109956187789284163L;

    @Column(length = 100, nullable = false)
    private String nameStudant;

    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_user_group"), nullable = false)
    private Group group;

    /**
     * Constructor.
     */
    public Student() {}

    /**
     * Construtor.
     * 
     * @param nameStudant - Nome do estudante.
     */
    public Student(String nameStudant) {
	super();
	this.nameStudant = nameStudant;
    }

    /**
     * Get nameStudant.
     * 
     * @return String.
     */
    public String getNameStudant() {
	return nameStudant;
    }

    /**
     * Set nameStudant.
     * 
     * @param nameStudant - Nome do estudante.
     */
    public void setNameStudant(String nameStudant) {
	this.nameStudant = nameStudant;
    }

    /**
     * Get Group.
     * 
     * @return Group.
     */
    public Group getGroup() {
	return group;
    }

    /**
     * Set Group.
     * 
     * @param group - Grupo do usuário.
     */
    public void setGroup(Group group) {
	this.group = group;
    }

}
