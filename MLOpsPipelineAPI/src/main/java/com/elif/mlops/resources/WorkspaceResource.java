package com.elif.mlops.resources;

import com.elif.mlops.DataStore;
import com.elif.mlops.MLWorkspace;
import com.elif.mlops.WorkspaceNotEmptyException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@Path("/workspaces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkspaceResource {

    @GET
    public Collection<MLWorkspace> getAllWorkspaces() {
        return DataStore.workspaces.values();
    }

    @POST
    public Response createWorkspace(MLWorkspace workspace) {
        DataStore.workspaces.put(workspace.getId(), workspace);

        return Response.status(Response.Status.CREATED)
                .entity(workspace)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getWorkspace(@PathParam("id") String id) {
        MLWorkspace workspace = DataStore.workspaces.get(id);

        if (workspace == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Workspace not found")
                    .build();
        }

        return Response.ok(workspace).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteWorkspace(@PathParam("id") String id) {
        MLWorkspace workspace = DataStore.workspaces.get(id);

        if (workspace == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Workspace not found")
                    .build();
        }

        if (workspace.getModelIds() != null && !workspace.getModelIds().isEmpty()) {
            throw new WorkspaceNotEmptyException(
                    "Workspace cannot be deleted because it still has assigned models");
        }

        DataStore.workspaces.remove(id);

        return Response.ok("Workspace deleted").build();
    }
}