package domain;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import utils.BDUtils;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertNotNull;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
    @Test
    public void contextUp() {
        assertNotNull(entityManager());
    }

    @Test
    public void contextUpWithTransaction() {
        withTransaction(() -> {});
    }

    /*

    @Test
    public void init(){
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        Corredor corredor1 = new Corredor();
        corredor1.setNombre("Jorge");

        em.persist(corredor1);

        BDUtils.commit(em);
    }
    */
}

