package modelo;

import java.util.ArrayList;

public class Customer {
	private final String id;
	private final Usuario userName;
	private ArrayList<Preferencias> listaPreferencias;

	public Customer(String id, Usuario userName,
			ArrayList<Preferencias> listaPreferencias) {
		this.id = id;
		this.userName = userName;
		this.listaPreferencias = new ArrayList<Preferencias>();
		this.listaPreferencias = listaPreferencias;
	}

	public String getId() {
		return id;
	}

	public Usuario getUserName() {
		return userName;
	}

	public ArrayList<Preferencias> getListaPreferencias() {
		return listaPreferencias;
	}

}
