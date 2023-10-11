package domain.handlers;

import domain.entities.actores.Comunidad;
import domain.entities.respuestas.ComunidadRespuesta;
import domain.repositorios.RepoGeneral;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiResponse;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GetComunidadesHandler implements Handler {
    @OpenApi(
            summary = "Obtener las comunidades Actualizados",
            operationId = "ObetenerTodasLasComunidades",
            path = "/api/comunidades",
            methods = HttpMethod.GET,
            tags = {"Comunidades"},
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = ComunidadRespuesta[].class)),
                    @OpenApiResponse(status = "404" )
            }
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {
        List<Comunidad> comunidades = RepoGeneral.getInstance().buscarComunidades();
        List<ComunidadRespuesta> comunidadesRespuesta = new ArrayList<>();
        for (Comunidad comunidad : comunidades) {
           ComunidadRespuesta comunidadRespuesta = new ComunidadRespuesta(comunidad);
            comunidadesRespuesta.add(comunidadRespuesta);
        }
        context.json(comunidadesRespuesta);
    }
}
