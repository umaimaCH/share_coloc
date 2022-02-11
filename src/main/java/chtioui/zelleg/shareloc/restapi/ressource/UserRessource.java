package chtioui.zelleg.shareloc.restapi.ressource;

import java.security.Principal;
import java.sql.SQLException;

import chtioui.zelleg.shareloc.restapi.model.Collocation;
import chtioui.zelleg.shareloc.restapi.model.CollocationUser;
import chtioui.zelleg.shareloc.restapi.model.Service;
import chtioui.zelleg.shareloc.restapi.model.User;
import chtioui.zelleg.shareloc.restapi.security.Secured;
import chtioui.zelleg.shareloc.restapi.service.UserManager;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

@Path("/collocation")
public class UserRessource {
	
	@Context
	SecurityContext securityContext;
	
UserManager umanager = new UserManager();
	
	@Path("/Signin")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	/*
	@ApiOperation(value = "Insert user")
	@ApiResponses({
	    @ApiResponse(code=200, message="User inserted")
	})
	*/
	public User addUser(User user) {
		 return umanager.insertUser(user);
	}
	
	
	@Path("/connect/{email}/{password}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkUser(@PathParam("email") String email,@PathParam("password") String password) throws SQLException {
		
		 return " Token : "+umanager.checkUser(email,password);
		
	}
	
	@Path("/createCollocation")
	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	/*
	@ApiOperation(value = "Create a new colocation")
	@ApiResponses({
	    @ApiResponse(code=200, message="Colocation Created")
	})
	*/
	public Collocation create(Collocation collocation) {
		
		Principal p = securityContext.getUserPrincipal();
		String id = p.getName();
		
		 return umanager.createCollocation(collocation,id);
		
	}
	
	@Path("/createService")
	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	/*
	@ApiOperation(value = "Create a new service")
	@ApiResponses({
	    @ApiResponse(code=200, message="Service Created")
	})
	*/
	public Service createS(Service service) {
			Principal p = securityContext.getUserPrincipal();
			String id = p.getName();
			return umanager.createService(service,id);
		
	}
	
	@Path("/deleteService/{title}")
	@DELETE
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	/*
	@ApiOperation(value = "Remove existing service")
	@ApiResponses({
	    @ApiResponse(code=200, message="Removed")
	})
	*/
	public String deleteS(@PathParam("title") String title) {
			Principal p = securityContext.getUserPrincipal();
			String id = p.getName();
			return umanager.deleteService(title,id);
		
	}
	
	
	
	@Path("/updateUser")
	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	/*
	@ApiOperation(value = "Updates existing user")
	@ApiResponses({
	    @ApiResponse(code=200, message="Updated"),
	    @ApiResponse(code=404, message="Not found")
	})
	*/
	public User updateUser(User user) {
			Principal p = securityContext.getUserPrincipal();
			String id = p.getName();
			return umanager.updateUser(user,id);
		
	}
	
	@Path("/deleteUser")
	@DELETE
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	/*
	@ApiOperation(value = "Remove existing user")
	@ApiResponses({
	    @ApiResponse(code=200, message="Removed")
	})
	*/
	public String deleteUser() {
			Principal p = securityContext.getUserPrincipal();
			String id = p.getName();
			return umanager.deleteUser(id);
		
	}
	
	
	@Path("/updateService")
	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	/*
	@ApiOperation(value = "Updates existing service")
	@ApiResponses({
	    @ApiResponse(code=200, message="Updated"),
	    @ApiResponse(code=404, message="Not found")
	})
	*/
	public Service updateS(Service service) {
			Principal p = securityContext.getUserPrincipal();
			String id = p.getName();
			return umanager.updateService(service,id);
		
	}
	
	
	@Path("/deleteCollocation/{name}")
	@DELETE
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	/*
	@ApiOperation(value = "Remove existing colocation")
	@ApiResponses({
	    @ApiResponse(code=200, message="Removed")
	})
	*/
	public String deleteCollocation(@PathParam("name") String name) {
			Principal p = securityContext.getUserPrincipal();
			String id = p.getName();
			return umanager.deleteCollocation(name,id);
		
	}
	
	@Path("/updateCollocation")
	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	/*
	@ApiOperation(value = "Updates existing colocation")
	@ApiResponses({
	    @ApiResponse(code=200, message="Updated"),
	    @ApiResponse(code=404, message="Not found")
	})
	*/
	public Collocation updateCollocation(Collocation collocation) {
			Principal p = securityContext.getUserPrincipal();
			String id = p.getName();
			return umanager.updateCollocation(collocation,id);
		
	}
	
	@Path("/addUserintoCollocation")
	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	public CollocationUser addUserintoCollocation(CollocationUser collocationuser) {
			Principal p = securityContext.getUserPrincipal();
			String id = p.getName();
			return umanager.addUserintoCollocation(collocationuser,id);
		
	}
	
	
	
	
}