package domain;

import com.google.firebase.database.core.Repo;
import domain.entities.actores.miembros.Miembro;
import domain.entities.incidentes.Incidente;
import domain.entities.incidentes.IncidenteMiembro;
import domain.repositorios.RepoGeneral;
import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.util.List;

public class calculadorTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Test
    public void obtenerMiembros(){
        List<Miembro> miembros = RepoGeneral.getInstance().buscarMiembros();
        Miembro miembro1 = miembros.stream().findFirst().get();
        Assert.assertEquals(1,miembro1.getMiembro_codigo());
        Assert.assertEquals(1,miembro1.getComunidades().size());
    }

    @Test
    public void obtenerIncidentes(){
        List<IncidenteMiembro> incidentes = RepoGeneral.getInstance().buscarIncidentesAbiertos();
        IncidenteMiembro incidenteMiembro1 = incidentes.stream().findFirst().get();
        Assert.assertEquals(1,incidenteMiembro1.getIncidente_codigo());
    }
}

//TODO: Terminar tests acá y en el otro proyecto, para llenar la base de datos