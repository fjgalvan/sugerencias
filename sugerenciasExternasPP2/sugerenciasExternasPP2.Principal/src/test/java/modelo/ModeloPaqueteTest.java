package modelo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.Test;

import com.mongodb.DBCollection;

import conexiones.Interfaz.RecolectorPromos;
import dao.mongoDB.MongoConcreteStub;
import twitter4j.TwitterException;
import util_.Date;

public class ModeloPaqueteTest {
	@Test
    public void customerTest(){
		Usuario u= new Usuario("u","usuario@yahoo.com.ar");
		ArrayList<Preferencias> listaPreferencias= new ArrayList<Preferencias>();
		Preferencias p1 = new Preferencias(1,"chatarras");
		Preferencias p2 = new Preferencias(2,"postres");
		listaPreferencias.add(p1);
		listaPreferencias.add(p2);
		Customer c1= new Customer("10", u,listaPreferencias);
		c1.getId();
		c1.getUserName();
		c1.getListaPreferencias();
	}
	
	@Test
    public void preferenciasTest(){
		Preferencias p = new Preferencias(1,"chatarras");
		p.getCodigo();
		p.setCodigo(p.getCodigo());
		p.getDescripcion();
		p.setDescripcion(p.getDescripcion());
		p.getProductos();
		
	}
	
	@Test
    public void productoTest(){
		Producto pro= new Producto(1,"hamburguesa", "chatarras");
		pro.mostrarProducto();
		pro.getNombre();
		pro.setNombre(pro.getNombre());
		pro.getDescripcion();
		pro.setDescripcion(pro.getDescripcion());
		pro.getCodigo();
		pro.setCodigo(pro.getCodigo());
	}
	
	@Test
    public void promocionTest(){
		Date fechaVigencia= new Date(28,11,2018);
		Promocion p= new Promocion("mcDonald", "virreyes", "hamburguesa", 100.0, fechaVigencia);
		p.nombreLocal=p.getNombreLocal();
		p.ubicacion= p.getUbicacion();
		p.Producto=p.getProducto();
		p.precio= p.getPrecio();
		p.fechaVigencia= p.getFechaVigencia();
		//p.mostrarPromo();
		p.getNombreLocal();
		p.setNombreLocal(p.getNombreLocal());
		p.getFechaVigencia();
		p.setFechaVigencia(p.getFechaVigencia());
		p.getUbicacion();
		p.setUbicacion(p.getUbicacion());
		p.getProducto();
		p.setProducto(p.getProducto());
		p.getPrecio();
		p.setPrecio(p.getPrecio());
		p.getProductoNombre();
		
	}
	
	@Test
    public void recomendacionTest() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException{
		Usuario u= new Usuario("u","usuario@yahoo.com.ar");
		ArrayList<Preferencias> listaPreferencias= new ArrayList<Preferencias>();
		Preferencias p1 = new Preferencias(1,"chatarras");
		Preferencias p2 = new Preferencias(2,"postres");
		listaPreferencias.add(p1);
		listaPreferencias.add(p2);
		Customer c1= new Customer("10", u,listaPreferencias);
		Recomendacion r= new Recomendacion(c1);
		r.leerPreferencias();
		RecolectorPromos c = new RecolectorPromos();//sin argumento con mongoStub
		
		c.cargarListaConectores();
		c.buscarPromociones();
		c.promosConFiltros("chatarras", "postres");
		//c.getMongoDB().leerColeccion();
		r.leerPreferencias();
		DBCollection coll=r.mostrarRecomendaciones(c.getMongoDB().getPromos());
		r.mostrarRecomendaciones(c.getMongoDB().getPromos());
		String s="#promo:Terrabusi_Martinez_lista(hamburguesa/120.0,flan/55.0,sopa/30.0,canelones/30.0)_10-12-2018";
		r.mostrarListProdDeTwitter(s, coll);
		r.buscarPreferenciasUsuarioConFiltro();
		r.getF2();
		r.getF4();
		r.getlRecomendaciones();
	}
	@Test
    public void usuarioTest(){
		Usuario u= new Usuario("javier", "javier@yahoo.com.ar");
		u.getUsuario();
		u.setUsuario(u.getUsuario());
		u.geteMail();
		u.seteMail(u.geteMail());
		u.getPreferencias();
		u.setPreferencias(u.getPreferencias());
	}
}
