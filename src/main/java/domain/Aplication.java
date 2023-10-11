package domain;

import domain.handlers.GetComunidadesHandler;
import domain.handlers.GetMiembrosHandler;
import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;

public class Aplication {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
                    config.plugins.register(new OpenApiPlugin(new OpenApiConfiguration()));
                    config.plugins.register(new SwaggerPlugin(new SwaggerConfiguration()));
                })
                .get("/", ctx -> ctx.result("Hello World"))
                .start(4567);
        System.out.println("Check out Swagger UI docs at http://localhost:4567/swagger");
        app.get("api/miembros",new GetMiembrosHandler());
        app.get("api/comunidades",new GetComunidadesHandler());
    }
}
