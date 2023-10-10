package domain;

import com.google.firebase.database.core.Repo;
import domain.calculadorGradosConfianza.CalculadorGradosDeConfianza;
import domain.entities.actores.miembros.Miembro;
import domain.entities.incidentes.Incidente;
import domain.entities.incidentes.IncidenteMiembro;
import domain.repositorios.RepoGeneral;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import java.util.List;

public class CalculadorTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    private CalculadorGradosDeConfianza calculadorGradosDeConfianza = new CalculadorGradosDeConfianza();
    private EntityManager em = utils.BDUtils.getEntityManager();

    @Before
    public void ejecutarCalculadora(){
        calculadorGradosDeConfianza.execute();
    }
    /*
    @Test
    public void TestMiembro1(){

        List<Miembro> miembros = em.createQuery("select m from Miembro m where m.apellido = ?1", Miembro.class)
                .setParameter(1,"Perez").getResultList();

        Assert.assertEquals(5.5,miembros.stream().findFirst().get().getGradoConfianza().getPuntaje(),0.1);
    }*/

    @Test
    public void TestMiembro2(){

        List<Miembro> miembros = em.createQuery("select m from Miembro m where m.apellido = ?1", Miembro.class)
                .setParameter(1,"Martinez").getResultList();

        Assert.assertEquals(4.8,miembros.stream().findFirst().get().getGradoConfianza().getPuntaje(),0.1);
    }

}

