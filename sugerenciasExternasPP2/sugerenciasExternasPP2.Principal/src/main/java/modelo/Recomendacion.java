package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import promo.Twitter.PromoTwitter;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.Sugerencias;
import util_.Date;
import validaciones.ValidarFechaPromo;
import validaciones.ValidarTwitter;
import bo.ProductosBo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Recomendacion {
	PromoTwitter pt = null;
	DBObject f2;
	DBObject f4;
	Customer customer;
	ArrayList<Promocion> lPromciones;
	ArrayList<Sugerencias> lSugerencias;
	ArrayList<Promocion> lRecomendaciones;
	DBCollection promoActual;

	// Recomendaciones para usuarios
	public Recomendacion(Customer customer) {
		this.customer = customer;
		this.lRecomendaciones = new ArrayList<Promocion>();
	}

	// Recomendaciones en general
	public Recomendacion(List<Preferencias> listaPreferencias) {
		// -------
	}

	public List<Preferencias> leerPreferencias() {// Cada usuario tiene 2 preferencias
		List<Preferencias> lista= new ArrayList<Preferencias>();
		// Declaramos el Iterador e imprimimos los Elementos del ArrayList
		Iterator<Preferencias> nombreIterator = this.customer
				.getListaPreferencias().iterator();
		while (nombreIterator.hasNext()) {
			Preferencias elemento = nombreIterator.next();
			System.out.println("Preferencia: " + elemento.getCodigo()
					+ "--desc: " + elemento.getDescripcion());
			lista.add(elemento);
		}
		return lista;
	}

	public DBCollection mostrarRecomendaciones(DBCollection coll) {
		System.out.println("mostarRecom size: "+coll.count());
		// Obtengo una lista de Promociones
		lPromciones = new ArrayList<Promocion>();
		lSugerencias = new ArrayList<Sugerencias>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {

				System.out.println("HOLA mostrarRecomendaciones");
				DBObject doc= cursor.next();
				String local=(String) doc.get("local");
				String ubicacion=(String) doc.get("ubicacion");
				String producto=(String) doc.get("producto");
				Double preciod=(Double) doc.get("precio");
//				String precio= String.valueOf(preciod);
//				Date fechaDeVigenciaD=(Date) doc.get("dd");//("fechaDeVigencia");
//				System.out.println("dd: "+ fechaDeVigenciaD);
				Date fechaDeVigencia= new Date(31,12,2018);
				Sugerencias sug= new Sugerencias(local, ubicacion, producto, preciod, fechaDeVigencia);
				lSugerencias.add(sug);
			}
		} finally {
			cursor.close();
		}
		System.out.println("lSugerencias size: "+lSugerencias.size());
		for (Sugerencias sug : lSugerencias) {
			try {
		
			Producto producto = buscarObjetoProducto(sug.getProducto());
			System.out.println("producto: "+sug.getProducto());
			System.out.println("producto-descripcio: "+producto.getNombre()+"-"+producto.getDescripcion());
			Promocion pro = new Promocion(sug.getLocal(), sug.getUbicacion(),
					producto, sug.getPrecio(), sug.getFechaDeVigencia());
			lPromciones.add(pro);
			}catch(Exception e){
			}
		}
		
		
		this.promoActual= coll;
		return coll;
	}

	// // Guardo las promos de Twitter
	public DBCollection mostrarListProdDeTwitter(String s, DBCollection coll) {

		ArrayList<Sugerencias> lSugerencias;
		// Valido formato del Tweet y Fecha de Vencimiento de la promo del Tweet
		ValidarTwitter vt = new ValidarTwitter(s);
		ValidarFechaPromo vf = new ValidarFechaPromo(s);
		if ((vt.twitterStringValido()) && (vf.VigenciaPromoValida())) {
			// Parseo el Tweet a Sugerencia
			ConvertirString_a_Sugerencia cs = new ConvertirString_a_Sugerencia(
					s);
			cs.convertirLocal();
			cs.convertirUbicacion();
			cs.convertirLista();
			cs.convertirFecha();
			// Obtengo una lista de Sugerencias
			lSugerencias = new ArrayList<Sugerencias>();
			lSugerencias = cs.getListSugerenciaTwitter();

			// Obtengo una lista de Promociones
			lPromciones = new ArrayList<Promocion>();
			for (Sugerencias sug : lSugerencias) {
				Producto producto = buscarObjetoProducto(sug.getProducto());
				Promocion pro = new Promocion(sug.getLocal(),
						sug.getUbicacion(), producto, sug.getPrecio(),
						sug.getFechaDeVigencia());
				lPromciones.add(pro);
			}

			// Declaramos el Iterador e imprimimos los Elementos del ArrayList
			// de Sugerencias
			Iterator<Sugerencias> nombreIterator = lSugerencias.iterator();
			while (nombreIterator.hasNext()) {
				Sugerencias elemento = nombreIterator.next();
			}

			// Declaramos el Iterador e imprimimos los Elementos del ArrayList
			// de Promocion
			Iterator<Promocion> nIterator = lPromciones.iterator();
			while (nIterator.hasNext()) {
				Promocion elemento = nIterator.next();
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
		System.out.println("producto: "+producto);
		Producto ObjProducto = null;
		// Busco el codigo y la descripcion del producto
		// Leo todos los Productos que tengo en ProductosBo
		ProductosBo pBo = new ProductosBo();
		pBo.getListaDeProductos();

		for (Producto prod : pBo.getListaDeProductos()) {
			if (prod.getNombre().equals(producto)) {
				ObjProducto = new Producto(prod.getCodigo(), prod.getNombre(),
						prod.getDescripcion());
			}
		}
		return ObjProducto;
	}

	public boolean buscarPreferenciasUsuarioConFiltro() {
		String comida1 = "";
		String comida2 = "";
		f2 = null;
		f4 = null;
		DBCollection col = this.promoActual;//pt.getCollection();//error
		ArrayList<String> comidas = new ArrayList<String>();
		if(lRecomendaciones != null){
			lRecomendaciones.clear();
		}
		
		// Declaramos el Iterador e imprimimos los Elementos del ArrayList de
		// Promocion
		Iterator<Promocion> nIterator = lPromciones.iterator();
		while (nIterator.hasNext()) {
			Promocion elemento = nIterator.next();

			for (int i = 0; i < this.customer.getListaPreferencias().size(); i++) {
				if (elemento
						.getProducto()
						.getDescripcion()
						.equals(this.customer.getListaPreferencias().get(i)
								.getDescripcion())) {
					comidas.add(elemento.getProducto().getNombre());
					System.out.println("Se encontró la preferencia nº: " + i);
					System.out.println("comida: "
							+ elemento.getProducto().getNombre());
					lRecomendaciones.add(elemento);//(promo);
				}
			}
		}
		try {
			comida1 = comidas.get(0);System.out.println("comida1: "+comida1);
			comida2 = comidas.get(1);System.out.println("comida2: "+comida2);
		} catch (Exception e) {
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
		if ((f2 != null) || (f4 != null)) {
			return true;
		}
		return false;
	}

	public DBObject getF2() {
		return f2;
	}

	public DBObject getF4() {
		return f4;
	}

	public ArrayList<Promocion> getlRecomendaciones() {
		return lRecomendaciones;
	}

}