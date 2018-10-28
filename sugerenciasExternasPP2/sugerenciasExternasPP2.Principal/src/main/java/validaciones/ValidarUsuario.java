package validaciones;

import java.util.Enumeration;

import bo.UsuariosBo;

public class ValidarUsuario {
	private static UsuariosBo userBo;
	public ValidarUsuario() {
		userBo= new UsuariosBo();
	}

	// valido si el usuario ya está registrado en la base
	public static boolean elUsuarioYaExiste(String nombreUsuario, String emailUsuario) {
		Enumeration<Object> keys = userBo.getUsuariosProperties().keys();

		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if ((key.equals(nombreUsuario))
					&& (userBo.getUsuariosProperties().get(key).equals(emailUsuario))) {
				return true;
			}
		}
		return false;
	}

	// busco y levanto la base de datos de MongoDB con los CustomerDTO o Usuario
	// valido usuario válido en su formato (mínimo 5 caracteres y máximo 20
	// caracteres
	// si no está registrado lo agrego a la base de datos de usuarios

	public static void main(String[] args) {
		System.out.println(elUsuarioYaExiste("a", "a@yahoo.com.ar"));

	}

}
