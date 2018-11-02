package iteracion2_CriteriosDeAceptaion;

import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import properties.Constants;

public class Us3_It2Test {
	Properties propiedadesEspanol;
	Properties propiedadesIngles;
	
	@Before
	public void init(){
		propiedadesEspanol = new Properties();
		try {
			propiedadesEspanol.load(new FileReader(
					Constants.ROUTE_ESPANOL));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		propiedadesIngles = new Properties();
		try {
			propiedadesIngles.load(new FileReader(
					Constants.ROUTE_INGLES));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	//Si se elige la opción de idioma español, entonces el TAG de comida chatarras es “chatarras”. 
	@Test
	public void test1(){
		System.out.println("Test 1!");
		String res="";
		Enumeration<Object> keys = propiedadesEspanol.keys();

		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if (key.equals("filtroChatarras")) {
				res=propiedadesEspanol.get("filtroChatarras").toString();
			}
		}
		System.out.println("res: "+res);
		assertEquals(res, "1_Chatarras");
	}
	
	//Si se elige la opción de idioma inglés, entonces el TAG de comida chatarras es “scrap”. 
	@Test
	public void test2(){
		System.out.println("Test 2!");
		String res="";
		Enumeration<Object> keys = propiedadesIngles.keys();

		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if (key.equals("filtroChatarras")) {
				res=propiedadesIngles.get("filtroChatarras").toString();
			}
		}
		System.out.println("res: "+res);
		assertEquals(res, "1_Scrap");
	}
	
	//Si se tiene la opción de ingresar bebidas en español, entonces queda “ ” 
	@Test
	public void test3(){
		System.out.println("Test 3!");
		String res="";
		Enumeration<Object> keys = propiedadesEspanol.keys();

		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if (key.equals("filtroBebidas")) {
				res=propiedadesEspanol.get("filtroBebidas").toString();
			}
		}
		System.out.println("res: "+res);
		assertEquals(res, "");
	}
	
}
