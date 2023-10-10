package domain.calculadorGradosConfianza.gradosConfianza;

import domain.entities.actores.miembros.Miembro;

import javax.persistence.Entity;

@Entity
public class NoConfiable extends GradoConfianza{

    public NoConfiable(Double puntajeNuevo) {
        super(puntajeNuevo);
        this.gradoConfianza="NoConfiable";
    }

    public NoConfiable() {

    }

    @Override
    public void verificar(Miembro miembro, double puntaje) {
        if (puntaje >=2) {
            miembro.setGradoConfianza(new ConReservas(puntaje));
        }
    }
}
