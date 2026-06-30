package com.elif.mlops.exceptions;

import com.elif.mlops.ErrorResponse;
import com.elif.mlops.LinkedWorkspaceNotFoundException;
import com.elif.mlops.ModelDeprecatedException;
import com.elif.mlops.WorkspaceNotEmptyException;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException ex) {

        if (ex instanceof WorkspaceNotEmptyException) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse(
                            409,
                            "Conflict",
                            ex.getMessage()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        if (ex instanceof LinkedWorkspaceNotFoundException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(
                            400,
                            "Bad Request",
                            ex.getMessage()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        if (ex instanceof ModelDeprecatedException) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(new ErrorResponse(
                            403,
                            "Forbidden",
                            ex.getMessage()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse(
                        500,
                        "Internal Server Error",
                        ex.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}