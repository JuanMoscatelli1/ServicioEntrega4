package domain.calculadorGradosConfianza;

import domain.entities.actores.miembros.Miembro;

public class NoConfiable extends GradoConfianza{

    public NoConfiable(Double puntajeNuevo) {
        super(puntajeNuevo);
    }

    @Override
    public void verificar(Miembro miembro, double puntaje) {
        if (puntaje >=2) {
            miembro.setGradoConfianza(new ConReservas(puntaje));
        }
    }
}
