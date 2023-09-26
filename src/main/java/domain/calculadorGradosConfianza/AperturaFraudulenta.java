package domain.calculadorGradosConfianza;

import domain.entities.actores.miembros.Miembro;
import domain.entities.incidentes.IncidenteMiembro;

import java.util.concurrent.atomic.AtomicBoolean;

public class AperturaFraudulenta extends VerificacionIncidente{


    @Override
    public void verificar(IncidenteMiembro i, Miembro m, AtomicBoolean resultado) {
        if(i.obtenerDiferencia().toMinutes()<3){
           m.actualizarPuntaje(-0.2);
            resultado.set(false);
        }

    }
}
