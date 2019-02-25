package notificaciones;

public interface InterfazNotificacion {
	
	public void conectarConServicioDeNotificacion();
	
	public void enviarNotificacion(String destino, String notificacion);
}
