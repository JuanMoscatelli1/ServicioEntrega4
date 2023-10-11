package domain.handlers;

import domain.entities.actores.miembros.Miembro;
import domain.entities.respuestas.ComunidadRespuesta;
import domain.entities.respuestas.MiembroRespuesta;
import domain.repositorios.RepoGeneral;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.jetbrains.annotations.NotNull;

public class GetMiembroHandler implements Handler {
    @OpenApi(
            summary = "Obtener Miembro segun ID",
            operationId = "ObetenerMiembroId",
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
        Integer idBuscado = context.pathParamAsClass("id", Integer.class).get();
        MiembroRespuesta miembroRespuesta = new MiembroRespuesta(RepoGeneral.getInstance().buscarMiembro(idBuscado));
        if(miembroRespuesta.getId()==-1){context.status(404);}
        else {context.json(miembroRespuesta);}
    }
}
