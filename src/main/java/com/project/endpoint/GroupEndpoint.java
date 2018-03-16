package com.project.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("v1/groups")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GroupEndpoint {	

    @GetMapping("/teasdasda")
    public ResponseEntity<Group> getAll(){
	
    }
    
    
}
