package mvc_modelo_observable;


import mvc_modelo_observable.Modelo;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Controlador del MVC. Se encuentra dentro de la vista y es llamado por esta para controlar a la lógica.
 * El controlador contiene un modelo, el cual es la logica de nuestro programa, y se encarga de llamar a este modelo para que realice las acciones necesarias sobre el programa.
 * @author fon
 */
public class Controlador {
    Modelo m; //Nuestra lógica del programa
    Vista v;
    
    /**
     * Constructora del controlador. Creará un controlador, y se le asignará el modelo correspondiente.
     * @param m 
     */
    public Controlador(Modelo m, Vista v){
        //Asignamos un modelo a nuestro controlador.
        this.m = m;
        this.v= v;
        
        m.addObserver(v);
        v.addFiltroListeners(new FiltroListener());
    }
    
    /**
     * Función sumar. Notifica al modelo que quiere incrementar el valor.
     */
    public void filtroUsuarioA(){
        m.filtroA();
    }
    
    /**
     * Funcion restar. Notifica al modeo que quiere restar al valor.
     */
    public void filtroUsuarioB(){
        m.filtroB();
    }
    
    class FiltroListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			 v.getBotonFiltroB().addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		            	m.filtroB();
		            }
		        });
			 
			 v.getBotonFiltroA().addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		            	m.filtroA();
		            }
		        });
		}
    	
    }

   
}
