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
    public StudentControlsTest() {
    }
    
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
     * End tests.
     */
    @After
    public void end() {
	groupControls.delete(group);
    }
    
    /**
     * Test save.
     */
    @Test	
    public void testASave() {
        System.out.println("testSave");

        List<Student> students = Arrays.asList(new Student("Student1", group), new Student("Student2", group), new Student("Student3", group)); 
        
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
    
    // HERE -----------------------------------------------
    
    /**
     * Test of GetByName method.
     */
    @Test	
    public void testGetByName() {
        System.out.println("testGetByName");

        String name = "Student";
        Student student = studentControls.getByName(name);
        assertEquals(student.getNameStudent(), name);
    }
    
    /**
     * Test of getAll method.
     */
    @Test	
    public void testGetAll() {
        System.out.println("testGetAll");

        List<Student> list = studentControls.getAll();
        assertNotNull(list);
    }
    

    /**
     * Test of delete method.
     */
    @Test	
    public void testDelete() {
        System.out.println("testDelete");
        
        List<Student> students = studentControls.getAll();
        assertNotNull(students);
        
        students.forEach(e -> {
            assertTrue(studentControls.delete(e));
        });
        
    }
    
}
