package com.elif.mlops.resources;

import com.elif.mlops.DataStore;
import com.elif.mlops.LinkedWorkspaceNotFoundException;
import com.elif.mlops.MLWorkspace;
import com.elif.mlops.MachineLearningModel;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;
import java.util.stream.Collectors;

@Path("/models")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ModelResource {

    @GET
    public Collection<MachineLearningModel> getModels(@QueryParam("status") String status) {
        if (status == null || status.isEmpty()) {
            return DataStore.models.values();
        }

        return DataStore.models.values()
                .stream()
                .filter(model -> model.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    @POST
    public Response createModel(MachineLearningModel model) {
        MLWorkspace workspace = DataStore.workspaces.get(model.getWorkspaceId());

        if (workspace == null) {
            throw new LinkedWorkspaceNotFoundException("Workspace not found");
        }

        DataStore.models.put(model.getId(), model);
        workspace.getModelIds().add(model.getId());

        return Response.status(Response.Status.CREATED)
                .entity(model)
                .build();
    }
}