package com.project.endpoint;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.project.controls.StudentControls;
import com.project.endpoint.constants.Paths;
import com.project.entities.Student;

/**
 * REST Web Service - Student.
 *
 * @author Mauricio Generoso
 * @since 20/03/2018
 * @version 0.1
 */
@Path(Paths.STUDENTS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentEndpoint {

    private StudentControls studentsControls;

    /**
     * Construct.
     */
    public StudentEndpoint() {
	this.studentsControls = new StudentControls();
    }

    /**
     * Get all students.
     * 
     * @return Response.
     */
    @GET
    public Response getAll() {

	List<Student> students = studentsControls.getAll();
	if (students == null || (students != null && students.isEmpty())) { 
	    throw new WebApplicationException("There are no students.", Status.NOT_FOUND); 
	 }
	return Response.status(Status.OK).entity(students).build();
    }

    /**
     * Get student by id.
     * 
     * @param id - Id.
     * @return Response.
     */
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
	if(!verifyExistsStudents(id)) {
	    throw new WebApplicationException("Student Not found with id: " + id, Status.NOT_FOUND);
	}
	return Response.status(Status.OK).entity(studentsControls.getById(id)).build();
    }

    /**
     * Get student by name.
     * 
     * @param name - Name.
     * @return Response.
     */
    @GET
    @Path("/name/{name}")
    public Response getByName(@PathParam("name") String name) {
	
	Student group = studentsControls.getByName(name);
	if (group != null) {
	    return Response.status(Status.OK).entity(group).build();
	}
	throw new WebApplicationException("Student Not found with name: " + name, Status.NOT_FOUND);
    }
    
    /**
     * Save new student.
     * 
     * @param student - Student.
     * @return Response.
     */
    @POST
    public Response save(@Valid Student student) {
	if (student.getId() != null) {
	    throw new WebApplicationException("Student with Id: " + student.getId(), Status.BAD_REQUEST);
	}
	
	if (studentsControls.getByName(student.getNameStudent()) != null) {
	    throw new WebApplicationException("Always exists student with that name.", Status.CONFLICT);
	}
	
	if (studentsControls.save(student)) {
	    return Response.status(Status.CREATED).entity(student).build();
	}
	throw new WebApplicationException("Error inserting new student.", Status.INTERNAL_SERVER_ERROR);
    }

    /**
     * Update student.
     * 
     * @param student - Student.
     * @return Response.
     */
    @PUT
    public Response update(@Valid Student student) {
	if(student.getId() == null || !verifyExistsStudents(student.getId())) {
	    throw new WebApplicationException("Student Not found with id: " + student.getId(), Status.BAD_REQUEST);
	}
	
	if (studentsControls.getByName(student.getNameStudent()) != null) {
	    throw new WebApplicationException("Always exists student with that name.", Status.CONFLICT);
	}
	
	if (studentsControls.save(student)) {
	    return Response.status(Status.ACCEPTED).entity(student).build();
	}
	throw new WebApplicationException("Error updating student.", Status.INTERNAL_SERVER_ERROR);
    }

    /**
     * Delete student.
     * 
     * @param id - Id.
     * @return Response.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
	if(!verifyExistsStudents(id)) {
	    throw new WebApplicationException("Student Not found with id: " + id, Status.NOT_FOUND);
	}
	if(studentsControls.delete(studentsControls.getById(id))) {
	    return Response.status(Status.NO_CONTENT).build();
	}
	throw new WebApplicationException("Error deleting student.", Status.INTERNAL_SERVER_ERROR);
    }

    /**
     * Verify exists students.
     * 
     * @param id - Id.
     * @return boolean.
     */
    private boolean verifyExistsStudents(Long id) {
	return studentsControls.getById(id) != null;
    }
}
