package domain.repositorios;

import domain.calculadorGradosConfianza.gradosConfianza.GradoConfianza;
import domain.entities.actores.Comunidad;
import domain.entities.actores.miembros.Miembro;
import domain.entities.actores.miembros.MiembroPorComunidad;
import domain.entities.incidentes.IncidenteMiembro;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//TODO: crear otros repos para cada clase
public class RepoGeneral {

    private static RepoGeneral instance;

    private RepoGeneral() {

    }

    public static RepoGeneral getInstance() {
        if (instance == null) {
            instance = new RepoGeneral();
        }
        return instance;
    }

    //miembros

    public List<Miembro> buscarMiembros(){
        EntityManager em = utils.BDUtils.getEntityManager();
        List<Miembro> miembros = em.createQuery("select m from Miembro m", Miembro.class)
                .getResultList();;
        return miembros;
    }

    public List<IncidenteMiembro> buscarIncidentesCerrados(MiembroPorComunidad miembro) {
        List<IncidenteMiembro> incidentes = buscarIncidentesMiembro(miembro);
        return incidentes.stream().filter(i->i.getResuelto().equals(true)).collect(Collectors.toList());
    }

    public List<IncidenteMiembro> buscarIncidentesCerrados() {
        List<IncidenteMiembro> incidentes = buscarIncidentesMiembroTotal();
        return incidentes.stream().filter(i->i.getResuelto().equals(true)).collect(Collectors.toList());
    }

    private List<IncidenteMiembro> buscarIncidentesMiembroTotal() {
        EntityManager em = utils.BDUtils.getEntityManager();
        return em.createQuery("select i from IncidenteMiembro i", IncidenteMiembro.class).
                getResultList();
    }
    private List<IncidenteMiembro> buscarIncidentesMiembro(MiembroPorComunidad miembro) {
        EntityManager em = utils.BDUtils.getEntityManager();
        return em.createQuery("select i from IncidenteMiembro i where i.miembro = ?1", IncidenteMiembro.class)
                .setParameter(1, miembro).getResultList();
    }

    public List<IncidenteMiembro> buscarIncidentesAbiertos(MiembroPorComunidad miembroPorComunidad) {
        List<IncidenteMiembro> incidentes = buscarIncidentesMiembro(miembroPorComunidad);
        return incidentes.stream().filter(i->i.getResuelto().equals(false)).collect(Collectors.toList());
    }

    public Miembro buscarMiembro(int idBuscado){
        EntityManager em = utils.BDUtils.getEntityManager();
        List<Miembro> miembros = em.createQuery("select m from Miembro m where m.miembro_codigo = ?1", Miembro.class)
                .setParameter(1,idBuscado).getResultList();
        return miembros.stream().findFirst().get();
    }

    //comunidades
    public List<Comunidad> buscarComunidades() {
        EntityManager em = utils.BDUtils.getEntityManager();
        List<Comunidad> comunidades = em.createQuery("select c from Comunidad c", Comunidad.class)
                .getResultList();;
        return comunidades;
    }

    public Comunidad buscarComunidad(int idBuscado){
        EntityManager em = utils.BDUtils.getEntityManager();
        List<Comunidad> comunidades = em.createQuery("select c from Comunidad c where c.comunidad_codigo = ?1", Comunidad.class)
                .setParameter(1,idBuscado).getResultList();
        return comunidades.stream().findFirst().get();
    }


    //persistir

    public void persistirMiembros(List<Miembro> miembros) {
        EntityManager em = utils.BDUtils.getEntityManager();
        utils.BDUtils.comenzarTransaccion(em);

        for (Miembro miembro : miembros){
            GradoConfianza gradoConfianzaActualizado = miembro.getGradoConfianza(); // Obtén la instancia actualizada de GradoConfianza
            miembro.setGradoConfianza(null); // Desvincula temporalmente la relación
            em.merge(miembro); // Realiza merge en el Miembro sin la relación
            miembro.setGradoConfianza(gradoConfianzaActualizado); // Asocia la instancia actualizada de GradoConfianza
            em.merge(miembro); // Realiza merge nuevamente en el Miembro
        }

        utils.BDUtils.commit(em);
    }

    public void persistirComunidades(List<Comunidad> comunidades) {
        EntityManager em = utils.BDUtils.getEntityManager();
        utils.BDUtils.comenzarTransaccion(em);

        for (Comunidad comunidad : comunidades){
            em.merge(comunidad);
        }

        utils.BDUtils.commit(em);
    }

}
