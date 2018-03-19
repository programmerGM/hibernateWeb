package com.project.endpoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.internal.HttpUrlConnector;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.endpoint.constants.Paths;
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

    private static final String PATH_GROUPS = Paths.GROUPS;

    /**
     * Configuration.
     */
    @Override
    public Application configure() {
	enable(TestProperties.LOG_TRAFFIC);
	enable(TestProperties.DUMP_ENTITY);
	return new ResourceConfig(GroupEndpoint.class);
    }

    /**
     * Delete all groups.
     */
    @Test
    public void testADelete() {
	Response output = target(PATH_GROUPS).request().get(); 
	
	List<Group> list = (List<Group>) output.getEntity();
	
	output = target("/groups/1").request().delete();
	assertEquals("Should return status 204", 204, output.getStatus());
	
	output = target(PATH_GROUPS).request().get(); 
	assertEquals("Should return status 404", 404, output.getStatus());
    }

    /**
     * Test create Group.
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    @Test
    public void testBPostSave() throws JsonParseException, JsonMappingException, IOException {
	System.out.println("testAPostSave");

	Group group = new Group("Group Test");
	Response output = target(PATH_GROUPS).request().post(Entity.entity(group, MediaType.APPLICATION_JSON));

	assertEquals("Should return status 201", 201, output.getStatus());
	assertNotNull("Should return notification", output.getEntity());
	assertTrue((new ObjectMapper().readValue(output.readEntity(String.class), Group.class)) instanceof Group);
    }

    //    /**
    //     * Test create Group - Duplicate.
    //     */
    //    @Test
    //    public void testBPostSaveDuplicate() {
    //	System.out.println("testAPostSave");
    //	
    //	Group group = new Group("Group Test");
    //	Response output = target(PATH).request().post(Entity.entity(group, MediaType.APPLICATION_JSON));
    //
    //	assertEquals("Should return status 209", 209, output.getStatus());
    //    }

    //    @Test
    //    public void testCFetchAll() {
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
