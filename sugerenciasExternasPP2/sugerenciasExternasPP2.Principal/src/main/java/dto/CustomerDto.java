package dto;

/**
 * {@link CustomerDto} is a data transfer object POJO. Instead of sending individual information to client
 * We can send related information together in POJO.
 * <p>
 * Dto will not have any business logic in it.
 */
public class CustomerDto {
  private final String id;
  private final String userName;
  private final String tipoComida1;
  private final String tipoComida2;

  /**
   * @param id        customer id
   * @param firstName customer first name
   * @param lastName  customer last name
   */
  public CustomerDto(String id, String userName, String tipoComida1, String tipoComida2) {
    this.id = id;
    this.userName = userName;
    this.tipoComida1 = tipoComida1;
    this.tipoComida2= tipoComida2;
  }

  public String getId() {
	  return id;
  }

  public String getUserName() {
	return userName;
  }

  public String getTipoComida1() {
	return tipoComida1;
  }

  public String getTipoComida2() {
	return tipoComida2;
  }
}
