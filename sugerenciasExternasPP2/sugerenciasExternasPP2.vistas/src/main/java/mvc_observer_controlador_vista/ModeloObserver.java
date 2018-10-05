package mvc_observer_controlador_vista;

import com.mongodb.DBObject;

/**
 * Interfaz Observador. Contendrá los metodos de tipo ModeloObserver para que el observador se actualice según lo
 * que reciba del modelo.
 */
public interface ModeloObserver {
    
    /**
     * Metodo valorCambiado. Unico metodo de la interfaz pues solo nos interesa como observadores el valor "valor"
     *  para modificar nuestra vista.
     * 
     * @param valor2 : Notificación del nuevo valor que nos llega del modelo. El modelo notificará con este metodo 
     * a cada observador, el nuevo valor de la logica.
     */
    public void valorCambiado(DBObject valor2, String preferencia, String usuario);

}
