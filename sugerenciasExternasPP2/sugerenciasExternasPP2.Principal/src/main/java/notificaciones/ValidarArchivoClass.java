package notificaciones;

public class ValidarArchivoClass {

	public ValidarArchivoClass(){
	}

	public boolean isInstanceOfInterfazNotificacion(Object obj, String clase) throws ClassNotFoundException {
		boolean res = false;
		if (Class.forName(clase).isInstance(obj)) {
			// myObj is an instance of the class as specified by myString.
			System.out.println("La Clase is an instance of the class as specified by: InterfazNotificacion");
			res = true;
		} else {
			System.out.println("No es una InstanceOf !!");
		}
		return res;
	}
}
