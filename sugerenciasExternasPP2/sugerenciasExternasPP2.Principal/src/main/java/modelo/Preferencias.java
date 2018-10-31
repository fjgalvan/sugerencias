package modelo;

import java.util.List;

public class Preferencias { 
	//Preferecia 1: "chatarras", 2:"postres", 3:"sanas", 4:"pastas"
	private Integer codigo;
	private String descripcion;
	private List<Producto> productos;

	public Preferencias(Integer codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public boolean pertenece(Producto p) {

		if (productos.contains(p)) {
			return true;
		}
		return false;

	}
}
