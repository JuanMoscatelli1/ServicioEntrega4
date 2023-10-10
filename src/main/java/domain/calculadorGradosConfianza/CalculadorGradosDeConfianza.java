package domain.calculadorGradosConfianza;

import domain.entities.actores.Comunidad;
import domain.entities.actores.miembros.Miembro;
import domain.entities.actores.miembros.MiembroPorComunidad;
import domain.entities.incidentes.IncidenteMiembro;
import domain.repositorios.RepoGeneral;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CalculadorGradosDeConfianza {

    private List<VerificacionIncidente> verificadores;
    double incrementoPorIncidente = 0.5;

    public CalculadorGradosDeConfianza(){
        verificadores = new ArrayList<>();
        this.verificadores.add(new AperturaFraudulenta());
        this.verificadores.add(new CierreFraudulento());
    }

    public void execute(){
        List<Miembro> miembros = obtenerMiembros();
        List<Comunidad> comunidades = obtenerComunidades();
        miembros.forEach(this::calcularPuntaje);
        comunidades.forEach(this::calcularPuntajeComunidad);
        RepoGeneral.getInstance().persistirMiembros(miembros);
        RepoGeneral.getInstance().persistirComunidades(comunidades);
    }

    private void calcularPuntajeComunidad(Comunidad comunidad) {
        comunidad.totalPuntaje();
    }

    private List<Comunidad> obtenerComunidades() {
        return RepoGeneral.getInstance().buscarComunidades();
    }

    private void calcularPuntaje(Miembro m) {
        for (MiembroPorComunidad miembroPorComunidad : m.getComunidades()){
            List<IncidenteMiembro> incidentesMiembro = obtenerIncidentesCerradosMiembro(miembroPorComunidad);

            incidentesMiembro.forEach(i->calcularPuntajeIncidente(i,miembroPorComunidad));

            List<IncidenteMiembro> incidentesAbiertos = obtenerIncidentesAbiertosMiembro(miembroPorComunidad);
            if(!incidentesAbiertos.isEmpty()) {
                m.actualizarPuntaje(incidentesAbiertos.size() * incrementoPorIncidente);
            }
        }

    }

    private void calcularPuntajeIncidente(IncidenteMiembro i, MiembroPorComunidad m) {
        AtomicBoolean resultado = new AtomicBoolean(true);
        for (VerificacionIncidente verificador : verificadores){
            verificador.verificar(i,m.getMiembro(),resultado);

        }
        boolean resultadoBool = resultado.get();
        if(resultadoBool) m.getMiembro().actualizarPuntaje(incrementoPorIncidente);
    }


    private List<IncidenteMiembro> obtenerIncidentesCerradosMiembro(MiembroPorComunidad m) {
        return  RepoGeneral.getInstance().buscarIncidentesCerrados(m);
    }

    private List<IncidenteMiembro> obtenerIncidentesAbiertosMiembro(MiembroPorComunidad m) {
        return RepoGeneral.getInstance().buscarIncidentesAbiertos(m);
    }

    private List<Miembro> obtenerMiembros() {
        return RepoGeneral.getInstance().buscarMiembros();
    }

}
