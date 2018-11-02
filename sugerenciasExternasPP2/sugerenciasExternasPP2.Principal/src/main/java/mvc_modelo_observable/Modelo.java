package mvc_modelo_observable;

import java.io.FileReader; 
import java.io.IOException;
import java.util.Properties;
import bo.ProductosBo;
import com.mongodb.DBObject;
import dao.mongoDB.MongoConcrete;
import dto.CustomerDto;
import java.util.Observable;
import modelo.Preferencias;
import modelo.Recomendacion;
import modelo.Usuario;
import properties.Constants;

/**
 * Modelo de mi programa, aquí estará toda la lógica y el funcionamiento interno
 * de este. Lo hacemos Observable sobre los observadores del modelo, para que
 * sean notificados con lo que les interese.
 * 
 * @author fon
 */
public class Modelo extends Observable {
	private String valorString;// PARECE QUE SOLO ACEPTA PRIMITIVOS Y NO
								// DBObject!!
	private String email;
	private String usuario;
	private CustomerDto c1;
	private CustomerDto c2;
	private CustomerDto c3;
	private CustomerDto c4;
	private CustomerDto c5;
	private CustomerDto c6;
	Recomendacion r;
	Recomendacion r2;
	Recomendacion r3;
	Recomendacion r4;
	Recomendacion r5;
	Recomendacion r6;
	String s = "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0,ensalada/20.0,fideos/30.0)_20-12-2018";
	MongoConcrete m;
	String filtroEspecial="";
	String filtroChatarras="";
	String filtroPostres="";
	String filtroSanas="";
	String filtroPastas="";
	String preferenciasChatarras="";
	String preferenciasPostres="";
	String preferenciasSanas="";
	String preferenciasPastas="";

	/**
	 * Constructora del modelo. Crea un modelo, inicializa variables. Crea la
	 * lista de los observadores.
	 */
	public Modelo(String valor, String nombreUsuario, String emailUsuario) {
		this.valorString = valor;
		this.email = emailUsuario;
		this.usuario = nombreUsuario;
		inicializar();
	}

	public void ConectarMongoDB() {
		// Leo todos los Productos que tengo en ProductosBo
		ProductosBo pBo = new ProductosBo();
		pBo.getListaDeProductos();
		pBo.mostrarListaDeProductos();
		m = new MongoConcrete();
		m.conectarseMongoDB();
		System.out.println("Elimino la coleccion!");
		m.eliminarTodaLaColeccion();
	}

	@SuppressWarnings("static-access")
	public void inicializar() {
		Usuario u = new Usuario("javier", "javier@yahoo.com.ar");
		Preferencias pref1 = new Preferencias(1, "chatarras");// codigo-descripcion
		Preferencias pref2 = new Preferencias(2, "postres");
		c1 = new CustomerDto("1", u, pref1, pref2);

		Usuario u2 = new Usuario("fernando", "fernando@yahoo.com.ar");
		Preferencias pref3 = new Preferencias(3, "sanas");
		Preferencias pref4 = new Preferencias(2, "postres");
		c2 = new CustomerDto("2", u2, pref3, pref4);
		
		Usuario us1 = new Usuario("us1", "us1@yahoo.com.ar");
		Preferencias pref5 = new Preferencias(1, "chatarras");// codigo-descripcion
		c3 = new CustomerDto("3", us1, pref5, pref5);

		Usuario us2 = new Usuario("us2", "us2@yahoo.com.ar");
		Preferencias pref6 = new Preferencias(2, "postres");// codigo-descripcion
		c4 = new CustomerDto("4", us2, pref6, pref6);
		
		Usuario us3 = new Usuario("us3", "us3@yahoo.com.ar");
		Preferencias pref7 = new Preferencias(3, "sanas");// codigo-descripcion
		c5 = new CustomerDto("5", us3, pref7, pref7);
		
		Usuario us4 = new Usuario("us4", "us4@yahoo.com.ar");
		Preferencias pref8 = new Preferencias(4, "pastas");// codigo-descripcion
		c6 = new CustomerDto("6", us4, pref8, pref8);
		
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

		// Inicializamos atributos...
		r = new Recomendacion(c1);
		r2 = new Recomendacion(c2);
		r3 = new Recomendacion(c3);
		r4 = new Recomendacion(c4);
		r5 = new Recomendacion(c5);
		r6 = new Recomendacion(c6);
		
		r.leerPreferencias();
		r.mostrarListProdDeTwitter(s, m.getPromos());
		r2.leerPreferencias();
		r2.mostrarListProdDeTwitter(s, m.getPromos());
		r3.leerPreferencias();
		r3.mostrarListProdDeTwitter(s, m.getPromos());
		r4.leerPreferencias();
		r4.mostrarListProdDeTwitter(s, m.getPromos());
		r5.leerPreferencias();
		r5.mostrarListProdDeTwitter(s, m.getPromos());
		r6.leerPreferencias();
		r6.mostrarListProdDeTwitter(s, m.getPromos());
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
		filtroPostres = parts[1] + " | " + parts[2] + " | " + parts[3]
				+ " , " + parts[4] + " , " + parts[5] + " | " + parts[7]
				+ " | " + parts[8];
		setModeloFiltros(filtroChatarras, usuario, email);
	}
	public void filtroSanas() {
		r5.buscarPreferenciasUsuarioConFiltro();
		DBObject valor2 = r5.getF2();
		String[] parts = valor2.toString().split(",");
		filtroSanas = parts[1] + " | " + parts[2] + " | " + parts[3]
				+ " , " + parts[4] + " , " + parts[5] + " | " + parts[7]
				+ " | " + parts[8];
		setModeloFiltros(filtroChatarras, usuario, email);
	}

	public void filtroPastas() {
		r6.buscarPreferenciasUsuarioConFiltro();
		DBObject valor2 = r6.getF2();
		String[] parts = valor2.toString().split(",");
		filtroPastas = parts[1] + " | " + parts[2] + " | " + parts[3]
				+ " , " + parts[4] + " , " + parts[5] + " | " + parts[7]
				+ " | " + parts[8];
		setModeloFiltros(filtroChatarras, usuario, email);
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
}