package domain.entities.actores.miembros;

import javax.persistence.*;

@Entity
@Table
public class TipoDeMiembro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tipoDeMiembro_codigo;
    @Column
    private String tipoDeMiembro;
}
