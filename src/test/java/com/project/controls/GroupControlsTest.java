package com.project.controls;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.project.entities.Group;

/**
 * Test Controls Group.
 * 
 * @author Maurício Generoso.
 * @version 15/03/2018.
 * @version 0.1
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GroupControlsTest {

    private GroupControls groupControls;

    /**
     * Construct.
     */
    public GroupControlsTest() {}

    /**
     * Init tests.
     */
    @Before
    public void init() {
	groupControls = new GroupControls();
    }

    /**
     * Test of save method.
     */
    @Test
    public void testASave() {
	System.out.println("testSave");

	List<Group> groups = Arrays.asList(new Group("Group1"), new Group("Group2"), new Group("Group3"), new Group("Group4"));
	
	groups.forEach(g -> {
	    assertTrue(groupControls.save(g));
	});
    }

    /**
     * Test of save method - duplicate.
     */
    @Test
    public void testBSaveDuplicate() {
	System.out.println("testSaveDuplicate");

	assertTrue(!groupControls.save(new Group("Group1")));
    }

    /**
     * Test of update method.
     */
    @Test
    public void testCUpdate() {
	System.out.println("testUpdate");

	List<Group> groups = groupControls.getAll();
	assertNotNull(groups);
	
	Group group = groupControls.getById(groups.get(0).getId()); 
	assertNotNull(group);
	
	group.setNameGroup("Group1changed");
	assertTrue(groupControls.save(group));
	group.setNameGroup("Group1");
	assertTrue(groupControls.save(group));
    }
    
    /**
     * Test of delete method.
     */
    @Test
    public void testDDelete() {
	System.out.println("testDelete");

	List<Group> groups = groupControls.getAll();
	
	assertNotNull(groups);
	groups.forEach(g -> {
	    assertTrue(groupControls.delete(g));
	});
    }    
    
    
}
