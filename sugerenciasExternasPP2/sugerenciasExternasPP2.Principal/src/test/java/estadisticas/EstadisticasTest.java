package estadisticas;

import java.lang.reflect.InvocationTargetException;

import mvc_modelo_observable.Modelo;

import org.junit.Test;

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
		p.getPromoMasCara(m.getMongo().getPromos());
		p.getPromoMasEconomica(m.getMongo().getPromos());
	}
}
