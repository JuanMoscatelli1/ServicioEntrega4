package domain.entities.actores;

import domain.calculadorGradosConfianza.gradosConfianza.ConReservas;
import domain.calculadorGradosConfianza.gradosConfianza.GradoConfianza;
import domain.entities.actores.miembros.MiembroPorComunidad;
import domain.entities.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
@Getter
@Setter
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

    @Column
    private Double puntaje;

    public void totalPuntaje(){
        List<Double> puntajes = miembros.stream().map(MiembroPorComunidad::obtenerPuntaje).collect(Collectors.toList());
        Double sum = 0.0;
        for (Double num : puntajes) {
            sum += num;
        }

        double average = sum / puntajes.size();

        this.setPuntaje( average - (miembros.stream().filter(m -> m.esGrado(new ConReservas())).count() *0.2));
    }

}
