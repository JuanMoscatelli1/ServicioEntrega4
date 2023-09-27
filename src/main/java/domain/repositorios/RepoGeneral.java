package domain.repositorios;

import domain.entities.actores.miembros.Miembro;
import domain.entities.incidentes.IncidenteMiembro;
import domain.entities.servicios.Organizacion;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepoGeneral {
    private List<Miembro> miembros;

    private static RepoGeneral instance;

    private RepoGeneral() {
        this.miembros = new ArrayList<>();
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

    /*public Organizacion buscar(String nombre){
        Optional<Organizacion> organizacionEncontrada = organizaciones.stream()
                .filter(org -> org.getNombre().equals(nombre)).findFirst();
        if (organizacionEncontrada.isPresent()){
            return organizacionEncontrada.get();
        }else return null;
    }*/

    //public void agregar(Organizacion org){this.organizaciones.add(org);}
}
