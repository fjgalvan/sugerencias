package bo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import modelo.Customer;
import modelo.Preferencias;
import modelo.Usuario;

import org.junit.Test;

public class BO_Test {
	@Test
    public void customerBoTest(){
		CustomersBo cBo= new CustomersBo();
		cBo.getListaCustomers();
		cBo.mostrarListaDeCustomers();
		cBo.getListaDeCustomers();
		Usuario u= new Usuario("u","usuario@yahoo.com.ar");
		ArrayList<Preferencias> listaPreferencias= new ArrayList<Preferencias>();
		Preferencias p1 = new Preferencias(1,"chatarras");
		Preferencias p2 = new Preferencias(2,"postres");
		listaPreferencias.add(p1);
		listaPreferencias.add(p2);
		Customer c1= new Customer("10", u,listaPreferencias);
		cBo.agregarCostumer(c1);
	}
	
	@Test
    public void productosBoTest(){
		ProductosBo pBo= new ProductosBo();
		pBo.getListaDeProductos();
		pBo.mostrarListaDeProductos();
	}
	
	@Test
    public void usuariosBoTest() throws IOException{
		UsuariosBo uBo= new UsuariosBo();
		uBo.getListaDeUsuarios();
		uBo.mostrarListaDeUsuarios();
		uBo.leerTodas();
		uBo.elUsuarioYaExiste("javier", "javier@yahoo.com.ar");
		assertTrue(uBo.caracteresValidosUsuario("javier"));
		assertTrue(uBo.validarEmail("javier@yahoo.com.ar"));
		assertFalse(uBo.agregarNuevoUsuario("peter2", "peter2@yahoo.com.ar"));
		uBo.getUsuariosProperties();
		uBo.getUsuariosPreferenciasProperties();
		uBo.getListaUsuarios();
	}
	
}
