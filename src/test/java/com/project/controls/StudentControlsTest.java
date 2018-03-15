package com.project.controls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.project.entities.Group;
import com.project.entities.Student;

/**
 * Test.
 * 
 * @author Mauricio Generoso.
 * @since 14/03/2018
 * @version 0.1
 */
public class StudentControlsTest {

    private StudentControls studentControl;
    
    /**
     * Construct.
     */
    public StudentControlsTest() {
    }
    
    @Before
    public void init() {
	studentControl = new StudentControls();
    }
    
    /**
     * Test of save method.
     */
    @Test	
    public void testSave() {
        System.out.println("testSave");

        Group group = new Group("grupo junit");
        List<Student> students = Arrays.asList(new Student("Aluno1", group), new Student("Aluno2", group), new Student("Aluno3", group)); 
        
        students.forEach(e -> {
            assertTrue(studentControl.save(e));
        });
    }
    
    /**
     * Test of GetByName method.
     */
    @Test	
    public void testGetByName() {
        System.out.println("testGetByName");

        String name = "Student";
        Student student = studentControl.getByName(name);
        assertEquals(student.getNameStudent(), name);
    }
    
    /**
     * Test of getAll method.
     */
    @Test	
    public void testGetAll() {
        System.out.println("testGetAll");

        List<Student> list = studentControl.getAll();
        assertNotNull(list);
    }
    

    /**
     * Test of delete method.
     */
    @Test	
    public void testDelete() {
        System.out.println("testDelete");
        
        List<Student> students = studentControl.getAll();
        assertNotNull(students);
        
        students.forEach(e -> {
            assertTrue(studentControl.delete(e));
        });
        
    }
    
}
