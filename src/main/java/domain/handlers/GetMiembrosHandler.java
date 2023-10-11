package domain.handlers;

import domain.entities.actores.miembros.Miembro;
import domain.entities.respuestas.MiembroRespuesta;
import domain.repositorios.RepoGeneral;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GetMiembrosHandler implements Handler {
    @OpenApi(
            summary = "Obtener los miembros Actualizados",
            operationId = "ObtenerMiembros",
            path = "/api/miembros",
            methods = HttpMethod.GET,
            tags = {"Miembros"},
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = MiembroRespuesta[].class)),
                    @OpenApiResponse(status = "404" )
            }
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {
        List<Miembro> miembros = RepoGeneral.getInstance().buscarMiembros();
        List<MiembroRespuesta> miembrosRespuesta = new ArrayList<>();
        for (Miembro miembro : miembros) {
            MiembroRespuesta miembroRespuesta = new MiembroRespuesta(miembro);
            miembrosRespuesta.add(miembroRespuesta);
        }
        context.json(miembrosRespuesta);
    }
}