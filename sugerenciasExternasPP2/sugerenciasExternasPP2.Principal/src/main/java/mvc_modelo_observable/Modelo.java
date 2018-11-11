package mvc_modelo_observable;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import bo.CustomersBo;
import bo.ProductosBo;

import com.mongodb.DBObject;

import dao.mongoDB.MongoConcrete;
import dao.mongoDB.MongoConcreteStub;

import java.util.Observable;

import modelo.Customer;
import modelo.Preferencias;
import modelo.Recomendacion;
import modelo.Usuario;
import properties.Constants;


public class Modelo extends Observable {
	private String valorString;
	private String email;
	private String usuario;
	private Customer c1;
	private Customer c2;
	private Customer c3;
	private Customer c4;
	private Customer c5;
	private Customer c6;
	private List<Customer> listaCustomers;
	private CustomersBo cBo; 
	private Recomendacion r;
	private Recomendacion r2;
	private Recomendacion r3;
	private Recomendacion r4;
	private Recomendacion r5;
	private Recomendacion r6;
	String s = "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0,ensalada/20.0,fideos/30.0)_20-12-2018";
	// MongoConcrete m;
	MongoConcreteStub m;
	String filtroEspecial = "";
	String filtroChatarras = "";
	String filtroPostres = "";
	String filtroSanas = "";
	String filtroPastas = "";
	String preferenciasChatarras = "";
	String preferenciasPostres = "";
	String preferenciasSanas = "";
	String preferenciasPastas = "";
	ArrayList<Preferencias> listaPreferencias;
	

	/**
	 * Constructora del modelo. Crea un modelo, inicializa variables. Crea la
	 * lista de los observadores.
	 */
	public Modelo(String valor, String nombreUsuario, String emailUsuario) {
		this.valorString = valor;
		this.email = emailUsuario;
		this.usuario = nombreUsuario;
		listaPreferencias = new ArrayList<Preferencias>();
		listaCustomers = new ArrayList<Customer>();
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
		pBo.mostrarListaDeProductos();
		m = new MongoConcreteStub();
		m.conectarseMongoDB();
	}

	@SuppressWarnings("static-access")
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
		pBo.mostrarListaDeProductos();

	}

	public void filtroA() {
		r.buscarPreferenciasUsuarioConFiltro();
		DBObject valor2 = r.getF2();
		DBObject valor4 = r.getF4();

		String[] parts = valor2.toString().split(",");
		String[] parts2 = valor4.toString().split(",");

		String valor2String = parts[1] + " | " + parts[2] + " | " + parts[3]
				+ " , " + parts[4] + " , " + parts[5] + " | " + parts[7]
				+ " | " + parts[8] + "\n" + parts2[1] + " | " + parts2[2]
				+ " | " + parts2[3] + " , " + parts2[4] + " , " + parts2[5]
				+ " | " + parts2[7] + " | " + parts2[8];

		setModeloFiltros(valor2String, usuario, email);
	}

	public void filtroB() {
		r2.buscarPreferenciasUsuarioConFiltro();
		DBObject valor2 = r2.getF2();
		DBObject valor4 = r2.getF4();
		String[] parts = valor2.toString().split(",");
		String[] parts2 = valor4.toString().split(",");

		String valor2String = parts[1] + " | " + parts[2] + " | " + parts[3]
				+ " , " + parts[4] + " , " + parts[5] + " | " + parts[7]
				+ " | " + parts[8] + "\n" + parts2[1] + " | " + parts2[2]
				+ " | " + parts2[3] + " , " + parts2[4] + " , " + parts2[5]
				+ " | " + parts2[7] + " | " + parts2[8];

		setModeloFiltros(valor2String, usuario, email);
	}

	public void filtroChatarras() {

		r3.buscarPreferenciasUsuarioConFiltro();
		DBObject valor2 = r3.getF2();
		String[] parts = valor2.toString().split(",");
		filtroChatarras = parts[1] + " | " + parts[2] + " | " + parts[3]
				+ " , " + parts[4] + " , " + parts[5] + " | " + parts[7]
				+ " | " + parts[8];
		setModeloFiltros(filtroChatarras, usuario, email);
	}

	public void filtroPostres() {
		r4.buscarPreferenciasUsuarioConFiltro();
		DBObject valor2 = r4.getF2();
		String[] parts = valor2.toString().split(",");
		filtroPostres = parts[1] + " | " + parts[2] + " | " + parts[3] + " , "
				+ parts[4] + " , " + parts[5] + " | " + parts[7] + " | "
				+ parts[8];
		setModeloFiltros(filtroPostres, usuario, email);
	}

	public void filtroSanas() {
		r5.buscarPreferenciasUsuarioConFiltro();
		DBObject valor2 = r5.getF2();
		String[] parts = valor2.toString().split(",");
		filtroSanas = parts[1] + " | " + parts[2] + " | " + parts[3] + " , "
				+ parts[4] + " , " + parts[5] + " | " + parts[7] + " | "
				+ parts[8];
		setModeloFiltros(filtroSanas, usuario, email);
	}

	public void filtroPastas() {
		r6.buscarPreferenciasUsuarioConFiltro();
		DBObject valor2 = r6.getF2();
		String[] parts = valor2.toString().split(",");
		filtroPastas = parts[1] + " | " + parts[2] + " | " + parts[3] + " , "
				+ parts[4] + " , " + parts[5] + " | " + parts[7] + " | "
				+ parts[8];
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
		listaCustomers=cBo.getListaDeCustomers();
		cBo.mostrarListaDeCustomers();
	}

	public void cargarRecomendaciones() {//Ahora cargo las recomendaciones para todos los cBo

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
	
	public void cargarUnaRecomendacion(Recomendacion reco, Customer user){
		System.out.println("\ncustomer recomendacion: "+ user.getUserName().getUsuario());
		reco.leerPreferencias();
		reco.mostrarListProdDeTwitter(s, m.getPromos());
	}
}