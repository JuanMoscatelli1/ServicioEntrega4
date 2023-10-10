package domain.entities.actores.miembros;


import com.fasterxml.jackson.annotation.JsonIgnore;
import domain.calculadorGradosConfianza.gradosConfianza.ConfiableNivel1;
import domain.calculadorGradosConfianza.gradosConfianza.GradoConfianza;
import domain.entities.actores.Usuario;
import domain.entities.notificaciones.HorarioNotificacion;
import domain.entities.notificaciones.MedioNotificacion;
import domain.entities.notificaciones.Notificacion;
import domain.entities.services.georef.entities.Localizacion;
import domain.entities.servicios.Entidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Miembros")
@Getter
@Setter
public class Miembro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int miembro_codigo;

    @Column
    private String nombre;

    @Column
    private String apellido;

    public int getMiembro_codigo() {
        return miembro_codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<MiembroPorComunidad> getComunidades() {
        return comunidades;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Entidad> getEntidadesDeInteres() {
        return entidadesDeInteres;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public MedioNotificacion getMedioNotificacion() {
        return medioNotificacion;
    }

    public List<HorarioNotificacion> getHorarios() {
        return horarios;
    }

    public GradoConfianza getGradoConfianza() {
        return gradoConfianza;
    }

    @Column
    private String email;

    @Column
    private String telefono;

    @JsonIgnore
    @OneToMany(mappedBy = "miembro", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<MiembroPorComunidad> comunidades;

    @OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_codigo", referencedColumnName = "usuario_codigo")
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<Entidad> entidadesDeInteres;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "localizacion_codigo", referencedColumnName = "localizacion_codigo")
    private Localizacion localizacion;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "medio_notificacion_codigo", referencedColumnName = "medio_notificacion_codigo")
    private MedioNotificacion medioNotificacion;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<HorarioNotificacion> horarios;


    public void setGradoConfianza(GradoConfianza gradoConfianza) {
        this.gradoConfianza = gradoConfianza;
    }

    @OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "gradoConfianza_codigo", referencedColumnName = "gradoConfianza_codigo")
    private GradoConfianza gradoConfianza;


    public Miembro(int id, List<MiembroPorComunidad> comunidades, GradoConfianza gradoConfianza) {
        this.miembro_codigo = id;
        this.comunidades = comunidades;
        this.gradoConfianza=gradoConfianza;
    }

    public Miembro(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.comunidades = new ArrayList<>();
        this.gradoConfianza = new ConfiableNivel1(5.0);
    }

    public Miembro() {

    }

    public void actualizarPuntaje(double puntaje){
        gradoConfianza.actualizar(this,puntaje);
    }

    public boolean tieneRangoHorario(LocalDateTime hora) {
        return this.horarios.stream().anyMatch(r -> r.getHorario().equals(hora));
    }

    public void notificar(Notificacion notificacion){
        this.getMedioNotificacion().notificar(notificacion,this);
    }

    public void agregarMiembroPorComunidad(MiembroPorComunidad miembroPorComunidad1) {
        comunidades.add(miembroPorComunidad1);
    }
}
