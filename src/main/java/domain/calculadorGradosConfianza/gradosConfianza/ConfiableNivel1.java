package domain.calculadorGradosConfianza.gradosConfianza;

import domain.entities.actores.miembros.Miembro;

public class ConfiableNivel1 extends GradoConfianza{
    public ConfiableNivel1(Double puntajeNuevo) {
        super(puntajeNuevo);
    }

    @Override
    public void verificar(Miembro miembro, double puntaje) {
        if (puntaje < 3){
            miembro.setGradoConfianza(new ConReservas(puntaje));
        }
        if (puntaje > 5){
            miembro.setGradoConfianza(new ConfiableNivel2(puntaje));
        }
    }
}
