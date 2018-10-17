package sugerencias;

import java.util.HashMap;  

import promo.Interfaz.InterfacePromo;
import util.Date;

public class Sugerencias {
	private String local;
	private String ubicacion;
	private Date fechaDeVigencia;
	private HashMap<String,Double> lComidas = new HashMap<String,Double>();//(producto, precio)
	private String producto;
	private Double precio;
	
	
	public Sugerencias(String local, String ubicacion, HashMap<String,Double> listaProductosPrecios, Date fechaDeVigencia) 
    {
        this.local= local;
        this.ubicacion= ubicacion;
	    this.lComidas= listaProductosPrecios;
        this.fechaDeVigencia= fechaDeVigencia;
	}
	public Sugerencias(String local, String ubicacion, String producto,Double precio, Date fechaDeVigencia) 
    {
        this.local= local;
        this.ubicacion= ubicacion;
	    this.producto= producto;
        this.precio= precio;
        this.fechaDeVigencia= fechaDeVigencia;
    }
	
	public Sugerencias(String local2, String ubicacion2, Object producto, Object precio, Date fechaDeVigencia2) {
		this.local= local2;
        this.ubicacion= ubicacion2;
	    this.producto= producto.toString();
        this.precio= Double.parseDouble(precio.toString());
        this.fechaDeVigencia= fechaDeVigencia2;
	}
	
	public void leerSugerencias(){
	}
	

	//GETs & SETs
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Date getFechaDeVigencia() {
		return fechaDeVigencia;
	}
	public void setFechaDeVigencia(Date fechaDeVigencia) {
		this.fechaDeVigencia = fechaDeVigencia;
	}
	public HashMap<String, Double> getListaProductosPrecios() {
		return lComidas;
	}
	public void setlistaProductosPrecios(HashMap<String, Double> listaProductosPrecios) {
		this.lComidas = listaProductosPrecios;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}	
	
}
