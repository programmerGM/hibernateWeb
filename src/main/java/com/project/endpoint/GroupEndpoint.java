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

import com.project.controls.GroupControls;
import com.project.endpoint.constants.Paths;
import com.project.entities.Group;

@Path(Paths.GROUPS)
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
     * @return Response.
     */
    @GET
    public Response getAll() {

	List<Group> groups = groupControls.getAll();
	if (groups == null
		|| (groups != null && groups.isEmpty())) { return Response.status(Status.NOT_FOUND).build(); }
	return Response.status(Status.OK).entity(groups).build();
    }

    /**
     * Get group by id.
     * 
     * @param id - Id.
     * @return Response.
     */
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
	if(!verifyExistsGroup(id)) {
	    return Response.status(Status.NOT_FOUND).build();
	}
	return Response.status(Status.OK).entity(groupControls.getById(id)).build();
    }

    /**
     * Save new group.
     * 
     * @param group - Group.
     * @return Response.
     */
    @POST
    public Response save(@Valid Group group) {
	if (groupControls.save(group)) {
	    return Response.status(Status.CREATED).entity(group).build();
	}
	throw new WebApplicationException("Error inserting new group.");
    }

    /**
     * Update Group.
     * 
     * @param group - Group.
     * @return Response.
     */
    @PUT
    public Response update(@Valid Group group) {
	if(!verifyExistsGroup(group.getId())) {
	    return Response.status(Status.NOT_FOUND).build();
	}
	return Response.status(Status.ACCEPTED).entity(groupControls.save(group)).build();
    }

    /**
     * Delete group.
     * 
     * @param id - Id.
     * @return Response.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
	if(!verifyExistsGroup(id)) {
	    return Response.status(Status.NOT_FOUND).build();
	}
	if(groupControls.delete(groupControls.getById(id))) {
	    return Response.status(Status.NO_CONTENT).build();
	}
	return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * Verify exists group.
     * 
     * @param id - Id.
     * @return boolean.
     */
    private boolean verifyExistsGroup(Long id) {
	return groupControls.getById(id) == null;
    }

}
