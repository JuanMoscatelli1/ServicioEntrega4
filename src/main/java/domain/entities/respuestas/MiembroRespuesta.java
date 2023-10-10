package domain.entities.respuestas;

import domain.entities.actores.miembros.Miembro;

public class MiembroRespuesta {
    private Integer id;
    private String gradoDeConfianza;
    private Double puntaje;


    public Integer getId() {
        return id;
    }

    public String getGradoDeConfianza() {
        return gradoDeConfianza;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public MiembroRespuesta(Miembro miembro){
        this.id=miembro.getMiembro_codigo();
        this.gradoDeConfianza=miembro.getGradoConfianza().getGradoConfianza();
        this.puntaje=miembro.getGradoConfianza().getPuntaje();
    }


}
