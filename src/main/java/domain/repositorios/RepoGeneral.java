package domain.repositorios;

import domain.entities.actores.Comunidad;
import domain.entities.actores.miembros.Miembro;
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

    public List<Miembro> buscarMiembros(){
        EntityManager em = utils.BDUtils.getEntityManager();
        List<Miembro> miembros = em.createQuery("select m from Miembro m", Miembro.class)
                .getResultList();;
        return miembros;
    }

    public List<IncidenteMiembro> buscarIncidentesCerrados() {
        List<IncidenteMiembro> incidentes = buscarIncidentes();
        return incidentes.stream().filter(i->i.getResuelto().equals(true)).collect(Collectors.toList());
    }

    public List<IncidenteMiembro> buscarIncidentesAbiertos() {
        List<IncidenteMiembro> incidentes = buscarIncidentes();
        return incidentes.stream().filter(i->i.getResuelto().equals(false)).collect(Collectors.toList());
    }

    public List<IncidenteMiembro> buscarIncidentes(){
        EntityManager em = utils.BDUtils.getEntityManager();
        return em.createQuery("select i from IncidenteMiembro i", IncidenteMiembro.class).getResultList();
    }

    public List<Comunidad> buscarComunidades() {
        EntityManager em = utils.BDUtils.getEntityManager();
        List<Comunidad> comunidades = em.createQuery("select c from Comunidad c", Comunidad.class)
                .getResultList();;
        return comunidades;
    }

    public void persistirMiembros(List<Miembro> miembros) {
        EntityManager em = utils.BDUtils.getEntityManager();
        utils.BDUtils.comenzarTransaccion(em);

        for (Miembro miembro : miembros){
            em.persist(miembro);
        }

        utils.BDUtils.commit(em);
    }

    public void persistirComunidades(List<Comunidad> comunidades) {
        EntityManager em = utils.BDUtils.getEntityManager();
        utils.BDUtils.comenzarTransaccion(em);

        for (Comunidad comunidad : comunidades){
            em.persist(comunidad);
        }

        utils.BDUtils.commit(em);
    }

}
