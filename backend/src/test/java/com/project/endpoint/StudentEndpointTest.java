package com.project.endpoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
 * Test Endpoint Student.
 * 
 * @author Mauricio Generoso.
 * @since 20/03/2018.
 * @version 0.1
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentEndpointTest extends JerseyTest {

    private static final String PATH_STUDENTS = Paths.STUDENTS;
    private static final String PATH_GROUPS = Paths.GROUPS;

    /**
     * Configuration.
     */
    @Override
    public Application configure() {
	enable(TestProperties.LOG_TRAFFIC);
	enable(TestProperties.DUMP_ENTITY);
	return new ResourceConfig(StudentEndpoint.class).register(GroupEndpoint.class);
    }

    /**
     * Delete all students and test get all students.
     * 
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @Test
    public void testADelete() throws JsonParseException, JsonMappingException, IOException {
	System.out.println("testADelete");

	Response response = target(PATH_STUDENTS).request().get();
	if (response.getStatus() == Status.OK.getStatusCode()) {
	    String listString = response.readEntity(String.class);
	    List<Student> list = Arrays.asList(new ObjectMapper().readValue(listString, Student[].class));

	    assertNotNull(list);
	    list.forEach(s -> {
		Response resp = target(PATH_STUDENTS + "/" + s.getId()).request().delete();
		assertEquals("Should return status 204", Status.NO_CONTENT.getStatusCode(), resp.getStatus());
	    });
	}
	response = target(PATH_STUDENTS).request().get();
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

	response = target(PATH_GROUPS + "/name/Group Test").request().get();
	assertEquals("Should return status 200", Status.OK.getStatusCode(), response.getStatus());
	group = new ObjectMapper().readValue(response.readEntity(String.class), Group.class);

	assertNotNull(group);
	assertNotNull(group.getId());

	Student student = new Student("Student Test", group);
	response = target(PATH_STUDENTS).request().post(Entity.entity(student, MediaType.APPLICATION_JSON));

	assertEquals("Should return status 201", Status.CREATED.getStatusCode(), response.getStatus());
	assertNotNull("Should return notification", response.getEntity());
    }

    /**
     * Test create Student - With id.
     * 
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @Test
    public void testCPostSaveWithId() throws JsonParseException, JsonMappingException, IOException {
	System.out.println("testCPostSaveWithId");

	Student student = new Student(1L, "Student Test");
	Response response = target(PATH_STUDENTS).request().post(Entity.entity(student, MediaType.APPLICATION_JSON));

	assertEquals("Should return status 400", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	assertNotNull("Should return student", response.getEntity());
    }

    /**
     * Test create Student - Duplicate.
     */
    @Test
    public void testDPostSaveDuplicate() {
	System.out.println("testDPostSaveDuplicate");

	Student student = new Student("Student Test");
	Response response = target(PATH_STUDENTS).request().post(Entity.entity(student, MediaType.APPLICATION_JSON));
	assertEquals("Should return status 209", Status.CONFLICT.getStatusCode(), response.getStatus());
    }

    /**
     * Test Update without id.
     */
    @Test
    public void testEUpdateWithoutId() {
	System.out.println("testEUpdateWithoutId");

	Student student = new Student("Student Test");
	Response response = target(PATH_STUDENTS).request().put(Entity.entity(student, MediaType.APPLICATION_JSON));
	assertEquals("Should return status 400", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    /**
     * Test Update not found.
     */
    @Test
    public void testFUpdateNotFound() {
	System.out.println("testFUpdateNotFound");

	Student student = new Student(-1L, "Student Test");
	Response response = target(PATH_STUDENTS).request().put(Entity.entity(student, MediaType.APPLICATION_JSON));
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

	Response response = target(PATH_STUDENTS + "/name/Student Test").request().get();
	assertEquals("Should return status 200", Status.OK.getStatusCode(), response.getStatus());
	Student student = new ObjectMapper().readValue(response.readEntity(String.class), Student.class);
	assertNotNull(student);

	response = target(PATH_STUDENTS).request().put(Entity.entity(student, MediaType.APPLICATION_JSON));
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

	Response response = target(PATH_STUDENTS + "/name/Student Test").request().get();
	assertEquals("Should return status 200", Status.OK.getStatusCode(), response.getStatus());
	Student student = new ObjectMapper().readValue(response.readEntity(String.class), Student.class);
	assertNotNull(student);

	student.setNameStudent("Student Test changed");
	response = target(PATH_STUDENTS).request().put(Entity.entity(student, MediaType.APPLICATION_JSON));

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

	Response response = target(PATH_STUDENTS + "/name/Student Test changed").request().get();
	assertEquals("Should return status 200", Status.OK.getStatusCode(), response.getStatus());
	Student student = new ObjectMapper().readValue(response.readEntity(String.class), Student.class);
	assertNotNull(student);

	response = target(PATH_STUDENTS + "/" + student.getId()).request().get();
	assertEquals("Should return status 200", Status.OK.getStatusCode(), response.getStatus());
	student = new ObjectMapper().readValue(response.readEntity(String.class), Student.class);
	assertNotNull(student);
    }

    /**
     * Test get with invalid id.
     */
    public void testJGetInvalidId() {
	System.out.println("testJGetInvalidId");

	Response response = target(PATH_STUDENTS + "/" + -1).request().get();
	assertEquals("Should return status 404", Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    /**
     * Test Delete.
     */
    @Test
    public void testJDelete() {
	System.out.println("testJDelete");

	Response response = target(PATH_STUDENTS + "/" + -1).request().delete();
	assertEquals("Should return status 404", Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    /**
     * Delete all students created for tests.
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
}
