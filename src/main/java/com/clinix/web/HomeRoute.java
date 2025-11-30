package com.clinix.web;

import io.quarkus.vertx.web.Route;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mutiny.core.Vertx;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.openapi.annotations.Operation;


@Singleton
public class HomeRoute {

    private final Vertx vertx;
    private static final String INDEX_HTML_PATH = "META-INF/resources/index.html";

    @Inject
    public HomeRoute(Vertx vertx) {
        this.vertx = vertx;
    }


    @Operation(hidden = true)
    @Route(path = "/", produces = "text/html")
    void index(RoutingContext ctx) {

        vertx.fileSystem().readFile(INDEX_HTML_PATH)
                .subscribe().with(
                        buffer -> ctx.response()
                                .putHeader("Content-Type", "text/html; charset=UTF-8")
                                .end(buffer.toString()),
                        failure -> ctx.response().setStatusCode(404)
                                .putHeader("Content-Type", "text/plain; charset=UTF-8")
                                .end("Page non trouvée")
                );
    }
}
