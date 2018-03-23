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

/**
 * REST Web Service - Group.
 *
 * @author Mauricio Generoso
 * @since 16/03/2018
 * @version 0.1
 */
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
		if (groups == null || (groups != null && groups.isEmpty())) {
			throw new WebApplicationException("There are no groups.", Status.NOT_FOUND);
		}
		return Response.status(Status.OK).entity(groups).build();
	}

	/**
	 * Get group by id.
	 * 
	 * @param id
	 *            - Id.
	 * @return Response.
	 */
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") Long id) {
		if (!verifyExistsGroup(id)) {
			throw new WebApplicationException("Group Not found with id: " + id, Status.NOT_FOUND);
		}
		return Response.status(Status.OK).entity(groupControls.getById(id)).build();
	}

	/**
	 * Get group by name.
	 * 
	 * @param name
	 *            - Name.
	 * @return Response.
	 */
	@GET
	@Path("/name/{name}")
	public Response getByName(@PathParam("name") String name) {

		Group group = groupControls.getByName(name);
		if (group != null) {
			return Response.status(Status.OK).entity(group).build();
		}
		throw new WebApplicationException("Group Not found with name: " + name, Status.NOT_FOUND);
	}

	/**
	 * Save new group.
	 * 
	 * @param group
	 *            - Group.
	 * @return Response.
	 */
	@POST
	public Response save(@Valid Group group) {
		if (group.getId() != null) {
			throw new WebApplicationException("Group with Id: " + group.getId(), Status.BAD_REQUEST);
		}

		if (groupControls.getByName(group.getNameGroup()) != null) {
			throw new WebApplicationException("Always exists group with that name.", Status.CONFLICT);
		}

		if (groupControls.save(group)) {
			return Response.status(Status.CREATED).entity(group).build();
		}
		throw new WebApplicationException("Error inserting new group.", Status.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Update Group.
	 * 
	 * @param group
	 *            - Group.
	 * @return Response.
	 */
	@PUT
	public Response update(@Valid Group group) {
		if (group.getId() == null || !verifyExistsGroup(group.getId())) {
			throw new WebApplicationException("Group Not found with id: " + group.getId(), Status.BAD_REQUEST);
		}

		if (groupControls.getByName(group.getNameGroup()) != null) {
			throw new WebApplicationException("Always exists group with that name.", Status.CONFLICT);
		}

		if (groupControls.save(group)) {
			return Response.status(Status.ACCEPTED).entity(group).build();
		}
		throw new WebApplicationException("Error updating group.", Status.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Delete group.
	 * 
	 * @param id
	 *            - Id.
	 * @return Response.
	 */
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		if (!verifyExistsGroup(id)) {
			throw new WebApplicationException("Group Not found with id: " + id, Status.NOT_FOUND);
		}
		if (groupControls.delete(groupControls.getById(id))) {
			return Response.status(Status.NO_CONTENT).build();
		}
		throw new WebApplicationException("Error deleting group.", Status.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Verify exists group.
	 * 
	 * @param id
	 *            - Id.
	 * @return boolean.
	 */
	private boolean verifyExistsGroup(Long id) {
		return groupControls.getById(id) != null;
	}

}
