package mvc_modelo_observable;

import java.io.FileReader; 
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
	private Recomendacion r;
	private Recomendacion r2;
	private Recomendacion r3;
	private Recomendacion r4;
	private Recomendacion r5;
	private Recomendacion r6;
	//String s = "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0,ensalada/20.0,fideos/30.0)_20-12-2018";
	// MongoConcrete m;
	MongoConcreteStub mongo;
	String filtroEspecial = "";
	static String filtroChatarras = "";
	static String filtroPostres = "";
	static String filtroSanas = "";
	static String filtroPastas = "";
	String preferenciasChatarras = "";
	String preferenciasPostres = "";
	String preferenciasSanas = "";
	String preferenciasPastas = "";
	ArrayList<Preferencias> listaPreferencias;
	

	/**
	 * Constructora del modelo. Crea un modelo, inicializa variables. Crea la
	 * lista de los observadores.
	 * @throws TwitterException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 */
	public Modelo(String valor, String nombreUsuario, String emailUsuario) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException {
		this.valorString = valor;
		this.email = emailUsuario;
		this.usuario = nombreUsuario;
		listaPreferencias = new ArrayList<Preferencias>();
		ConectarMongoDBStub();
		cargarCustomers();
		cargarRecomendaciones();
		inicializar();
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
	public void ConectarMongoDBStub() {
		// Leo todos los Productos que tengo en ProductosBo
		ProductosBo pBo = new ProductosBo();
		pBo.getListaDeProductos();
		//pBo.mostrarListaDeProductos();
		mongo = new MongoConcreteStub();
		mongo.conectarseMongoDB();
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
		//pBo.mostrarListaDeProductos();

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
					+"| ubicacion: "+prod.getUbicacion()
					+"| producto: "+prod.getProducto().getDescripcion()
					+"| precio: "+prod.getPrecio()
					+"| fechaDeVigencia: "+prod.getFechaVigencia().getDate();
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

	public String getFiltroEspecial() {
		return filtroEspecial;
	}

	public void setFiltroEspecial(String filtroEspecial) {
		this.filtroEspecial = filtroEspecial;
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
		//cBo.mostrarListaDeCustomers();
	}

	public void cargarRecomendaciones() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException {//Ahora cargo las recomendaciones para todos los cBo

			r= new Recomendacion(cBo.getListaCustomers().get(5));
			cargarUnaRecomendacion(r, cBo.getListaCustomers().get(5));
			
			r2= new Recomendacion(cBo.getListaCustomers().get(4));
			cargarUnaRecomendacion(r2, cBo.getListaCustomers().get(4));
			
			r3= new Recomendacion(cBo.getListaCustomers().get(3));
			cargarUnaRecomendacion(r3, cBo.getListaCustomers().get(3));
			
			r4= new Recomendacion(cBo.getListaCustomers().get(2));
			cargarUnaRecomendacion(r4, cBo.getListaCustomers().get(2));
			
			r5= new Recomendacion(cBo.getListaCustomers().get(1));
			cargarUnaRecomendacion(r5, cBo.getListaCustomers().get(1));
			
			r6= new Recomendacion(cBo.getListaCustomers().get(0));
			cargarUnaRecomendacion(r6, cBo.getListaCustomers().get(0));
			
	}
	
	public void cargarUnaRecomendacion(Recomendacion reco, Customer user) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException{
		RecolectorPromos c = new RecolectorPromos();

		c.cargarListaConectores();
		c.buscarPromociones();
		//System.out.println("c.getMongoDB().getPromos().count(): "+c.getMongoDB().getPromos().count());
		c.getMongoDB().leerColeccion();
		//--------------
		//System.out.println("\ncustomer recomendacion: "+ user.getUserName().getUsuario());
		reco.leerPreferencias();
		//reco.mostrarListProdDeTwitter(promoExtra,c.getMongoDB().getPromos());//(s, m.getPromos());
		reco.mostrarRecomendaciones(c.getMongoDB().getPromos());
	}

	public MongoConcreteStub getMongo() {
		return mongo;
	}

	public void setMongo(MongoConcreteStub mongo) {
		this.mongo = mongo;
	}
	
	public DBCollection cargarTodasLasPromos() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException{
		RecolectorPromos c = new RecolectorPromos();

		c.cargarListaConectores();
		c.buscarPromociones();
		//System.out.println("c.getMongoDB().getPromos().count(): "+c.getMongoDB().getPromos().count());
		DBCollection promos= c.getMongoDB().leerColeccion();
		c.finishMongo();//cierro la base,
		return promos;
	}
}