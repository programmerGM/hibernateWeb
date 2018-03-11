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

    @Column(name = "name_student", length = 100, nullable = false)
    private String nameStudent;

    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_student_group"), nullable = false)
    private Group group;

    /**
     * Constructor.
     */
    public Student() {}

    /**
     * Construtor.
     * 
     * @param nameStudent - Nome do estudante.
     */
    public Student(String nameStudent) {
	super();
	this.nameStudent = nameStudent;
    }

    /**
     * Get nameStudent.
     * 
     * @return String.
     */
    public String getNameStudent() {
	return nameStudent;
    }

    /**
     * Set nameStudent.
     * 
     * @param nameStudent - Nome do estudante.
     */
    public void setNameStudent(String nameStudent) {
	this.nameStudent = nameStudent;
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
