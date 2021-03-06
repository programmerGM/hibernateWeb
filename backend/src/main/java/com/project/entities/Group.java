package com.project.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity Group.
 * 
 * @author Maurício Generoso.
 * @since 09/03/2018.
 * @version 0.1
 */
@Entity
@Table(name = "student_group")
@NamedQueries({ @NamedQuery(name = "Group.searchAll", query = "SELECT g FROM Group g"),
		@NamedQuery(name = "Group.getByName", query = "SELECT g FROM Group g where g.nameGroup= :nameGroup") })
public class Group extends BasicEntity {

	private static final long serialVersionUID = 6364586923311620003L;

	@Column(name = "name_group", length = 50, unique = true, nullable = false)
	private String nameGroup;

	@JsonIgnore
	@OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
	private List<Student> student;

	/**
	 * Constructor.
	 */
	public Group() {
	}

	/**
	 * Constructor.
	 * 
	 * @param nameGroup
	 *            - Nome do grupo.
	 */
	public Group(String nameGroup) {
		this.nameGroup = nameGroup;
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 *            - Id.
	 * @param nameGroup
	 *            - Nome do grupo.
	 */
	public Group(Long id, String nameGroup) {
		this.setId(id);
		this.nameGroup = nameGroup;
	}

	/**
	 * Get nome do grupo.
	 */
	public String getNameGroup() {
		return nameGroup;
	}

	/**
	 * Set nome do grupo.
	 * 
	 * @param nameGroup
	 *            - Nome do grupo.
	 */
	public void setNameGroup(String nameGroup) {
		this.nameGroup = nameGroup;
	}

	/**
	 * Get student.
	 * 
	 * @return List
	 */
	public List<Student> getStudent() {
		return student;
	}

	/**
	 * Set student.
	 * 
	 * @param student
	 *            - List.
	 */
	public void setStudent(List<Student> student) {
		this.student = student;
	}

}
