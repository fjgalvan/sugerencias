package estadisticas;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import modelo.Customer;
import modelo.Preferencias;
import modelo.Recomendacion;
import modelo.Usuario;
import mvc_modelo_observable.Modelo;

import org.junit.Test;

import com.mongodb.DBCollection;

import conexiones.Interfaz.RecolectorPromos;
import dao.mongoDB.MongoConcreteStub;
import twitter4j.TwitterException;

public class EstadisticasTest {
	@Test
    public void promosOpuestosEnPrecioTest() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException{
		PromosOpuestosEnPrecio p= new PromosOpuestosEnPrecio();
		Modelo m = new Modelo("recomendaciones","usuario", "email", "horarioActualizacion");
		m.cargarCustomers();
		m.cargarMapReco();
		m.cargarRecomendacionesGenerales(m.getMapRecomendaciones());
		m.cargarTodasLasPromos();
		m.ConectarMongoDBStub();
		RecolectorPromos c = new RecolectorPromos();//sin argumento con mongoStub
		
		c.cargarListaConectores();
		c.buscarPromociones();
		c.promosConFiltros("chatarras", "postres");
		//c.getMongoDB().leerColeccion();
		Usuario u= new Usuario("u","usuario@yahoo.com.ar");
		ArrayList<Preferencias> listaPreferencias= new ArrayList<Preferencias>();
		Preferencias p1= new Preferencias(1,"chatarras");
		Preferencias p2= new Preferencias(2,"postres");
		listaPreferencias.add(p1);
		listaPreferencias.add(p2);
		Customer c1= new Customer("10", u,listaPreferencias);
		Recomendacion reco= new Recomendacion(c1);
		reco.leerPreferencias();
		DBCollection coll=reco.mostrarRecomendaciones(c.getMongoDB().getPromos());
		System.out.println("coll count: "+ coll.count());
		p.getPromoMasCara(coll);
		p.getPromoMasEconomica(coll);
	}
	
	
	
    
}
