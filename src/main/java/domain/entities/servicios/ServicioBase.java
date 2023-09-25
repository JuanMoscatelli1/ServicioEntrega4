package domain.entities.servicios;


import javax.persistence.*;

@Entity

public class ServicioBase extends Servicio{


    @Column
    private Boolean estaHabilitado;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoDeServicio_codigo", referencedColumnName = "tipoDeServicio_codigo")
    private TipoDeServicio tipoDeServicio;

    public ServicioBase(Establecimiento establecimiento,Boolean x, TipoDeServicio tipoDeServicio){
        this.establecimiento = establecimiento;
        this.estaHabilitado= x;
        this.tipoDeServicio =tipoDeServicio;
    }

    public ServicioBase() {

    }
}
