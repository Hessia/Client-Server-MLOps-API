package com.elif.mlops.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryResource {

    @GET
    public Map<String, Object> getApiInfo() {
        Map<String, Object> info = new HashMap<>();

        info.put("version", "1.0");
        info.put("name", "MLOps Pipeline Management API");
        info.put("admin", "elif@westminster.ac.uk");
        info.put("workspaces", "/api/v1/workspaces");
        info.put("models", "/api/v1/models");

        return info;
    }
}
