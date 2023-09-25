package domain.entities.servicios;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class ServicioPublico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int servicioPublico_codigo;

    @Enumerated(EnumType.STRING)
    @Column
    private TipoDeTransporte tipoDeTransporte;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "servicioPublico_codigo", referencedColumnName = "servicioPublico_codigo")
    private List<Linea> lineas;

    public ServicioPublico(TipoDeTransporte tipoDeTransporte) {
        this.tipoDeTransporte = tipoDeTransporte;
        this.lineas=new ArrayList<>();
    }

    public ServicioPublico() {

    }
}
