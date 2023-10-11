package domain.handlers;

import domain.entities.actores.Comunidad;
import domain.entities.respuestas.ComunidadRespuesta;
import domain.repositorios.RepoGeneral;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class GetComunidadHandler implements Handler {
    @OpenApi(
            summary = "Obtener Comunidad segun ID",
            operationId = "ObetenerComunidadId",
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
        Integer idBuscado = context.pathParamAsClass("id", Integer.class).get();
        ComunidadRespuesta comunidadRespuesta = new ComunidadRespuesta(RepoGeneral.getInstance().buscarComunidad(idBuscado));
        if(comunidadRespuesta.getComunidad_codigo() == -1 ){
            context.status(404);
        }else{
            context.status(202).json(comunidadRespuesta);
        }
    }
}
