package domain.calculadorGradosConfianza.gradosConfianza;

import domain.entities.actores.miembros.Miembro;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class GradoConfianza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradoConfianza_codigo;

    private double puntaje;

    public GradoConfianza(Double puntajeNuevo){
        this.puntaje = puntajeNuevo;
    }

    public GradoConfianza() {

    }

    public void actualizar(Miembro miembro, double puntajeNuevo){
        puntaje += puntajeNuevo;
        verificar(miembro, puntaje);
    }

    public abstract void verificar(Miembro miembro, double puntaje);


}
