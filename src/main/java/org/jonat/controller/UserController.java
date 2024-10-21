package org.jonat.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jonat.entity.UserEntity;
import org.jonat.service.UserService;



@Path("/Users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response getAllUsers(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize
    ) {
        var users = userService.findAll(page, pageSize);

        return  Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(
            @PathParam("id") Long id
            ) {
        var user = userService.findById(id);

        return  Response.ok(user).build();
    }

    @POST
    public Response createUser(UserEntity user) {
        return Response.ok(userService.createUser(user)).build();
    }

    @PATCH
    @Path("/{id}")
    public Response updateUser(UserEntity user,
                               @PathParam("id") Long id ){
        return Response.ok(userService.patchUser(user, id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);

        return Response.ok("Usuario deletado com sucesso").build();
    }
}
