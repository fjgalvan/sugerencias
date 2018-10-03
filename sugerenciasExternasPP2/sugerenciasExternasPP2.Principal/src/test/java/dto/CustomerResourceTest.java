package dto;




import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * tests {@link CustomerResource}.
 */
public class CustomerResourceTest {
  @Test
  public void shouldGetAllCustomers() {
    CustomerDto customer = new CustomerDto("1", "usuarioA", "chatarras", "postres");
    List<CustomerDto> customers = new ArrayList<>();
    customers.add(customer);

    CustomerResource customerResource = new CustomerResource(customers);

    List<CustomerDto> allCustomers = customerResource.getAllCustomers();

    assertEquals(allCustomers.size(), 1);
    assertEquals(allCustomers.get(0).getId(), "1");
    assertEquals(allCustomers.get(0).getUserName(), "usuarioA");
    assertEquals(allCustomers.get(0).getTipoComida1(), "chatarras");
    assertEquals(allCustomers.get(0).getTipoComida2(), "postres");
  }

  @Test
  public void shouldSaveCustomer() {
    CustomerDto customer = new CustomerDto("1", "usuarioA", "chatarras", "postres");
    CustomerResource customerResource = new CustomerResource(new ArrayList<>());

    customerResource.save(customer);

    List<CustomerDto> allCustomers = customerResource.getAllCustomers();
    assertEquals(allCustomers.get(0).getId(), "1");
    assertEquals(allCustomers.get(0).getUserName(), "usuarioA");
    assertEquals(allCustomers.get(0).getTipoComida1(), "chatarras");
    assertEquals(allCustomers.get(0).getTipoComida2(), "postres");
  }

  @Test
  public void shouldDeleteCustomer() {
    CustomerDto customer = new CustomerDto("1", "usuarioA", "chatarras", "postres");
    List<CustomerDto> customers = new ArrayList<>();
    customers.add(customer);

    CustomerResource customerResource = new CustomerResource(customers);

    customerResource.delete(customer.getId());

    List<CustomerDto> allCustomers = customerResource.getAllCustomers();
    assertEquals(allCustomers.size(), 0);
  }

}
