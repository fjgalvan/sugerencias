package mvc_observer_controlador_vista;
//
//import mvc_modelo_observable.Modelo;
//
//
//
///**
// * Controlador del MVC. Se encuentra dentro de la vista y es llamado por esta para controlar a la lógica.
// * El controlador contiene un modelo, el cual es la logica de nuestro programa, y se encarga de llamar a este modelo para que realice las acciones necesarias sobre el programa.
// * @author fon
// */
//public class Controlador {
//    Modelo m; //Nuestra lógica del programa
//    Vista v;
//    
//    /**
//     * Constructora del controlador. Creará un controlador, y se le asignará el modelo correspondiente.
//     * @param m 
//     */
//    public Controlador(Modelo m, Vista v){
//        //Asignamos un modelo a nuestro controlador.
//        this.m = m;
//        this.v= v;
//        
//        m.addObserver(v);
//    }
//    
//    /**
//     * Función sumar. Notifica al modelo que quiere incrementar el valor.
//     */
//    public void filtroUsuarioA(){
//        m.filtroA();
//    }
//    
//    /**
//     * Funcion restar. Notifica al modeo que quiere restar al valor.
//     */
//    public void filtroUsuarioB(){
//        m.filtroB();
//    }
//   
//}
