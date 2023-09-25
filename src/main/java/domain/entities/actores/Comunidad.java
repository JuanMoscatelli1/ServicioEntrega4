package domain.entities.actores;

import domain.entities.actores.miembros.MiembroPorComunidad;
import domain.entities.incidentes.Incidente;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Comunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comunidad_codigo;

    @OneToMany(mappedBy = "comunidad", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @Getter
    private List<MiembroPorComunidad> miembros;
    @Column
    private String objetivo;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "comunidad_codigo", referencedColumnName = "comunidad_codigo")
    private List<Incidente> incidentes;


}
