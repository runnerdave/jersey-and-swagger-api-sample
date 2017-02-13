package net.runnerdave.api.services.v1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/**
 * Created by runnerdave on 13/02/17.
 */
@Path("/v1/users")
@Api(value = "/users", description="Manage Users")
public class UsersResource {
	private static final Logger log = Logger.getLogger(UsersResource.class.getName());
	
    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Find User", 
    		notes = "This API retrieves the public information for the user (Private info is returned if this is the auth user)" + 
    			    "<u>Input Parameters</u><ul><li><b>userId</b> is required</li></ul>")
    @ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success: { user profile }"),
			@ApiResponse(code = 400, message = "Failed: {\"error\":\"error description\", \"status\":\"FAIL\"}") 
			} )
    public Response getUserById(@ApiParam(value = "userId", required = true, defaultValue = "23456", allowableValues = "", allowMultiple = false)
	@PathParam("userId") String userId) {
    	
    	log.info("UsersResource::getUserById started userId=" + userId);
    	
    	if (userId == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"error\":\"Empty userId\", \"status\":\"FAIL\"}")
					.build();
		}
    	User user = new User();
    	user.setId("111125");
    	user.setName("Tom Jay");
    	
    	return Response.status(Response.Status.OK).entity(user).build();
    }
}
