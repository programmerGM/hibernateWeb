package com.project.controls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.project.entities.Group;
import com.project.entities.Student;

/**
 * Test methods of controls student.
 * 
 * @author Mauricio Generoso.
 * @since 14/03/2018
 * @version 0.1
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentControlsTest {

    private StudentControls studentControls;
    private GroupControls groupControls;
    private Group group;

    /**
     * Construct.
     */
    public StudentControlsTest() {}

    /**
     * Init tests.
     */
    @Before
    public void init() {
	studentControls = new StudentControls();
	groupControls = new GroupControls();
	group = new Group("TestStudent");

	groupControls.save(group);
	assertNotNull(group.getId());
    }

    /**
     * Test save.
     */
    @Test
    public void testASave() {
	System.out.println("testSave");

	List<Student> students = Arrays.asList(new Student("Student1", group), new Student("Student2", group),
		new Student("Student3", group));

	students.forEach(e -> {
	    assertTrue(studentControls.save(e));
	});
    }

    /**
     * Test save - duplicate.
     */
    @Test
    public void testBSaveDuplicate() {
	System.out.println("testSaveDuplicate");

	assertTrue(!studentControls.save(new Student("Student1", group)));
    }

    /**
     * Test update.
     */
    @Test
    public void testCUpdate() {
	System.out.println("testCUpdate");

	List<Student> students = studentControls.getAll();
	assertNotNull(students);

	Student student = studentControls.getById(students.get(0).getId());
	assertNotNull(student);

	student.setNameStudent("Student1Changed");
	assertTrue(studentControls.save(student));
	student.setNameStudent("Student1");
	assertTrue(studentControls.save(student));
    }

    /**
     * Test get student by id.
     */
    @Test
    public void testDGetById() {
	System.out.println("testDGetById");

	List<Student> students = studentControls.getAll();
	assertNotNull(students);

	Student student = studentControls.getById(students.get(0).getId());
	assertNotNull(student);
    }

    /**
     * Test of GetByName method.
     */
    @Test
    public void testEGetByName() {
	System.out.println("testEGetByName");

	Student student = studentControls.getByName("Student1");
	assertNotNull(student);
	assertEquals(student.getNameStudent(), "Student1");
    }

    /**
     * Test of getAll method.
     */
    @Test
    public void testFGetAll() {
	System.out.println("testFGetAll");

	List<Student> list = studentControls.getAll();
	assertNotNull(list);
    }

    /**
     * Test of delete method.
     */
    @Test
    public void testGDelete() {
	System.out.println("testGDelete");

	List<Student> students = studentControls.getAll();

	assertNotNull(students);
	students.forEach(e -> {
	    assertTrue(studentControls.delete(e));
	});
    }

    /**
     * End tests.
     */
    @After
    public void end() {
	groupControls.delete(group);
    }

}
