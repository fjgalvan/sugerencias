package bo;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import properties.Constants;
import modelo.Producto;

public class ProductosBo {
	private List<Producto> listaProducto= new ArrayList<Producto>(); //se cargan todos los productos que hay
	//cuando tenga todas las promociones cargarlo junto con los properties de los productos que yo manejo
	public ProductosBo(){
		
	}
	
	public List<Producto> getListaDeProductos(){//Producto(Integer codigo,String nombre, String descripcion)
		
		Properties p1 = new Properties();
		try { p1.load(new FileReader(Constants.ROUTE_PRODUCTOS));
		} catch (IOException e) { e.printStackTrace();}
		
		int i=0;
		for (Enumeration e = p1.keys(); e.hasMoreElements() ; ) {
		    // Obtenemos el objeto
		    Object obj = e.nextElement();
		    //System.out.println(obj + ": " + p1.getProperty(obj.toString()));
		    
		    String[] parts = p1.getProperty(obj.toString()).split("-");
			Producto prod= new Producto(i, parts[0], parts[1]);
			listaProducto.add(prod);
			i++;
		}
		
		return listaProducto;
	}

	public void mostrarListaDeProductos() {
		for (Producto prod: listaProducto) {
//		    System.out.println(prod.getCodigo());
//		    System.out.println(prod.getNombre());
//		    System.out.println(prod.getDescripcion()+"\n");
		}
		
	}
	
}
