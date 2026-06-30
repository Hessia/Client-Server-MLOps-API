package com.elif.mlops.resources;

import com.elif.mlops.DataStore;
import com.elif.mlops.EvaluationMetric;
import com.elif.mlops.MachineLearningModel;
import com.elif.mlops.ModelDeprecatedException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/models/{modelId}/metrics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EvaluationMetricResource {

    @GET
    public Response getMetrics(@PathParam("modelId") String modelId) {
        MachineLearningModel model = DataStore.models.get(modelId);

        if (model == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Model not found")
                    .build();
        }

        List<EvaluationMetric> modelMetrics =
                DataStore.metrics.getOrDefault(modelId, new ArrayList<>());

        return Response.ok(modelMetrics).build();
    }

    @POST
    public Response addMetric(@PathParam("modelId") String modelId, EvaluationMetric metric) {
        MachineLearningModel model = DataStore.models.get(modelId);

        if (model == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Model not found")
                    .build();
        }

        if (model.getStatus().equalsIgnoreCase("DEPRECATED")) {
            throw new ModelDeprecatedException(
                    "Deprecated models cannot accept new evaluation metrics");
        }

        if (metric.getId() == null || metric.getId().isEmpty()) {
            metric.setId(UUID.randomUUID().toString());
        }

        metric.setTimestamp(System.currentTimeMillis());

        DataStore.metrics
                .computeIfAbsent(modelId, k -> new ArrayList<>())
                .add(metric);

        model.setLatestAccuracy(metric.getAccuracyScore());

        return Response.status(Response.Status.CREATED)
                .entity(metric)
                .build();
    }
}