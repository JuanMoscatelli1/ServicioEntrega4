package domain.calculadorGradosConfianza;

import domain.entities.actores.miembros.Miembro;
import domain.entities.incidentes.IncidenteMiembro;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class VerificacionIncidente {
    public abstract void verificar(IncidenteMiembro i, Miembro m, AtomicBoolean resultado);
}
