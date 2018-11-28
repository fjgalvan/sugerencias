package estadisticas;

import java.lang.reflect.InvocationTargetException;

import mvc_modelo_observable.Modelo;

import org.junit.Test;

import dao.mongoDB.MongoConcreteStub;
import twitter4j.TwitterException;

public class EstadisticasTest {
	@Test
    public void promosOpuestosEnPrecioTest() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException{
		PromosOpuestosEnPrecio p= new PromosOpuestosEnPrecio();
		Modelo m = new Modelo("recomendaciones","usuario", "email", "horarioActualizacion");
		m.cargarCustomers();
		m.cargarMapReco();
		m.cargarRecomendacionesGenerales(m.cargarMapReco());
		m.cargarTodasLasPromos();
		m.ConectarMongoDBStub();
		MongoConcreteStub mongo= new MongoConcreteStub();
		Modelo modelo= new Modelo("a","e","i","0");
		modelo.ConectarMongoDBStub();
		modelo.cargarRecomendacionesGenerales(modelo.getMapRecomendaciones());
		mongo.setColl(modelo.cargarTodasLasPromos());
		p.getPromoMasCara(m.getMongo().getPromos());
		p.getPromoMasEconomica(m.getMongo().getPromos());
		p.getPromoMasCara(modelo.cargarTodasLasPromos());
		p.getPromoMasEconomica(modelo.cargarTodasLasPromos());
	}
	
	
    
}
