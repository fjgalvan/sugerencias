package principalMain;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import com.mongodb.BasicDBObject;

import bo.CustomersBo;
import bo.ProductosBo;
import promo.Twitter.PromoTwitter;
import properties.Constants;
import dao.mongoDB.MongoConcrete;
import modelo.Customer;
import modelo.Preferencias;
import modelo.Recomendacion;
import modelo.Usuario;

public class MainPrincipal {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ArrayList<Preferencias> listaPreferencias = new ArrayList<Preferencias>();

		// Usuario u= new Usuario("javier", "javier@yahoo.com.ar");
		// Preferencias p1= new Preferencias(1,"chatarras");//codigo-descripcion
		// Preferencias p2= new Preferencias(2,"postres");
		// listaPreferencias.add(p1);
		// listaPreferencias.add(p2);
		// CustomerDto c1= new CustomerDto("1",u,listaPreferencias);
		// listaPreferencias.clear();
		//
		// Usuario u2= new Usuario("fernando", "fernando@yahoo.com.ar");
		// Preferencias p3= new Preferencias(3,"sanas");
		// Preferencias p4= new Preferencias(2,"postres");
		// listaPreferencias.add(p3);
		// listaPreferencias.add(p4);
		// CustomerDto c2= new CustomerDto("2",u2,listaPreferencias);
		// listaPreferencias.clear();

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

		// Me conecto a la base de datos real de MongoDB
		MongoConcrete mongoReal = new MongoConcrete();
		mongoReal.conectarseMongoDB();

		// Leo la coleccion de documentos de MongoDB
		mongoReal.leerColeccion();
		mongoReal.buscarYmostrarTodosLosDocumentos();
		System.out.println("Elimino la coleccion!");
		mongoReal.eliminarTodaLaColeccion();

		String sComidaValida = "#promo:mcDonalds_sanIsidro_lista(hamburguesa/40.0,ensalada/60.0,helado/30.0)_20-12-2018";

		Recomendacion recomendacion = new Recomendacion(c3);
		System.out.println("Leo preferencias del customer");
		recomendacion.leerPreferencias();
		System.out.println("Fin, Leo preferencias del customer");
		recomendacion.mostrarListProdDeTwitter(sComidaValida,
				mongoReal.leerColeccion());
		System.out.println("Recomendacion: "
				+ recomendacion.buscarPreferenciasUsuarioConFiltro());// boolean

		// Leo todos los Customers que tengo en CustomersBo
		CustomersBo cBo = new CustomersBo();
		cBo.getListaDeCustomers();
		cBo.mostrarListaDeCustomers();

	}

}
