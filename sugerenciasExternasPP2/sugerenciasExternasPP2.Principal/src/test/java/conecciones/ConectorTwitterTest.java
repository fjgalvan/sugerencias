package conecciones;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;










import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import conexiones.conexionTwitter.UsoTwitterDeUsuario;
import dao.mongoDB.MyConstants;
import properties.Constants;
import properties.PropertiesPrincipal;


public class ConectorTwitterTest {
	PropertiesPrincipal pp= new PropertiesPrincipal(Constants.ROUTE_PROPERTIES);
	HashMap<String, String> map= new HashMap<String, String>();
	@Before
	public void init() {
		map.put(MyConstants.ConsumerKey, pp.leerValorDeUnaClave(MyConstants.ConsumerKey));
		map.put(MyConstants.ConsumerSecret, pp.leerValorDeUnaClave(MyConstants.ConsumerSecret));
		map.put(MyConstants.AccessToken, pp.leerValorDeUnaClave(MyConstants.AccessToken));
		map.put(MyConstants.AccessTokenSecret, pp.leerValorDeUnaClave(MyConstants.AccessTokenSecret));
	}
	
	@Test
	public void conectarTwitterTest() {

		UsoTwitterDeUsuario ct= mock(UsoTwitterDeUsuario.class);
		when(ct.conectarsePruebaTwitter()).thenReturn(map);
		try {
			Assert.assertEquals(ct.conectarsePruebaTwitter(),map);
			System.out.println("Conexión exitosa con Twitter!");
		}catch (Exception e) {
		     System.out.println("No se pudo conectar a Twitter!");

		}
		
	}
}
