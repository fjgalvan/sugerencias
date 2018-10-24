package principalMain;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.mongodb.BasicDBObject;

import bo.ProductosBo;
import promo.Twitter.PromoTwitter;
import properties.Constants;
import dao.mongoDB.MongoConcrete;
import dto.CustomerDto;
import modelo.Preferencias;
import modelo.Recomendacion;
import modelo.Usuario;

public class MainPrincipal {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Usuario u= new Usuario("javier", "javier@yahoo.com.ar");
		Preferencias p1= new Preferencias(1,"chatarras");//codigo-descripcion
		Preferencias p2= new Preferencias(2,"postres");
		CustomerDto c1= new CustomerDto("1",u,p1, p2);
		
		Usuario u2= new Usuario("fernando", "fernando@yahoo.com.ar");
		Preferencias p3= new Preferencias(3,"sanas");
		Preferencias p4= new Preferencias(2,"postres");
		CustomerDto c2= new CustomerDto("2",u2,p3, p4);
		
		Usuario u3= new Usuario("cristian", "cristian@yahoo.com.ar");
		Preferencias p5= new Preferencias(4,"pastas");
		Preferencias p6= new Preferencias(2,"postres");
		CustomerDto c3= new CustomerDto("3",u3,p5, p6);
		
		Properties prop1 = new Properties();
		try { prop1.load(new FileReader(Constants.ROUTE_COMIDAS_PROPERTIES));
		} catch (IOException e) { e.printStackTrace();}
		
		
		
		//Leo todos los Productos que tengo en ProductosBo
		ProductosBo pBo= new ProductosBo();
		pBo.getListaDeProductos();
		pBo.mostrarListaDeProductos();
		
		//Me conecto a la base de datos real de MongoDB
		MongoConcrete mongoReal= new MongoConcrete();
		mongoReal.conectarseMongoDB();
		
		//Leo la coleccion de documentos de MongoDB
		mongoReal.leerColeccion();
		mongoReal.buscarYmostrarTodosLosDocumentos();
		System.out.println("Elimino la coleccion!");
		mongoReal.eliminarTodaLaColeccion();
		
		String sComidaValida= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/40.0,ensalada/60.0,helado/30.0)_20-12-2018";
		
		Recomendacion recomendacion= new Recomendacion(c3);
		recomendacion.leerPreferencias();
		recomendacion.mostrarListProdDeTwitter(sComidaValida, mongoReal.leerColeccion());
		System.out.println(recomendacion.buscarPreferenciasUsuarioConFiltro());//boolean
		
	}

}
