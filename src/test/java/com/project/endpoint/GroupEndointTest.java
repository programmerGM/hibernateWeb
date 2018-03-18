package com.project.endpoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.project.entities.Group;

/**
 * Test Endpoint Group.
 * 
 * @author Mauricio Generoso.
 * @since 16/03/2018.
 * @version 0.1
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GroupEndointTest extends JerseyTest {

    /**
     * Configuration.
     */
    @Override
    public Application configure() {
	enable(TestProperties.LOG_TRAFFIC);
	enable(TestProperties.DUMP_ENTITY);
	return new ResourceConfig(GroupEndpoint.class).register(GroupEndpoint.class);
    }

    /**
     * Test create Group.
     */
    @Test
    public void testAPostSave() {
	System.out.println("testAPostSave");
	
	Group group = new Group("Group Test");
	Response output = target("/api/groups").request().post(Entity.entity(group, MediaType.APPLICATION_JSON));

	assertEquals("Should return status 200", 200, output.getStatus());
	assertNotNull("Should return notification", output.getEntity());
    }

//    @Test
//    public void testFetchAll() {
//	Response output = target("/groups").request().get();
//	assertEquals("should return status 200", 200, output.getStatus());
//	assertNotNull("Should return list", output.getEntity());
//    }
//
//    @Test
//    public void testUpdate() {
//	Group group = new Group(1L, "Group Test");
//	Response output = target("/groups").request().put(Entity.entity(group, MediaType.APPLICATION_JSON));
//	assertEquals("Should return status 204", 204, output.getStatus());
//    }
//
//    @Test
//    public void testDelete() {
//	Response output = target("/groups/1").request().delete();
//	assertEquals("Should return status 204", 204, output.getStatus());
//    }

}
