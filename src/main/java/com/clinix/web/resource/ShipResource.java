package com.clinix.web.resource;


import com.clinix.application.ShipService;
import com.clinix.config.ApiRoutes;
import com.clinix.persistence.entity.Ship;
import com.clinix.persistence.projection.ShipInfo;
import com.clinix.web.dto.request.ShipUpsert;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.UUID;

@Path(ApiRoutes.Ships.ROOT)
public class ShipResource {

    private static final Logger LOG = Logger.getLogger(ShipResource.class);

    private final ShipService shipService;

    @Inject
    public ShipResource(ShipService shipService) {
        this.shipService = shipService;
    }


    @GET
    @Path("/{uuid}")
    public Response getUserById(@PathParam("uuid") UUID uuid) {

        ShipInfo ship = shipService.findByUuid(uuid);

        return Response.ok(ship).build();
    }

    @POST
    public Response create(ShipUpsert shipInsert) {

        ShipInfo ship = shipService.create(shipInsert);

        return Response.ok(ship).build();
    }

    @PUT
    @Path("/{uuid}")
    public Response update(@PathParam("uuid") UUID uuid, ShipUpsert shipUpdate) {

        ShipInfo ship = shipService.update(uuid, shipUpdate);

        return Response.ok(ship).build();
    }

    @DELETE
    @Path("/{uuid}")
    public Response delete(@PathParam("uuid") UUID uuid) {
        shipService.delete(uuid);
        return Response.noContent().build();
    }
}
