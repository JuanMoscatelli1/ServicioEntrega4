package domain.calculadorGradosConfianza.gradosConfianza;

import domain.entities.actores.miembros.Miembro;

public class ConfiableNivel2 extends GradoConfianza{
    public ConfiableNivel2(Double puntajeNuevo) {
        super(puntajeNuevo);
    }

    @Override
    public void verificar(Miembro miembro, double puntaje) {
        if (puntaje < 5){
            miembro.setGradoConfianza(new ConfiableNivel1(puntaje));
        }
    }
}
