package mvc_modelo_observable;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import bo.ProductosBo;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import dao.Interfaz.InterfaceMongoAccess;
import dao.mongoDB.MongoConcrete;
import dto.CustomerDto;

import java.util.Observable;

import modelo.Preferencias;
import modelo.Recomendacion;
import modelo.Usuario;
import promo.Twitter.PromoTwitter;
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
	private String preferencias;
	private String usuario;
	private CustomerDto c1;
	private CustomerDto c2;
	Recomendacion r;
	Recomendacion r2;
	String s = "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0,ensalada/20.0,fideos/30.0)_20-11-2018";
	Properties p1;
	Properties p2;
	MongoConcrete m;

	/**
	 * Constructora del modelo. Crea un modelo, inicializa variables. Crea la
	 * lista de los observadores.
	 */
	public Modelo(String valor, String preferencias, String usuario) {
		this.valorString = valor;
		this.preferencias = preferencias;
		this.usuario = usuario;
		inicializar();
	}

	public void ConectarMongoDB() {
		// Leo todos los Productos que tengo en ProductosBo
		ProductosBo pBo = new ProductosBo();
		pBo.getListaDeProductos();
		pBo.mostrarListaDeProductos();
		m = new MongoConcrete();
		m.conectarseMongoDB();
		// Leo la coleccion de documentos de MongoDB
		m.leerColeccion();
		m.buscarYmostrarTodosLosDocumentos();
		System.out.println("Elimino la coleccion!");
		m.eliminarTodaLaColeccion();
	}

	public void inicializar() {
		Usuario u = new Usuario("javier", "javier@yahoo.com.ar");
		Preferencias pref1 = new Preferencias(1, "chatarras");// codigo-descripcion
		Preferencias pref2 = new Preferencias(2, "postres");
		c1 = new CustomerDto("1", u, pref1, pref2);

		Usuario u2 = new Usuario("fernando", "fernando@yahoo.com.ar");
		Preferencias pref3 = new Preferencias(3, "sanas");
		Preferencias pref4 = new Preferencias(2, "postres");
		c2 = new CustomerDto("2", u2, pref3, pref4);

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
		// IMPRIMO BSON
		System.out.println("inicializar Modelo!");
		System.out.println("promos count:" + m.getPromos().count());
		DBCursor cursorDoc5 = m.getPromos().find();
		while (cursorDoc5.hasNext()) {
			System.out.println(cursorDoc5.next());
		}
		System.out.println("FIN inicializar Modelo!");
		r.leerPreferencias();
		r.mostrarListProdDeTwitter(s,  m.getPromos());
		r2.leerPreferencias();
		r2.mostrarListProdDeTwitter(s,  m.getPromos());
	}

	public void filtroA() {
		r.buscarPreferenciasUsuarioConFiltro();
		DBObject valor2 = r.getF2();
		DBObject valor4 = r.getF4();
		
		String[] parts = valor2.toString().split(",");
		String[] parts2 = valor4.toString().split(",");
		
		String valor2String = parts[1]+" | "+parts[2]+" | "+parts[3]+" , "+parts[4]+" , "+parts[5]
				+" | "+parts[7]+" | "+parts[8] 
				+"\n"+parts2[1]+" | "+parts2[2]+" | "+parts2[3]+" , "+parts2[4]+" , "+parts2[5]
				+" | "+parts2[7]+" | "+parts2[8]; 
		
		
		
		String preferencias2 = c1.getPreferencias().getDescripcion().toString()
				+ " - " + c1.getPreferencias2().getDescripcion().toString();
		String usuario2 = "A";

		setModeloFiltros(valor2String, preferencias2, usuario2);
		System.out.println("valor2String: " + valor2String);
		System.out.println("preferencias2: " + preferencias2);
		System.out.println("usuario2: " + usuario2);
	}

	public void filtroB() {
		r2.buscarPreferenciasUsuarioConFiltro();
		DBObject valor2 = r2.getF2();
		DBObject valor4 = r2.getF4();
		String[] parts = valor2.toString().split(",");
		String[] parts2 = valor4.toString().split(",");
		
		String valor2String = parts[1]+" | "+parts[2]+" | "+parts[3]+" , "+parts[4]+" , "+parts[5]
				+" | "+parts[7]+" | "+parts[8] 
				+"\n"+parts2[1]+" | "+parts2[2]+" | "+parts2[3]+" , "+parts2[4]+" , "+parts2[5]
				+" | "+parts2[7]+" | "+parts2[8]; 
		
		String preferencias2 = c2.getPreferencias().getDescripcion().toString()
				+ " - " + c2.getPreferencias2().getDescripcion().toString();
		String usuario2 = "B";

		setModeloFiltros(valor2String, preferencias2, usuario2);
		System.out.println("valor2String: " + valor2String);
		System.out.println("preferencias2: " + preferencias2);
		System.out.println("usuario2: " + usuario2);
	}

	public void setModeloFiltros(String valorString, String p, String u) {
		this.valorString = valorString;
		this.preferencias = p;
		this.usuario = u;

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