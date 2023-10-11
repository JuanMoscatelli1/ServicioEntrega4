package domain.handlers;

import domain.entities.respuestas.ComunidadRespuesta;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.jetbrains.annotations.NotNull;

public class GetComunidadHandler implements Handler {
    @OpenApi(
            summary = "Obtener Comunidad segun ID",
            operationId = "ObetenerComunidadSegunId",
            path = "/api/comunidades/{Id}",
            methods = HttpMethod.GET,
            tags = {"Comunidades"},
            pathParams = @OpenApiParam(name = "id", description = "ID comunidad a buscar", required = true, type = Integer.class),
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = ComunidadRespuesta.class)),
                    @OpenApiResponse(status = "404" )
            }
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {


    }
}
