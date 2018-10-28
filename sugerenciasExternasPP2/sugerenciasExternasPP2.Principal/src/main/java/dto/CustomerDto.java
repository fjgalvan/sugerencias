package dto;

import modelo.Preferencias;
import modelo.Usuario;

/**
 * {@link CustomerDto} is a data transfer object POJO. Instead of sending individual information to client
 * We can send related information together in POJO.
 * <p>
 * Dto will not have any business logic in it.
 */
public class CustomerDto {
  private final String id;
  private final Usuario userName;
  private final Preferencias preferencias;
  private final Preferencias preferencias2;

  public CustomerDto(String id, Usuario userName, Preferencias preferencias,Preferencias preferencias2) {
    this.id = id;
    this.userName = userName;
    this.preferencias= preferencias; 
    this.preferencias2=preferencias2;
  }

  public Preferencias getPreferencias2() {
	return preferencias2;
  }

  public String getId() {
	  return id;
  }

  public Usuario getUserName() {
	return userName;
  }

  public Preferencias getPreferencias() {
	return preferencias;
  }

}
