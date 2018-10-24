package modelo;

public class Usuario {
	private String usuario;
	private String eMail;
	private String preferencias;
	
	public Usuario(String usuario, String eMail) {
		super();
		this.usuario = usuario;
		this.eMail = eMail;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String geteMail() {
		return eMail;
	}


	public void seteMail(String eMail) {
		this.eMail = eMail;
	}


	public String getPreferencias() {
		return preferencias;
	}


	public void setPreferencias(String preferencias) {
		this.preferencias = preferencias;
	}

	
}
