package iteracion2_CriteriosDeAceptaion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import modelo.Usuario;

import org.junit.Test;

import estadisticas.MontoDeUnaRecomendación;

public class Us2_It2Test {

	//Si elige la opción de comidas de javier , entonces trae todas las comidas chatarras y pastas .
	@Test
	public void test1(){
		System.out.println("Test 1!");
		
		MontoDeUnaRecomendación m= new MontoDeUnaRecomendación();
		Usuario u2= new Usuario("javier","javier@yahoo.com.ar");
		String res="";
		res=m.getRecomendacion(u2);
		System.out.println("res: "+res);
		assertTrue(res.contains("hamburguesa"));
		assertTrue(res.contains("fideos"));
		assertFalse(res.contains("helado"));
		assertFalse(res.contains("ensalada"));
	}
	
	//Si elige la opción de comidas de sinPreferencias, entonces no se trae ninguna comida
	@Test
	public void test2(){
		System.out.println("Test 2!");
		MontoDeUnaRecomendación m= new MontoDeUnaRecomendación();
		Usuario u2= new Usuario("sinPreferencias","sinPreferencias@yahoo.com.ar");
		String res="";
		res=m.getRecomendacion(u2);
		System.out.println("res: "+res);
		assertFalse(res.contains("hamburguesa"));
		assertFalse(res.contains("fideos"));
		assertFalse(res.contains("helado"));
		assertFalse(res.contains("ensalada"));
	}
	
	//Si elige la opción de comidas de fernando, entonces trae todas las comidas chatarras,postres,sanas y pastas
	@Test
	public void test3(){
		System.out.println("Test 3!");
		MontoDeUnaRecomendación m= new MontoDeUnaRecomendación();
		Usuario u2= new Usuario("fernando","fernando@yahoo.com.ar");
		String res="";
		res=m.getRecomendacion(u2);
		System.out.println("res: "+res);
		assertTrue(res.contains("hamburguesa"));
		assertTrue(res.contains("fideos"));
		assertTrue(res.contains("helado"));
		assertTrue(res.contains("ensalada"));
	}
}
