package mvc_modelo_observable;

import java.io.FileReader;      
import java.io.IOException;
import java.util.Properties;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import dao.Interfaz.InterfaceMongoAccess;
import dao.filtrosDeUsuario.FiltrosDeUsuarioAyB;
import dao.mongoDB.MongoConcrete;

import java.util.Observable;

import promo.Twitter.PromoTwitter;
import properties.Constants;


/**
 * Modelo de mi programa, aquí estará toda la lógica y el funcionamiento interno de este.
 * Lo hacemos Observable sobre los observadores del modelo, para que sean notificados con lo que les interese.
 * @author fon
 */
public class Modelo extends Observable{
    private String valorString;//PARECE QUE SOLO ACEPTA PRIMITIVOS Y NO DBObject!!
    private String preferencias;
    private String usuario;
    FiltrosDeUsuarioAyB f;
    String s= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0,ensalada/20.0,fideos/30.0)_20-11-2018";
    Properties p1;
	Properties p2;
	MongoConcrete m;
    /**
     * Constructora del modelo. Crea un modelo, inicializa variables. Crea la lista de los observadores.
     */
    public Modelo(String valor, String preferencias, String usuario) {//(DBObject valor, String preferencias, String usuario) {
    	this.valorString= valor;
    	this.preferencias= preferencias;
    	this.usuario= usuario;  
    	inicializar();
    }
    
    public void ConectarMongoDB(){
    	m= new MongoConcrete();
    	m.conectarseMongoDB();
    }
    public void inicializar(){
    	//Inicializamos atributos...
    	f= new FiltrosDeUsuarioAyB();
    	//IMPRIMO BSON
    	System.out.println("inicializar Modelo!");
    	System.out.println("promos count:"+ m.getPromos().count());
		DBCursor cursorDoc5 = m.getPromos().find();
		while (cursorDoc5.hasNext()) {
			System.out.println(cursorDoc5.next());
		}
		System.out.println("FIN inicializar Modelo!");
    	f.mostrarListProdDeTwitter(s,m.getPromos());
    	p1 = new Properties();
		try { p1.load(new FileReader(Constants.ROUTE_PREFERENCIAS_USUARIO_A));
		} catch (IOException e) { e.printStackTrace();}
		
		p2 = new Properties();
		try { p2.load(new FileReader(Constants.ROUTE_PREFERENCIAS_USUARIO_B));
		} catch (IOException e) { e.printStackTrace();}
        //valor = null;
        //observadores = new ArrayList<ModeloObserver>();
    }
  
    public void filtroA(){
    	f.buscarPreferenciasUsuarioConFiltro(p1, "chatarras", "postres", "hamburguesa", "helado");
    	DBObject valor2=f.getFa();
    	String valor2String= valor2.toString();
        String preferencias2= "comidas chatarras y postres";
        String usuario2= "A";
        
		setModeloFiltros(valor2String,preferencias2,usuario2);
        System.out.println("valor2String: "+valor2String);
        System.out.println("preferencias2: "+preferencias2);
        System.out.println("usuario2: "+usuario2);
    }

    public void filtroB(){
    	f.buscarPreferenciasUsuarioConFiltro(p2, "sanas", "postres", "ensalada", "helado");
    	DBObject valor2=f.getFb();
    	String valor2String= valor2.toString();
        String preferencias2= "comidas sanas y postres";
        String usuario2= "B";

        setModeloFiltros(valor2String,preferencias2,usuario2);
        System.out.println("valor2String: "+valor2String);
        System.out.println("preferencias2: "+preferencias2);
        System.out.println("usuario2: "+usuario2);
    }
    

    
	public void setModeloFiltros(String valorString,String p,  String u) {
		this.valorString=valorString;
		this.preferencias=p;
		this.usuario= u;
		
        setChanged();
        notifyObservers();
    }
	
	public String getValorString() {
		return valorString;
	}
	public String getPreferencias() {
		return preferencias;
	}
	public String getUsuario() {
		return usuario;
	}
}