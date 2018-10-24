package modelo;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.bson.Document;

import promo.Twitter.PromoTwitter;
import properties.Constants;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.Sugerencias;
import validaciones.ValidarFechaPromo;
import validaciones.ValidarTwitter;
import bo.ProductosBo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;

import dto.CustomerDto;

public class Recomendacion {
	PromoTwitter pt = null;
	Properties p1;
	Properties p2;
	Properties p3;
	DBObject fa;
	DBObject fb;

	CustomerDto customer;
	ArrayList<Promocion> lPromciones;
	ArrayList<Promocion> lRecomendaciones;

	// Recomendaciones para usuarios
	public Recomendacion(CustomerDto customer) {
		this.customer = customer;
	}

	// Recomendaciones en general
	public Recomendacion(List<Preferencias> listaPreferencias) {
		// -------
	}

	public void leerPreferencias() {// Cada usuario tiene 2 preferencias
		System.out.println("Preferencia1: "
				+ this.customer.getPreferencias().getCodigo()+"--desc: "+this.customer.getPreferencias().getDescripcion());
		System.out.println("Preferencia2: "
				+ this.customer.getPreferencias2().getCodigo()+"--desc: "+this.customer.getPreferencias2().getDescripcion());
	}

//	// Guardo las promos de Twitter
	public DBCollection mostrarListProdDeTwitter(String s, DBCollection coll) {

		ArrayList<Sugerencias> lSugerencias;
		// Valido formato del Tweet y Fecha de Vencimiento de la promo del Tweet
		System.out.println("Valido formato del Tweet y Fecha de Vencimiento de la promo del Tweet");
		ValidarTwitter vt = new ValidarTwitter(s);
		ValidarFechaPromo vf = new ValidarFechaPromo(s);
		if ((vt.twitterStringValido()) && (vf.VigenciaPromoValida())) {
			// Parseo el Tweet a Sugerencia
			System.out.println("Parseo el Tweet a Sugerencia");
			ConvertirString_a_Sugerencia cs = new ConvertirString_a_Sugerencia(
					s);
			cs.convertirLocal();
			cs.convertirUbicacion();
			cs.convertirLista();
			cs.convertirFecha();
			// Obtengo una lista de Sugerencias
			System.out.println("Obtengo una lista de Sugerencias");
			lSugerencias = new ArrayList<Sugerencias>();
			lSugerencias = cs.getListSugerenciaTwitter();

			// Obtengo una lista de Promociones
			System.out.println("Obtengo una lista de Promociones");
			lPromciones = new ArrayList<Promocion>();
			for (Sugerencias sug : lSugerencias) {
				System.out.println("entro al for!");
				Producto producto = buscarObjetoProducto(sug.getProducto());
				Promocion pro = new Promocion(sug.getLocal(),
						sug.getUbicacion(), producto, sug.getPrecio(),
						sug.getFechaDeVigencia());
				//pro.mostrarPromo();
				lPromciones.add(pro);
			}

			// Declaramos el Iterador e imprimimos los Elementos del ArrayList
			// de Sugerencias
			System.out.println("Declaramos el Iterador e imprimimos los Elementos del ArrayList de Sugerencias");
			Iterator<Sugerencias> nombreIterator = lSugerencias.iterator();
			while (nombreIterator.hasNext()) {
				Sugerencias elemento = nombreIterator.next();
				System.out.print(elemento.getProducto() + " / "
						+ elemento.getPrecio() + "\n");
			}

			// Declaramos el Iterador e imprimimos los Elementos del ArrayList
			// de Promocion
			System.out.println("Declaramos el Iterador e imprimimos los Elementos del ArrayList de Promocion");
			Iterator<Promocion> nIterator = lPromciones.iterator();
			while (nIterator.hasNext()) {
				Promocion elemento = nIterator.next();
				System.out.print(elemento.getProducto().getNombre() + " / "
						+ elemento.getPrecio() + "\n");
			}

			// PARSEO A JSON y A BSON
			System.out.println("PARSEO A JSON y A BSON");
			pt = new PromoTwitter();
			pt.parsear_a_JSON(lSugerencias, coll);
		}
		return pt.getCollection();
	}

	// Busco en el listado de todos los productos que menejo
	private Producto buscarObjetoProducto(String producto) {
		//System.out.println("buscarObjetoProducto ->"+producto);
		Producto ObjProducto = null;
		// Busco el codigo y la descripcion del producto
		// Leo todos los Productos que tengo en ProductosBo
		ProductosBo pBo = new ProductosBo();
		pBo.getListaDeProductos();
		
		for (Producto prod: pBo.getListaDeProductos()) {
		    if (prod.getNombre().equals(producto)) {
				ObjProducto = new Producto(prod.getCodigo(), prod.getNombre(),prod.getDescripcion());
			}
		}
		ObjProducto.mostrarProducto();
		return ObjProducto;
	}

	public boolean buscarPreferenciasUsuarioConFiltro() {
		String comida1="";
		String comida2="";
		System.out.println("buscarPreferenciasUsuarioConFiltro()");
		fa = null;
		DBObject f2 = null;
		fb = null;
		DBObject f4 = null;
		DBCollection col = pt.getCollection();

		// Declaramos el Iterador e imprimimos los Elementos del ArrayList
		// de Promocion
		Iterator<Promocion> nIterator = lPromciones.iterator();
		while (nIterator.hasNext()) {
			Promocion elemento = nIterator.next();
			if(elemento.getProducto().getDescripcion().equals(this.customer.getPreferencias().getDescripcion())){
				comida1= elemento.getProducto().getNombre();
				System.out.println("Se encontró la preferencia 1 del usuario dentro de las promociones");
				System.out.println("comida1: "+ elemento.getProducto().getNombre());
				lRecomendaciones.add(elemento);
				
			}
			if(elemento.getProducto().getDescripcion().equals(this.customer.getPreferencias2().getDescripcion())){
				comida2= elemento.getProducto().getNombre();
				System.out.println("Se encontró la preferencia 2 del usuario dentro de las promociones");
				System.out.println("comida2: "+elemento.getProducto().getNombre());
				lRecomendaciones.add(elemento);
			}
		}
		BasicDBObject filtro2 = new BasicDBObject();
		filtro2.put("producto", comida1);
		DBCursor cur2 = col.find(filtro2);
		while (cur2.hasNext()) {
			f2 = cur2.next();
		}
		BasicDBObject filtro4 = new BasicDBObject();
		filtro4.put("producto", comida2);
		DBCursor cur4 = col.find(filtro4);
		while (cur4.hasNext()) {
			f4 = cur4.next();
		}
		if((f2!=null) || (f4!=null)){
			return true;
		}
		return false;
	}

	public Properties getP1() {
		return p1;
	}

	public Properties getP2() {
		return p2;
	}

	public DBObject getFa() {
		return fa;
	}

	public DBObject getFb() {
		return fb;
	}

	public ArrayList<Promocion> getlRecomendaciones() {
		return lRecomendaciones;
	}
	
	
}