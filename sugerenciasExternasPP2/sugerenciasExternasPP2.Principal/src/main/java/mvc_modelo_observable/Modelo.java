package mvc_modelo_observable;

import java.io.FileReader;  
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import bo.CustomersBo;
import bo.ProductosBo;

import com.mongodb.DBCollection;

import conexiones.Interfaz.RecolectorPromos;
import dao.mongoDB.MongoConcreteStub;

import java.util.Observable;

import modelo.Customer;
import modelo.Preferencias;
import modelo.Promocion;
import modelo.Recomendacion;
import properties.Constants;
import twitter4j.TwitterException;


public class Modelo extends Observable {
	private String valorString;
	private String email;
	private String usuario;
	private CustomersBo cBo; 
	private Recomendacion r, r2,r3,r4,r5,r6;
	// MongoConcrete m;
	MongoConcreteStub mongo;
	static String filtroChatarras = "";
	static String filtroPostres = "";
	static String filtroSanas = "";
	static String filtroPastas = "";
	ArrayList<Preferencias> listaPreferencias;
	HashMap<Recomendacion, Integer> mapRecomendacionesGeneral= new HashMap<Recomendacion, Integer>();
	String horaActualizacion= "";
	
	public Modelo(String valor, String nombreUsuario, String emailUsuario, String horaActualizacion) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException {
		this.valorString = valor;
		this.email = emailUsuario;
		this.usuario = nombreUsuario;
		this.horaActualizacion= horaActualizacion;
		listaPreferencias = new ArrayList<Preferencias>();
	}
	
	public HashMap<Recomendacion, Integer> cargarMapReco(){
		r= new Recomendacion(cBo.getListaCustomers().get(5));
		r2= new Recomendacion(cBo.getListaCustomers().get(4));
		r3= new Recomendacion(cBo.getListaCustomers().get(3));
		r4= new Recomendacion(cBo.getListaCustomers().get(2));
		r5= new Recomendacion(cBo.getListaCustomers().get(1));
		r6= new Recomendacion(cBo.getListaCustomers().get(0));		
		mapRecomendacionesGeneral.put(r, 5);
		mapRecomendacionesGeneral.put(r2, 4);
		mapRecomendacionesGeneral.put(r3, 3);
		mapRecomendacionesGeneral.put(r4, 2);
		mapRecomendacionesGeneral.put(r5, 1);
		mapRecomendacionesGeneral.put(r6, 0);
		
		return mapRecomendacionesGeneral;
	}

	// public void ConectarMongoDB() {
	// // Leo todos los Productos que tengo en ProductosBo
	// ProductosBo pBo = new ProductosBo();
	// pBo.getListaDeProductos();
	// pBo.mostrarListaDeProductos();
	// m = new MongoConcrete();
	// m.conectarseMongoDB();
	// System.out.println("Elimino la coleccion!");
	// m.eliminarTodaLaColeccion();
	// }
	public void ConectarMongoDBStub() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException {
		// Leo todos los Productos que tengo en ProductosBo
		ProductosBo pBo = new ProductosBo();
		pBo.getListaDeProductos();
		//pBo.mostrarListaDeProductos();
		mongo = new MongoConcreteStub();
		//mongo.conectarseMongoDB();
		cargarCustomers();
		cargarMapReco();
		cargarRecomendacionesGenerales(mapRecomendacionesGeneral);
		inicializar();
	}

