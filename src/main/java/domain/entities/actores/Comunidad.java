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
    public int getComunidad_codigo() {
        return comunidad_codigo;
    }


    public List<MiembroPorComunidad> getMiembros() {
        return miembros;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public List<Incidente> getIncidentes() {
        return incidentes;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    @OneToMany(mappedBy = "comunidad", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @Getter
    private List<MiembroPorComunidad> miembros;
    @Column
    private String objetivo;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "comunidad_codigo", referencedColumnName = "comunidad_codigo")
    private List<Incidente> incidentes;

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

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
    public Comunidad(Integer id){
        this.comunidad_codigo=id;
    }
    public Comunidad(){}

}
