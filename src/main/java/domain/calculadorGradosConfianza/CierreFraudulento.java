package domain.calculadorGradosConfianza;

import domain.entities.actores.miembros.Miembro;
import domain.entities.incidentes.IncidenteMiembro;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CierreFraudulento extends VerificacionIncidente{


    @Override
    public void verificar(IncidenteMiembro i, Miembro m, AtomicBoolean resultado) {
        List<IncidenteMiembro> incidentesCerrados = obtenerIncidentesCerrados();
        if(verificarCierreFraudulento(i,incidentesCerrados)){
            m.actualizarPuntaje(-0.2);
            resultado.set(false);
        }

    }

    private Boolean verificarCierreFraudulento(IncidenteMiembro i, List<IncidenteMiembro> incidentesCerrados) {
        return incidentesCerrados.stream().anyMatch(in->in.esParecido(i));
    }

    private List<IncidenteMiembro> obtenerIncidentesCerrados() {
        return null;
    }


}
