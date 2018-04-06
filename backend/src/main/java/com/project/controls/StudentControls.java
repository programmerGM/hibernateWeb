package com.project.controls;

import java.util.List;

import com.project.dao.StudentDao;
import com.project.entities.Student;

/**
 * Student controls.
 * 
 * @author Maurício Generoso.
 * @since 14/03/2018.
 * @version 0.1
 */
public class StudentControls extends Controls<StudentDao, Student> {

	/**
	 * Constructor.
	 */
	public StudentControls() {
		super(new StudentDao());
	}

	/**
	 * Consulta por ID.
	 */
	@Override
	public Student getById(Long id) {
		return (Student) getDao().getById(Student.class, id);
	}

	/**
	 * Consulta todos.
	 */
	@Override
	public List<Student> getAll() {
		return getDao().getAll(Student.class.getSimpleName());
	}

	/**
	 * Busca o estudante pelo nome.
	 * 
	 * @param name
	 *            - Nome do estudante.
	 * @return Stufent.
	 */
	public Student getByName(String name) {
		return getDao().getByName(name);
	}
}