package domain.entities.actores.miembros;


import domain.calculadorGradosConfianza.gradosConfianza.GradoConfianza;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Miembros")
@Getter
public class Miembro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int miembro_codigo;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String email;

    @Column
    private String telefono;

    @OneToMany(mappedBy = "miembro", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<MiembroPorComunidad> comunidades;

    @Setter
    @Transient
    private GradoConfianza gradoConfianza;


    public Miembro(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public Miembro() {

    }

    public void actualizarPuntaje(double puntaje){
        gradoConfianza.actualizar(this,puntaje);
    }

}
