package sugerencias;

import io.netty.util.Constant;

import java.util.HashMap;

import org.junit.Test;

import dao.mongoDB.MyConstants;
import properties.Constants;
import properties.PropertiesPrincipal;
import util_.Date;

public class SugerenciasTest {
	
	@Test
    public void sugerenciasTest(){
		Sugerencias s= new Sugerencias("local","ubicacion","producto","precio","fechaDeVigencia");
		HashMap<String,Double> mapProd= new HashMap<String,Double>();
		Date d= new Date(30,12,2018);
		d.setDd(31);
		d.setMm(12);
		d.setAaaa(2019);
		d.getDd();
		Sugerencias s2= new Sugerencias("local","ubicacion",mapProd,d);
		Sugerencias s3= new Sugerencias("local","ubicacion","producto",10.0,d);
		
		s2.getFechaDeVigencia();
		s2.getListaProductosPrecios();
		s.getLocal();
		s3.getPrecio();
		s.getProducto();
		s.getUbicacion();
		PropertiesPrincipal p= new PropertiesPrincipal(Constants.ROUTE_COMIDAS_ACEPTADAS);
		p.leerTodas();
		
	}
	
	@Test
    public void sugerenciaExcelTest(){
		SugerenciaExcel s= new SugerenciaExcel("local","ubicacion","producto","precio","fechaDeVigencia");
		s.leerSugerencias();
		
	}
	
	@Test
    public void sugerenciaTwitterTest(){
		HashMap<String,Double> mapProd= new HashMap<String,Double>();
		Date d= new Date(30,12,2018);
		SugerenciaTwitter s= new SugerenciaTwitter("local","ubicacion",mapProd,d);
		s.conexionConTwitter();
		s.usarTwitterDeUsuario();
		s.leerSugerencias();
		
	}
}
