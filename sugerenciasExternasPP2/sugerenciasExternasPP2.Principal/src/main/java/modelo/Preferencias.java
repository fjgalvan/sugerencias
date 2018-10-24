package modelo;

import java.util.List;

public class Preferencias { 
	//Con abstract no se puede hacer un new, si lo hago dinámico no se usa abstract
	//Se puede usar 2 properties (1_preferenciaComida) (2_codigo_descripciones)
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
	public static void main(String args[]){
//		String string = "004-034556-123";
//		String[] parts = string.split("-");
//		for(int i=0; i<parts.length; i++){
//			System.out.println(parts[i]);
//		}
//		
	}
}
