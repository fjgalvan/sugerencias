package modelo;

import java.util.ArrayList;

/**
 * {@link Customer} is a data transfer object POJO. Instead of sending
 * individual information to client We can send related information together in
 * POJO.
 * <p>
 * Dto will not have any business logic in it.
 */
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
