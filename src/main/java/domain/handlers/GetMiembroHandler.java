package domain.handlers;

import domain.entities.actores.miembros.Miembro;
import domain.repositorios.RepoGeneral;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.jetbrains.annotations.NotNull;

public class GetMiembroHandler implements Handler {
    @OpenApi(
            path = "/api/miembros",
            methods = {HttpMethod.GET},
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = Miembro.class)),
                    @OpenApiResponse(status = "404" )
            }
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {
        context.json(RepoGeneral.getInstance().buscarMiembros());
    }
}