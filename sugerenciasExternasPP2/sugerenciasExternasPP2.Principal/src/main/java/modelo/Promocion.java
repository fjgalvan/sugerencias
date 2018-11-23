package modelo;

import util_.Date;


public class Promocion {
	String nombreLocal;
	String ubicacion;
	Date fechaVigencia;
	Producto Producto;
	Double precio;

	public Promocion(String nombreLocal, String ubicacion, Producto producto,
			Double precio, Date fechaVigencia) {
		super();
		this.nombreLocal = nombreLocal;
		this.ubicacion = ubicacion;
		this.Producto = producto;
		this.precio = precio;
		this.fechaVigencia = fechaVigencia;
	}
	
	public void mostrarPromo(){
		System.out.println("nombreLocal:" +nombreLocal+"\nubicacion:"+ubicacion+
				"\nProducto:"+Producto.getNombre()+Producto.getDescripcion()+"\n"+Producto.getCodigo()
				+"\nprecio:"+precio+"fechaVigencia:"+fechaVigencia);
	}
	public String getNombreLocal() {
		return nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public Date getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Producto getProducto() {
		return Producto;
	}

	public void setProducto(Producto producto) {
		Producto = producto;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
