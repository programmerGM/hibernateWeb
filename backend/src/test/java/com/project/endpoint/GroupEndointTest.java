package com.project.endpoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
import com.project.entities.Student;

/**
 * Test Endpoint Group.
 * 
 * @author Mauricio Generoso.
 * @since 16/03/2018.
 * @version 0.1
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GroupEndointTest extends JerseyTest {

	private static final String PATH_STUDENTS = Paths.STUDENTS;
	private static final String PATH_GROUPS = Paths.GROUPS;

	/**
	 * Configuration.
	 */
	@Override
	public Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(GroupEndpoint.class).register(StudentEndpoint.class);
	}

	/**
	 * Delete all groups and test get all groups.
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@Test
	public void testADelete() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("testADelete");

		Response response = target(PATH_GROUPS).request().get();
		if (response.getStatus() == Status.OK.getStatusCode()) {
			String listString = response.readEntity(String.class);
			List<Group> list = Arrays.asList(new ObjectMapper().readValue(listString, Group[].class));

			assertNotNull(list);
			list.forEach(g -> {
				Response resp = target(PATH_GROUPS + "/" + g.getId()).request().delete();
				assertEquals("Should return status 204", Status.NO_CONTENT.getStatusCode(), resp.getStatus());
			});
		}
		response = target(PATH_GROUPS).request().get();
		assertEquals("Should return status 404", Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	/**
	 * Test create Group.
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@Test
	public void testBPostSave() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("testBPostSave");

		Group group = new Group("Group Test");
		Response response = target(PATH_GROUPS).request().post(Entity.entity(group, MediaType.APPLICATION_JSON));

		assertEquals("Should return status 201", Status.CREATED.getStatusCode(), response.getStatus());
		assertNotNull("Should return notification", response.getEntity());
		assertTrue((new ObjectMapper().readValue(response.readEntity(String.class), Group.class)) instanceof Group);
	}

	/**
	 * Test create Group - With id.
	 */
	@Test
	public void testCPostSaveWithId() {
		System.out.println("testCPostSaveWithId");

		Group group = new Group(1L, "Group Test");
		Response response = target(PATH_GROUPS).request().post(Entity.entity(group, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 400", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	/**
	 * Test create Group - Duplicate.
	 */
	@Test
	public void testDPostSaveDuplicate() {
		System.out.println("testDPostSaveDuplicate");

		Group group = new Group("Group Test");
		Response response = target(PATH_GROUPS).request().post(Entity.entity(group, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 209", Status.CONFLICT.getStatusCode(), response.getStatus());
	}

	/**
	 * Test Update without id.
	 */
	@Test
	public void testEUpdateWithoutId() {
		System.out.println("testEUpdateWithoutId");

		Group group = new Group("Group Test");
		Response response = target(PATH_GROUPS).request().put(Entity.entity(group, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 400", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	/**
	 * Test Update not found.
	 */
	@Test
	public void testFUpdateNotFound() {
		System.out.println("testFUpdateNotFound");

		Group group = new Group(-1l, "Group Test");
		Response response = target(PATH_GROUPS).request().put(Entity.entity(group, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 400", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	/**
	 * Test Update duplicate.
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@Test
	public void testGUpdateDuplicate() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("testGUpdateDuplicate");

		Response response = target(PATH_GROUPS + "/name/Group Test").request().get();
		assertEquals("Should return status 200", Status.OK.getStatusCode(), response.getStatus());
		Group group = new ObjectMapper().readValue(response.readEntity(String.class), Group.class);
		assertNotNull(group);

		response = target(PATH_GROUPS).request().put(Entity.entity(group, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 409", Status.CONFLICT.getStatusCode(), response.getStatus());
	}

	/**
	 * Test Update and get by name.
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@Test
	public void testHUpdate() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("testHUpdate");

		Response response = target(PATH_GROUPS + "/name/Group Test").request().get();
		assertEquals("Should return status 200", Status.OK.getStatusCode(), response.getStatus());
		Group group = new ObjectMapper().readValue(response.readEntity(String.class), Group.class);
		assertNotNull(group);

		group.setNameGroup("Group Teste changed");
		response = target(PATH_GROUPS).request().put(Entity.entity(group, MediaType.APPLICATION_JSON));

		assertEquals("Should return status 201", Status.ACCEPTED.getStatusCode(), response.getStatus());
	}

	/**
	 * Test Update and get by name.
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@Test
	public void testIGet() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("testIGet");

		Response response = target(PATH_GROUPS + "/name/Group Teste changed").request().get();
		assertEquals("Should return status 200", Status.OK.getStatusCode(), response.getStatus());
		Group group = new ObjectMapper().readValue(response.readEntity(String.class), Group.class);
		assertNotNull(group);

		response = target(PATH_GROUPS + "/" + group.getId()).request().get();
		assertEquals("Should return status 200", Status.OK.getStatusCode(), response.getStatus());
		group = new ObjectMapper().readValue(response.readEntity(String.class), Group.class);
		assertNotNull(group);
	}

	/**
	 * Test get with invalid id.
	 */
	public void testJGetInvalidId() {
		System.out.println("testJGetInvalidId");

		Response response = target(PATH_GROUPS + "/" + -1).request().get();
		assertEquals("Should return status 404", Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	/**
	 * Test Delete.
	 */
	@Test
	public void testJDelete() {
		System.out.println("testJDelete");

		Response response = target(PATH_GROUPS + "/" + -1).request().delete();
		assertEquals("Should return status 404", Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	/**
	 * Delete all groups created for tests.
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test
	public void testKDelete() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("testKDelete");
		testADelete();
	}

	/**
	 * Test delete group with student.
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@Test
	public void testLDelete() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("testLDelete");

		/* Create group */
		Group group = new Group("Group Test");
		Response response = target(PATH_GROUPS).request().post(Entity.entity(group, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 201", Status.CREATED.getStatusCode(), response.getStatus());
		assertNotNull("Should return group", response.getEntity());

		/* Get group */
		response = target(PATH_GROUPS + "/name/Group Test").request().get();
		assertEquals("Should return status 200", Status.OK.getStatusCode(), response.getStatus());
		group = new ObjectMapper().readValue(response.readEntity(String.class), Group.class);
		assertNotNull(group);
		assertNotNull(group.getId());

		/* Create student 1*/
		Student student1 = new Student("Student Test1", group);
		response = target(PATH_STUDENTS).request().post(Entity.entity(student1, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 201", Status.CREATED.getStatusCode(), response.getStatus());
		assertNotNull("Should return student", response.getEntity());
		
		/* Create student 2*/
		Student student2 = new Student("Student Test2", group);
		response = target(PATH_STUDENTS).request().post(Entity.entity(student2, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 201", Status.CREATED.getStatusCode(), response.getStatus());
		assertNotNull("Should return student", response.getEntity());
		
		/* Get student 1*/
		response = target(PATH_STUDENTS + "/name/Student Test1").request().get();
		assertEquals("Should return status 200", Status.OK.getStatusCode(), response.getStatus());
		student1 = new ObjectMapper().readValue(response.readEntity(String.class), Student.class);
		assertNotNull(student1);
		
		/* Get student 2*/
		response = target(PATH_STUDENTS + "/name/Student Test2").request().get();
		assertEquals("Should return status 200", Status.OK.getStatusCode(), response.getStatus());
		student2 = new ObjectMapper().readValue(response.readEntity(String.class), Student.class);
		assertNotNull(student2);
		
		/* Delete student 1*/
		response = target(PATH_STUDENTS + "/" + student1.getId()).request().delete();
		assertEquals("Should return status 204", Status.NO_CONTENT.getStatusCode(), response.getStatus());
		
		/* Delete group*/
		response = target(PATH_GROUPS + "/" + group.getId()).request().delete();
		assertEquals("Should return status 204", Status.NO_CONTENT.getStatusCode(), response.getStatus());
		
	}

}
