package domain.entities.respuestas;
import domain.entities.actores.Comunidad;
import domain.entities.actores.miembros.MiembroPorComunidad;
import java.util.ArrayList;
import java.util.List;

public class ComunidadRespuesta {

    private Integer comunidad_codigo;
    //private Double puntaje;
    private List<Integer> miembros;

    public ComunidadRespuesta(Comunidad comunidad){
        this.miembros=new ArrayList<>();
        this.comunidad_codigo=comunidad.getComunidad_codigo();
        //this.puntaje=comunidad.getPuntaje();
        this.obtenerIds(comunidad);
    }
    private void obtenerIds(Comunidad comunidad){
        List<MiembroPorComunidad> miembros= comunidad.getMiembros();
        for (MiembroPorComunidad miembroComunidad : miembros) {
            this.miembros.add(miembroComunidad.getMiembro().getMiembro_codigo());
        }

    }
    public int getComunidad_codigo() {
        return comunidad_codigo;
    }
    /*public double getPuntaje() {
        return puntaje;
    }*/
    public List<Integer> getMiembros() {
        return miembros;
    }
}
