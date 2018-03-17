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

import com.project.controls.GroupControls;
import com.project.entities.Group;

@Path("/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GroupEndpoint {	

    private static final String PATH = "/groups";
    
    private GroupControls groupControls;
    
    /**
     * Construct.
     */
    public GroupEndpoint() {
	super();
	this.groupControls = groupControls;
    }

    @GET
    @Path(PATH)
    public Response getAll(){
	return null;
    }
    
    @GET
    @Path(PATH + "/{id}")
    public Response getById(@PathParam("id") Long id){
	return null;
    }
    
    @POST
    @Path(PATH)
    public Response save(@Valid Group group){
	return null;
    }
    
    @PUT
    @Path(PATH)
    public Response update(@Valid Group group){
	return null;
    }
    
    @DELETE
    @Path(PATH + "/{id}")
    public Response delete(@PathParam("id") Long id){
	return null;
    }
    
}