	public void inicializar() {
		Properties prop1 = new Properties();
		try {
			prop1.load(new FileReader(Constants.ROUTE_COMIDAS_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Leo todos los Productos que tengo en ProductosBo
		ProductosBo pBo = new ProductosBo();
		pBo.getListaDeProductos();
	}

	public void filtroA() {
		r.buscarPreferenciasUsuarioConFiltro();
		r.getlRecomendaciones();
		String valor2String= setFiltroString(r);
		setModeloFiltros(valor2String, usuario, email);
	}
	
	public void filtroB() {
		r2.buscarPreferenciasUsuarioConFiltro();
		r2.getlRecomendaciones();
		String valor2String= setFiltroString(r2);
		setModeloFiltros(valor2String, usuario, email);
	}
	
	public String setFiltroString(Recomendacion reco){
		String filtroFinal="";
		
		for (Promocion prod : reco.getlRecomendaciones()) {
			filtroFinal= filtroFinal+ 
					"\n"+"local: "+prod.getNombreLocal()
					+" | ubicacion: "+prod.getUbicacion()
					+" | producto: "+prod.getProducto().getNombre()
					+" | precio: "+prod.getPrecio()
					+" | fechaDeVigencia: "+prod.getFechaVigencia().getDate();
		}
		return filtroFinal;
	}
	
	public void filtroChatarras() {
		r3.buscarPreferenciasUsuarioConFiltro();
		r3.getlRecomendaciones();
		filtroChatarras= setFiltroString(r3);
		setModeloFiltros(filtroChatarras, usuario, email);
	}

	public void filtroPostres() {
		r4.buscarPreferenciasUsuarioConFiltro();
		r4.getlRecomendaciones();
		filtroPostres= setFiltroString(r4);
		setModeloFiltros(filtroPostres, usuario, email);
	}

	public void filtroSanas() {
		r5.buscarPreferenciasUsuarioConFiltro();
		r5.getlRecomendaciones();
		filtroSanas= setFiltroString(r5);
		setModeloFiltros(filtroSanas, usuario, email);
	}

	public void filtroPastas() {
		r6.buscarPreferenciasUsuarioConFiltro();
		r6.getlRecomendaciones();
		filtroPastas= setFiltroString(r6);
		setModeloFiltros(filtroPastas, usuario, email);
	}

	public void setModeloFiltros(String valorString, String u, String e) {
		this.valorString = valorString;
		this.usuario = u;
		this.email = e;

		setChanged();
		notifyObservers();
	}

	public String getValorString() {
		return valorString;
	}

	public String getEmail() {
		return email;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getHoraActualizacion() {
		return horaActualizacion;
	}

	public void setHoraActualizacion(String horaActualizacion) {
		this.horaActualizacion = horaActualizacion;
	}

	public void setValorString(String valorString) {
		this.valorString = valorString;
	}

	public String getFiltroChatarras() {
		return filtroChatarras;
	}

	public String getFiltroPostres() {
		return filtroPostres;
	}

	public String getFiltroSanas() {
		return filtroSanas;
	}

	public String getFiltroPastas() {
		return filtroPastas;
	}

	public void cargarCustomers() {
		cBo = new CustomersBo();
		cBo.getListaDeCustomers();
	}
	
	
	
	public HashMap<Recomendacion, Integer> getMapRecomendaciones() {
		return mapRecomendacionesGeneral;
	}

	public void cargarRecomendacionesGenerales(HashMap<Recomendacion, Integer> map) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException{
			
		for (Map.Entry<Recomendacion, Integer> entry : mapRecomendacionesGeneral.entrySet()) {
			cargarUnaRecomendacion(entry.getKey(), cBo.getListaCustomers().get(entry.getValue()));
		}	
	}
	
	public DBCollection cargarUnaRecomendacion(Recomendacion reco, Customer user) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException{
		RecolectorPromos c = new RecolectorPromos();//sin argumento con mongoStub
		
		c.cargarListaConectores();
		c.buscarPromociones();
		c.getMongoDB().leerColeccion();
		reco.leerPreferencias();
		DBCollection coll=reco.mostrarRecomendaciones(c.getMongoDB().getPromos());
		return coll;
	}

	public MongoConcreteStub getMongo() {
		return mongo;
	}

	public void setMongo(MongoConcreteStub mongo) {
		this.mongo = mongo;
	}
	
	public DBCollection cargarTodasLasPromos() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException{
		RecolectorPromos c = new RecolectorPromos();//sin argumento con mongoStub

		c.cargarListaConectores();
		c.buscarPromociones();
		DBCollection promos= c.getMongoDB().leerColeccion();
		return promos;
	}
}