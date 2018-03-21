package com.project.controls;

import static org.junit.Assert.assertEquals;
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
 * Test GroupControls.
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
    public GroupControlsTest() {
    }

    /**
     * Init tests.
     */
    @Before
    public void init() {
	groupControls = new GroupControls();
    }

    /**
     * Test of save.
     */
    @Test
    public void testASave() {
	System.out.println("testASave");

	List<Group> groups = Arrays.asList(new Group("Group1"), new Group("Group2"), new Group("Group3"),
		new Group("Group4"));

	groups.forEach(g -> {
	    assertTrue(groupControls.save(g));
	});
    }

    /**
     * Test of save - duplicate.
     */
    @Test
    public void testBSaveDuplicate() {
	System.out.println("testBSaveDuplicate");

	assertTrue(!groupControls.save(new Group("Group1")));
    }

    /**
     * Test update.
     */
    @Test
    public void testCUpdate() {
	System.out.println("testCUpdate");

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
     * Test get group by id.
     */
    @Test
    public void testDGetById() {
	System.out.println("testDGetById");

	List<Group> groups = groupControls.getAll();
	assertNotNull(groups);

	Group group = groupControls.getById(groups.get(0).getId());
	assertNotNull(group);
    }

    /**
     * Test get group by id.
     */
    @Test
    public void testEGetByName() {
	System.out.println("testGGetByName");

	Group group = groupControls.getByName("Group1");
	assertNotNull(group);
	assertEquals(group.getNameGroup(), "Group1");
    }

    /**
     * Test get all groups.
     */
    @Test
    public void testFGetAll() {
	System.out.println("testEGetAll");

	List<Group> groups = groupControls.getAll();
	assertNotNull(groups);
    }

    /**
     * Test delete.
     */
    @Test
    public void testGDelete() {
	System.out.println("testGDelete");

	List<Group> groups = groupControls.getAll();

	assertNotNull(groups);
	groups.forEach(g -> {
	    assertTrue(groupControls.delete(g));
	});
    }

}
