package modelo;

import java.util.ArrayList;

public class Usuario {
	private String usuario;
	private String eMail;
	private ArrayList<Preferencias> preferencias= new ArrayList<Preferencias>();;
	
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


	public ArrayList<Preferencias> getPreferencias() {
		return preferencias;
	}


	public void setPreferencias(ArrayList<Preferencias> preferencias) {
		this.preferencias = preferencias;
	}

	
}
