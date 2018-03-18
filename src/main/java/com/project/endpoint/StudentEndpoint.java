package com.project.endpoint;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.project.controls.StudentControls;
import com.project.endpoint.constants.Paths;
import com.project.entities.Student;

/**
 * REST Web Service - Student.
 *
 * @author Mauricio Generoso
 * @since 09/03/2018
 * @version 0.1
 */
@Path(Paths.V1)
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

    @GET
    @Path(Paths.STUDENTS)
    public Response getAll() {
	return null;
    }

    @GET
    @Path(Paths.STUDENTS + "/{id}")
    public Response getById(@PathParam("id") Long id) {
	return null;
    }

    @POST
    @Path(Paths.STUDENTS)
    public Response save(@Valid Student student) {
	return null;
    }

    @PUT
    @Path(Paths.STUDENTS)
    public Response update(@Valid Student student) {
	return null;
    }

    @DELETE
    @Path(Paths.STUDENTS + "/{id}")
    public Response delete(@PathParam("id") Long id) {
	return null;
    }

}
