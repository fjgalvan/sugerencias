package iteracion2_CriteriosDeAceptaion;

import static org.junit.Assert.assertEquals;
import modelo.Usuario;

import org.junit.Before;
import org.junit.Test;

import estadisticas.Money;
import estadisticas.MoneyCalculation;
import estadisticas.MontoDeUnaRecomendación;

public class Us1_It2Test {
//	Si un usuario Javier elije chatarras y pastas , entonces el gasto es de $80.0.
	Double preciou2=0.0;
	Double preciou1=0.0;
	@Test
	public void test1(){
		System.out.println("Test 1!");
		MontoDeUnaRecomendación m= new MontoDeUnaRecomendación();
		Usuario u2= new Usuario("javier","javier@yahoo.com.ar");
		String res="";
		res=m.getRecomendacion(u2);
		System.out.println("res: "+res);
		preciou2=m.getPrecioRecomendacion(res);
		assertEquals("80.0", String.valueOf(preciou2));
	}
	
//	Si un usuario Fernando elije chatarras, postres, sanas y pastas , entonces el gasto es de $140.0.
	@Test
	public void test2(){
		System.out.println("Test 2!");
		MontoDeUnaRecomendación m= new MontoDeUnaRecomendación();
		Usuario u= new Usuario("fernando","fernando@yahoo.com.ar");
		String res="";
		res=m.getRecomendacion(u);
		System.out.println("res: "+res);
		m.getPrecioRecomendacion(res);
		preciou1=m.getPrecioRecomendacion(res);
		assertEquals("140.0", String.valueOf(preciou1));
	}
	
//	Si se comparan los montos de gastos de Javier y Fernando, entonces Fernando gastaría más.
	@Test
	public void test3(){
		System.out.println("Test 3!");
		Money m= new Money(80.0, Money.getCurrencyArgentina());
		Money m2= new Money(140.0, Money.getCurrencyArgentina());
		MoneyCalculation mc= new MoneyCalculation(m.getAmount(), m2.getAmount());
		mc.doCalculations();
		assertEquals("60.00", mc.getDifference().toString());
	}
//	Si no se eligen ninguna comida, entonces no se trae ninguna comida.
	@Test
	public void test4(){
		System.out.println("Test 4!");
		MontoDeUnaRecomendación m= new MontoDeUnaRecomendación();
		Usuario u= new Usuario("sinPreferencias","sinPreferencias@yahoo.com.ar");
		String res="";
		res=m.getRecomendacion(u);
		System.out.println("res: "+res);
		m.getPrecioRecomendacion(res);
		preciou1=m.getPrecioRecomendacion(res);
		assertEquals("0.0", String.valueOf(preciou1));
	}
}
