package bo;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import properties.Constants;
import validaciones.ValidarEmail;
import modelo.Preferencias;
import modelo.Producto;
import modelo.Usuario;

public class UsuariosBo {
	private List<Usuario> listaUsuarios;
	private Properties usuariosProperties;

	public UsuariosBo() {
		listaUsuarios = new ArrayList<Usuario>();
		usuariosProperties = new Properties();
		try {
			usuariosProperties.load(new FileReader(Constants.ROUTE_USUARIOS));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> getListaDeUsuarios() {

		Enumeration<Object> keys = usuariosProperties.keys();

		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			System.out.println(key + " = " + usuariosProperties.get(key));
			String[] parts = usuariosProperties.getProperty(key.toString())
					.split(",");
			Usuario user = new Usuario(key.toString(), parts[0]);
			listaUsuarios.add(user);
		}
		return listaUsuarios;
	}

	public void mostrarListaDeUsuarios() {
		for (Usuario user : listaUsuarios) {
			System.out.println(user.getUsuario());
			System.out.println(user.geteMail() + "\n");

		}
	}

	public void leerTodas() {
		Enumeration<Object> keys = usuariosProperties.keys();

		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			System.out.println(key + " = " + usuariosProperties.get(key));
		}

	}

	public boolean elUsuarioYaExiste(String nombreUsuario, String emailUsuario) {
		Enumeration<Object> keys = usuariosProperties.keys();

		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if ((key.equals(nombreUsuario))
					&& (usuariosProperties.get(key).equals(emailUsuario))) {
				return true;
			}
		}
		return false;
	}

	// Comprobar si el String cadena está formado por un mínimo de 5
	// letras mayúsculas o minúsculas o numeros y un máximo de 20.
	public boolean caracteresValidosUsuario(String nombreUsuario) {
		Pattern pat = Pattern.compile("[a-zA-Z0-9]{5,20}");
		Matcher mat = pat.matcher(nombreUsuario);
		if (mat.matches()) {
			System.out.println("SI");
			return true;
		} else {
			System.out.println("NO");
			return false;
		}
	}

	public boolean validarEmail(String emailUsuario) {
		boolean res = false;
		ValidarEmail ve = new ValidarEmail();
		res = ve.isEmail(emailUsuario);
		return res;
	}

	public void agregarNuevoUsuario(String nombreUsuario, String email) throws IOException{
		if((!elUsuarioYaExiste(nombreUsuario, email)) 
			&& (caracteresValidosUsuario(nombreUsuario))
			&& (validarEmail(email))){
			usuariosProperties.setProperty(nombreUsuario, email);
			FileOutputStream os = null;	   
		    
			try {
			  os=new FileOutputStream(Constants.ROUTE_USUARIOS);//("fichero.prop");	
			  usuariosProperties.store(os, "Fichero de Propiedades de Usuarios!");
			} catch(IOException ioe) {ioe.printStackTrace();}
		}
	}

	public Properties getUsuariosProperties() {
		return usuariosProperties;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public static void main(String[] args) throws IOException {
		UsuariosBo user = new UsuariosBo();
		
		 user.agregarNuevoUsuario("pablo","pablo@yahoo.com.ar");
		 user.getListaDeUsuarios();
	}
}