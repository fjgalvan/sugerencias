package modelo;

public class Producto {
	private String nombre;
	private String descripcion;
	private Integer codigo;
	
	public Producto(Integer codigo,String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigo = codigo;
	}
	
	public void mostrarProducto(){
		System.out.println("nombre:"+nombre+"\ndescripcion:"+descripcion+"\ncodigo:"+codigo);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}	
}
