package domain.calculadorGradosConfianza;

import domain.entities.actores.Comunidad;
import domain.entities.actores.miembros.Miembro;
import domain.entities.incidentes.IncidenteMiembro;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CalculadorGradosDeConfianza {

    private List<VerificacionIncidente> verificadores;
    double incrementoPorIncidente = 0.5;

    public CalculadorGradosDeConfianza(){
        this.verificadores.add(new AperturaFraudulenta());
        this.verificadores.add(new CierreFraudulento());
    }

    public void execute(){
        List<Miembro> miembros = obtenerMiembros();
        List<Comunidad> comunidades = obtenerComunidades();
        miembros.forEach(this::calcularPuntaje);
        comunidades.forEach(this::calcularPuntajeComunidad);
    }

    private void calcularPuntajeComunidad(Comunidad comunidad) {
        comunidad.totalPuntaje();
    }

    private List<Comunidad> obtenerComunidades() {
        return null;
    }

    private void calcularPuntaje(Miembro m) {

        List<IncidenteMiembro> incidentesMiembro = obtenerIncidentesCerradosMiembro(m);

        incidentesMiembro.forEach(i->calcularPuntajeIncidente(i,m));

        List<IncidenteMiembro> incidentesAbiertos = obtenerIncidentesAbiertosMiembro(m);
        if(!incidentesAbiertos.isEmpty()) {
            m.actualizarPuntaje(incidentesAbiertos.size() * incrementoPorIncidente);
        }
    }

    private void calcularPuntajeIncidente(IncidenteMiembro i, Miembro m) {
        AtomicBoolean resultado = new AtomicBoolean(true);
        for (VerificacionIncidente verificador : verificadores){
            verificador.verificar(i,m,resultado);

            boolean resultadoBool = resultado.get();
            if(resultadoBool) m.actualizarPuntaje(incrementoPorIncidente);
        }

    }


    private List<IncidenteMiembro> obtenerIncidentesCerradosMiembro(Miembro m) {
        return null;
    }

    private List<IncidenteMiembro> obtenerIncidentesAbiertosMiembro(Miembro m) {
        return null;
    }

    private List<Miembro> obtenerMiembros() {
        return null;
    }

}
