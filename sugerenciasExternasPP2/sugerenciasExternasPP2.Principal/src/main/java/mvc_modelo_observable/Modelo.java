package mvc_modelo_observable;

import java.io.FileReader;    
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;





import properties.Constants;

import com.mongodb.DBObject;

import dao.FiltrosDeUsuarioAyB;




/**
 * Modelo de mi programa, aquí estará toda la lógica y el funcionamiento interno de este.
 * Lo hacemos Observable sobre los observadores del modelo, para que sean notificados con lo que les interese.
 * @author fon
 */
public class Modelo implements Observable<ModeloObserver>{

    //Aquí añadiremos la liste de nuestros observadores
    private ArrayList<ModeloObserver> observadores;
    //Valor de filtros
    private DBObject valor;
    private String preferencias= "";
    private String usuario= "";
    FiltrosDeUsuarioAyB f;
    String s= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0,ensalada/20.0,fideos/30.0)_20-11-2018";
    Properties p1;
	Properties p2;
    /**
     * Constructora del modelo. Crea un modelo, inicializa variables. Crea la lista de los observadores.
     */
    public Modelo() {
        //Inicializamos atributos...
    	f= new FiltrosDeUsuarioAyB();
    	f.mostrarListProdDeTwitter(s);
    	p1 = new Properties();
		try { p1.load(new FileReader(Constants.ROUTE_PREFERENCIAS_USUARIO_A));
		} catch (IOException e) { e.printStackTrace();}
		
		p2 = new Properties();
		try { p2.load(new FileReader(Constants.ROUTE_PREFERENCIAS_USUARIO_B));
		} catch (IOException e) { e.printStackTrace();}
        valor = null;
        observadores = new ArrayList<ModeloObserver>();
        
    }
  
    public void filtroA(){
    	f.buscarPreferenciasUsuarioConFiltro(p1, "chatarras", "postres", "hamburguesa", "helado");
        valor=f.getFa();
        preferencias= "comidas chatarras y postres";
        usuario= "A";
        notificarObservadores(valor, preferencias, usuario);
    }

    public void filtroB(){
    	f.buscarPreferenciasUsuarioConFiltro(p2, "sanas", "postres", "ensalada", "helado");
        valor=f.getFb();
        preferencias= "comidas sanas y postres";
        usuario= "B";
        notificarObservadores(valor, preferencias, usuario);
    }
    
    
    
    
    
    
    /**
     * addObservador: Añade observadores a nuestro modelo
     * @param t : observador de tipo ModeloObserver
     */
    //@Override
    public void addObservador(ModeloObserver t) {
        //Añadimos el observador a nuestro arraylist
        observadores.add(t);
        //Notificamos el valor a nuestros observadores ya que tenemos un nuevo observador que necesita saber el valor.
        notificarObservadores(valor, preferencias, usuario);
    }

    /**
     * removeObservador: Borra observadores a nuestro modelo
     * @param t : observador que queramos borrar.
     */
    //**En realidad no vamos a emplear en nuestro ejemplo este metodo, pero es importante tenerlo en cuenta.
    //@Override
    public void removeObservador(ModeloObserver t) {
        observadores.remove(t);
    }
    
    /**
     * Método que notifica a nuestros observadores los cambios que nos interese que sepan.
     * @param valor2 : estado del valor del numero.
     */
    private void notificarObservadores(DBObject valor2, String preferencia, String usuario) {
        //Nos recorremos el arraylist de los observadores
        for(ModeloObserver o : observadores){
            //Le a cada observador que el valor se ha cambiado al nuevo valor "t".
            //Recuerdo que para este caso, estamos notificando a cada vista que tengamos, el nuevo valor que tiene el numero, para que estas modifiquen su JLabel como output de la logica.
            o.valorCambiado(valor2, preferencia, usuario);
        }
    }
    
    
}