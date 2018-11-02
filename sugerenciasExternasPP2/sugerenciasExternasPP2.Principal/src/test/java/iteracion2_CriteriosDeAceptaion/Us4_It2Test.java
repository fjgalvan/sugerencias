package iteracion2_CriteriosDeAceptaion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import validaciones.ValidarEmail;
import validaciones.ValidarUsuario;
import bo.UsuariosBo;

public class Us4_It2Test {
	UsuariosBo uBo;
	ValidarEmail ve;
	ValidarUsuario vu;
	
	@Before
	public void init(){
		uBo= new UsuariosBo();
		ve= new ValidarEmail();
		vu= new ValidarUsuario();
	}
	//Si se ingresa un usuario correcto “federico” , entonces el formatos es correcto, devuelve “true”
	@Test
	public void test1(){
		System.out.println("Test 1!");
		boolean res=false;
		res= uBo.caracteresValidosUsuario("federico");
		
		assertTrue(res);
	}
	//Si se ingresa un usuario incorrecto “die” , entonces no cumple con un formato de mínimo 5 caracteres. Devuelve “false”.
	@Test
	public void test2(){
		System.out.println("Test 2!");
		boolean res=true;
		res= uBo.caracteresValidosUsuario("die");
		
		assertFalse(res);
	}
	//Si se ingresa un email correcto federico@yahoo.com.ar , entonces al cumplir con el formato, devuelve “true”
	@Test
	public void test3(){
		System.out.println("Test 3!");
		boolean res=false;
		res= ve.isEmail("federico@yahoo.com.ar");
		
		assertTrue(res);
	}
	//Si se ingresa un email incorrecto federico.yahoo.com.ar, entonces al no cumplir con el formato de que debe de haber un @ devuelve “false”.
	@Test
	public void test4(){
		System.out.println("Test 4!");
		boolean res=true;
		res= ve.isEmail("federico.yahoo.com.ar");
		
		assertFalse(res);
	}
	//Si se ingresa un usuario ya existente, no lo guarda
	@Test
	public void test5(){
		System.out.println("Test 5!");
		boolean res=false;
		res= vu.elUsuarioYaExiste("fernando", "fernando@yahoo.com.ar");
		
		assertTrue(res);
	}
	
}
