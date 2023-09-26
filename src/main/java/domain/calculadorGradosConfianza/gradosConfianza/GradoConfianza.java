package domain.calculadorGradosConfianza.gradosConfianza;

import domain.entities.actores.miembros.Miembro;
import lombok.Getter;

@Getter
public abstract class GradoConfianza {

    private double puntaje;

    public GradoConfianza(Double puntajeNuevo){
        this.puntaje = puntajeNuevo;
    }

    public GradoConfianza() {

    }

    public void actualizar(Miembro miembro, double puntajeNuevo){
        puntaje += puntajeNuevo;
        verificar(miembro, puntaje);
    }

    public abstract void verificar(Miembro miembro, double puntaje);


}
