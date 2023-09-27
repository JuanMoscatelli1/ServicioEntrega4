package domain.entities.notificaciones;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class NotificadorIncidentes {
    private List<Notificacion> notifcacionesPendientes;

    public void agregarNotificaciones(List<Notificacion> notis){
        this.notifcacionesPendientes.addAll(notis);
    }

    public void limpiar(){
        notifcacionesPendientes= this.notifcacionesPendientes.stream().filter(n-> !n.estaVacia()).collect(Collectors.toList());
    }

    public void notificar(){
        this.notifcacionesPendientes.forEach(n -> n.notificar(LocalDateTime.now()));
        limpiar();
    }
}
