package domain.entities.actores.miembros;

import domain.calculadorGradosConfianza.gradosConfianza.GradoConfianza;
import domain.entities.actores.Comunidad;
import domain.entities.actores.miembros.Miembro;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table
@Getter
public class MiembroPorComunidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int miembroPorComunidad_codigo;

    public int getMiembroPorComunidad_codigo() {
        return miembroPorComunidad_codigo;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public TipoDeMiembro getTipoDeMiembro() {
        return tipoDeMiembro;
    }

    public Boolean getEsAdmin() {
        return esAdmin;
    }

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "miembro_codigo", referencedColumnName = "miembro_codigo")
    @Getter
    private Miembro miembro;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "comunidad_codigo", referencedColumnName = "comunidad_codigo")
    @Getter
    private Comunidad comunidad;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoDeMiembro_codigo", referencedColumnName = "tipoDeMiembro_codigo")
    private TipoDeMiembro tipoDeMiembro;

    @Column
    private Boolean esAdmin;

    public MiembroPorComunidad(Miembro miembro,Comunidad comunidad) {
        this.miembro = miembro;
        this.comunidad = comunidad;
    }

    public MiembroPorComunidad() {

    }
     public Double obtenerPuntaje(){
        return this.getMiembro().getGradoConfianza().getPuntaje();
     }

     public Boolean esGrado(GradoConfianza grado){
        return this.getMiembro().getGradoConfianza().getClass().equals(grado.getClass());
     }


}
