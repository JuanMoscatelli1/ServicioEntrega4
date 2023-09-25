package domain.entities.incidentes;

import domain.entities.actores.miembros.Miembro;
import domain.entities.actores.miembros.MiembroPorComunidad;
import domain.entities.servicios.Establecimiento;
import domain.entities.servicios.Servicio;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class IncidenteMiembro extends Incidente{
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int incidenteMiembro_codigo;*/

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "miembroPorComunidad_codigo", referencedColumnName = "miembroPorComunidad_codigo")
    private MiembroPorComunidad miembro;

    public IncidenteMiembro(String descripcion, Servicio servicio, LocalDateTime fechaRealizacion, Establecimiento establecimiento, MiembroPorComunidad miembro) {
        super(descripcion,servicio, fechaRealizacion,establecimiento);
        this.miembro = miembro;
    }

    public IncidenteMiembro() {

    }

    @Override
    public List<Miembro> obtenerContactos() {
        return miembro.getComunidad().getMiembros().stream().map(m->m.getMiembro()).collect(Collectors.toList());
    }

    @Override
    public void notificar() {

    }
}
