package domain;

import domain.calculadorGradosConfianza.CalculadorGradosDeConfianza;
import domain.entities.actores.Comunidad;
import domain.entities.actores.miembros.Miembro;
import domain.entities.actores.miembros.MiembroPorComunidad;
import domain.entities.incidentes.IncidenteMiembro;
import domain.entities.servicios.Establecimiento;
import domain.entities.servicios.ServicioBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import utils.BDUtils;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    private CalculadorGradosDeConfianza calculadorGradosDeConfianza = new CalculadorGradosDeConfianza();
    private EntityManager em = BDUtils.getEntityManager();
    @Test
    public void contextUp() {
        assertNotNull(entityManager());
    }

    @Test
    public void contextUpWithTransaction() {
        withTransaction(() -> {});
    }

    @Test
    public void init(){

        BDUtils.comenzarTransaccion(em);

        //miembro1 (deberia sumar 0,5)

        Miembro miembro1 = new Miembro("Jorge","Perez","hola@gmail.com","123");
        Comunidad comunidad1 = new Comunidad();
        em.persist(comunidad1);
        MiembroPorComunidad miembroPorComunidad1 = new MiembroPorComunidad(miembro1, comunidad1);
        em.persist(miembroPorComunidad1);
        //miembro1.setComunidades(Collections.singletonList(miembroPorComunidad1));
        miembro1.agregarMiembroPorComunidad(miembroPorComunidad1);
        em.persist(miembro1);
        ServicioBase servicioBase1 = new ServicioBase();
        em.persist(servicioBase1);
        Establecimiento establecimiento1 = new Establecimiento();
        em.persist(establecimiento1);
        IncidenteMiembro incidenteMiembro1 =
                new IncidenteMiembro("incidente 1", servicioBase1, LocalDateTime.now(),establecimiento1,miembroPorComunidad1);
        em.persist(incidenteMiembro1);

        //miembro2 (deberia restar 0,2) (apertura fraudulenta)

        Miembro miembro2 = new Miembro("Tomas","Martinez","hola2@gmail.com","456");
        Comunidad comunidad2 = new Comunidad();
        em.persist(comunidad2);
        MiembroPorComunidad miembroPorComunidad2 = new MiembroPorComunidad(miembro2, comunidad2);
        em.persist(miembroPorComunidad2);
        miembro2.agregarMiembroPorComunidad(miembroPorComunidad2);
        em.persist(miembro2);
        ServicioBase servicioBase2 = new ServicioBase();
        em.persist(servicioBase2);
        Establecimiento establecimiento2 = new Establecimiento();
        em.persist(establecimiento2);
        IncidenteMiembro incidenteMiembro2 =
                new IncidenteMiembro("incidente 2", servicioBase2, LocalDateTime.now(),establecimiento2,miembroPorComunidad2);
        incidenteMiembro2.setFechaCierre(LocalDateTime.now().plusMinutes(1));
        em.persist(incidenteMiembro2);

        /*
        List<Miembro> miembros = em.createQuery("select m from Miembro m where m.apellido = ?1", Miembro.class)
                .setParameter(1,"Perez").getResultList();

        Assert.assertEquals(miembros.stream().findFirst().get().getApellido(),"Perez");
        */

        BDUtils.commit(em);
    }


}

