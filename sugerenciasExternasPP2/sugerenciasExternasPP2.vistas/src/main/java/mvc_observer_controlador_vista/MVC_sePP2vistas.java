package mvc_observer_controlador_vista;
//
//import mvc_modelo_observable.Modelo;
//
//
//
//
///**
// * Ejemplo patrones de diseño MVC y Observer
// * Realizado por Alfonso Soria Muñoz
// * Para el canal de experimentando y aprendiendo.
// */
///**
// * Clase Main, desde aqui cargaremos el modelo, la vista y el controlador, y añadiremos observadores sobre el modelo.
// * @author fon
// */
//public class MVC_sePP2vistas {
//
//    @SuppressWarnings("unused")
//	public static void main(String args[]){
//        //Cargamos modelo
//        Modelo m = new Modelo("recomendaciones","preferencias", "usuarios");
//      
//        //Cargamos vista
//        Vista v = new Vista(m); 
//        
//        //Cargamos controlador y le asignamos qué modelo controlar
//        Controlador c = new Controlador(m, v);
//        
//        System.out.println("m.getValor(): "+m.getValorString());
//        System.out.println("m.getUsuario(): "+m.getUsuario());
//        System.out.println("m.getPreferencias(): "+m.getPreferencias());
//    }
//}