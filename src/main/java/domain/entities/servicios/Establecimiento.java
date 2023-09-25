package domain.entities.servicios;


import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table
public class Establecimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int establecimiento_codigo;
    @Column
    private String nombre;
    @OneToMany(mappedBy = "establecimiento", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<Servicio> servicios;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoDeEstablecimiento_codigo", referencedColumnName = "tipoDeEstablecimiento_codigo")
    private TipoDeEstablecimiento tipoDeEstablecimiento;


    @Getter
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "entidad_codigo", referencedColumnName = "entidad_codigo")
    private Entidad entidad;

    public Establecimiento(String nombre, TipoDeEstablecimiento tipoDeEstablecimiento, Entidad entidad){
        this.servicios = new ArrayList<>();
        this.nombre = nombre;
        this.tipoDeEstablecimiento= tipoDeEstablecimiento;
        this.entidad = entidad;
    }

    public Establecimiento() {

    }

    public void agregarServicio(Servicio servicio){
        this.servicios.add(servicio);
    }
}
