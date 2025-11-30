package com.clinix.web.resource;

import com.clinix.application.AstronautService;
import com.clinix.config.ApiRoutes;
import com.clinix.persistence.entity.Astronaut;
import com.clinix.persistence.projection.AstronautInfo;
import com.clinix.web.dto.request.AstronautUpsert;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.UUID;

@Path(ApiRoutes.Astronauts.ROOT)
public class AstronautResource {

    private static final Logger LOG = Logger.getLogger(AstronautResource.class);

    private final AstronautService astronautService;

    @Inject
    public AstronautResource(AstronautService astronautService) {
        this.astronautService = astronautService;
    }


    @GET
    @Path("/{uuid}")
    public Response getUserById(@PathParam("uuid") UUID uuid) {

        AstronautInfo astronaut = astronautService.findByUuid(uuid);

        return Response.ok(astronaut).build();
    }

    @POST
    public Response create(AstronautUpsert astronautInsert) {

        AstronautInfo astronaut = astronautService.create(astronautInsert);

        return Response.ok(astronaut).build();
    }

    @PUT
    @Path("/{uuid}")
    public Response update(@PathParam("uuid") UUID uuid, AstronautUpsert astronautUpdate) {
        AstronautInfo astronaut = astronautService.update(uuid, astronautUpdate);
        return Response.ok(astronaut).build();
    }

    @DELETE
    @Path("/{uuid}")
    public Response delete(@PathParam("uuid") UUID uuid) {
        astronautService.delete(uuid);
        return Response.noContent().build();
    }
}
