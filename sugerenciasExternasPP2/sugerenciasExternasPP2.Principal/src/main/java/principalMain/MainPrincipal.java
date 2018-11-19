package principalMain;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Properties;

import com.mongodb.BasicDBObject;

import conexiones.PromoTwitterStub;
import conexiones.Interfaz.ConexionDinamica;
import conexiones.conexionTwitter.UsoTwitterDeUsuario;
import bo.CustomersBo;
import bo.ProductosBo;
import promo.Twitter.PromoTwitter;
import properties.Constants;
import twitter4j.TwitterException;
import dao.mongoDB.MongoConcrete;
import modelo.Customer;
import modelo.Preferencias;
import modelo.Recomendacion;
import modelo.Usuario;

public class MainPrincipal {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, TwitterException {
		ArrayList<Preferencias> listaPreferencias = new ArrayList<Preferencias>();

		Usuario u3 = new Usuario("cristian", "cristian@yahoo.com.ar");
		Preferencias p5 = new Preferencias(4, "pastas");
		Preferencias p6 = new Preferencias(2, "postres");
		listaPreferencias.add(p5);
		listaPreferencias.add(p6);
		Customer c3 = new Customer("3", u3, listaPreferencias);
		// listaPreferencias.clear();

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

		// Plugin
		ConexionDinamica c = new ConexionDinamica();

		// Escriba-cast y acceda a los datos de la clase Base.
		MongoConcrete conexionMongoReal = (MongoConcrete) c
				.conexionExternaDinamica("mongoDB");
		// // Me conecto a la base de datos real de MongoDB
		// MongoConcrete mongoReal = new MongoConcrete();
		conexionMongoReal.conectarseMongoDB();

		// Leo la coleccion de documentos de MongoDB
		conexionMongoReal.leerColeccion();
		conexionMongoReal.buscarYmostrarTodosLosDocumentos();
		System.out.println("Elimino la coleccion!");
		conexionMongoReal.eliminarTodaLaColeccion();

		String sComidaValida = "#promo:mcDonalds_sanIsidro_lista(hamburguesa/40.0,ensalada/60.0,helado/30.0)_20-12-2018";

		Recomendacion recomendacion = new Recomendacion(c3);
		System.out.println("Leo preferencias del customer");
		recomendacion.leerPreferencias();
		System.out.println("Fin, Leo preferencias del customer");
//		recomendacion.mostrarListProdDeTwitter(sComidaValida,
//				conexionMongoReal.leerColeccion())
		recomendacion.mostrarRecomendaciones(conexionMongoReal.leerColeccion());
		System.out.println("Recomendacion: "
				+ recomendacion.buscarPreferenciasUsuarioConFiltro());// boolean

		// Leo todos los Customers que tengo en CustomersBo
		CustomersBo cBo = new CustomersBo();
		cBo.getListaDeCustomers();
		cBo.mostrarListaDeCustomers();

		// -----------------------------------------------
//		ConexionDinamica c = new ConexionDinamica();
//
//		// Escriba-cast y acceda a los datos de la clase Base.
//		PromoTwitterStub conexion = (PromoTwitterStub) c
//				.conexionExternaDinamica("twitterStub");
//		conexion.conectarseMongoDBstub();
//		conexion.mostrarListProdDeTwitter(sComidaValida);
	}

}
