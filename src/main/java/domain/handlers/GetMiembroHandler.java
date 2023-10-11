package domain.handlers;

import domain.entities.respuestas.ComunidadRespuesta;
import domain.entities.respuestas.MiembroRespuesta;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.jetbrains.annotations.NotNull;

public class GetMiembroHandler implements Handler {
    @OpenApi(
            summary = "Obtener Miembro segun ID",
            operationId = "ObetenerMiembroSegunId",
            path = "/api/miembros/{Id}",
            methods = HttpMethod.GET,
            tags = {"Miembros"},
            pathParams = @OpenApiParam(name = "id", description = "ID miembro a buscar", required = true, type = Integer.class),
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = MiembroRespuesta.class)),
                    @OpenApiResponse(status = "404" )
            }
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {

    }
}
