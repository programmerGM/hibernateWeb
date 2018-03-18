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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.project.controls.GroupControls;
import com.project.endpoint.constants.Paths;
import com.project.entities.Group;

@Path(Paths.V1)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GroupEndpoint {

    private GroupControls groupControls;

    /**
     * Construct.
     */
    public GroupEndpoint() {
	this.groupControls = new GroupControls();
    }

    /**
     * Get all groups.
     * 
     * @return
     */
    @GET
    @Path(Paths.GROUPS)
    public Response getAll() {

	List<Group> groups = groupControls.getAll();
	if (groups == null || (groups != null && groups.isEmpty())) { 
	    return Response.status(Status.NOT_FOUND).build(); 
	}
	return Response.status(Status.OK).entity(groups).build();
    }

    /**
     * Get group by id.
     * 
     * @param id - Id.
     * @return Response.
     */
    @GET
    @Path(Paths.GROUPS + "/{id}")
    public Response getById(@PathParam("id") Long id) {
	return null;
    }

    /**
     * Save new group.
     * 
     * @param group - Group.
     * @return Response.
     */
    @POST
    @Path(Paths.GROUPS)
    public Response save(@Valid Group group) {
	return Response.status(Status.CREATED).entity(groupControls.save(group)).build();
    }

    /**
     * Update Group.
     * 
     * @param group - Group.
     * @return Response.
     */
    @PUT
    @Path(Paths.GROUPS)
    public Response update(@Valid Group group) {
	return null;
    }

    /**
     * Delete group.
     * 
     * @param id - Id.
     * @return Response.
     */
    @DELETE
    @Path(Paths.GROUPS + "/{id}")
    public Response delete(@PathParam("id") Long id) {
	return null;
    }

}
