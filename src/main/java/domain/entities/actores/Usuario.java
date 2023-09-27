package domain.entities.actores;

import javax.persistence.*;

@Entity
@Table
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuario_codigo;

    @Column
    private String nombreUsuario;
    @Column
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private Rol rol;
}
