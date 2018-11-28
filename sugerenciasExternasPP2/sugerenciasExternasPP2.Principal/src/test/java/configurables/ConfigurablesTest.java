package configurables;

import org.junit.Test;

public class ConfigurablesTest {
	@Test
    public void constantsTest(){
		String a=MyConstantsDAO.localburguerKing;
		Integer b=MyConstantsDAO.tiempoUnSegeundo;
		Integer c=MyConstantsDAO.tiempoDosMinuto;
		Integer d=MyConstantsDAO.tiempoUnMinuto;
		Integer e=MyConstantsDAO.tiempoUnaHora;

		String f=MyConstantsConexiones.hojaExcelXlsx;
		String g=MyConstantsConexiones.nombreArchivoExcelXlsx;
		String h=MyConstantsConexiones.rutaArchivoExcelXlsx;
		String i=MyConstantsConexiones.promoLocal;
		String j=MyConstantsConexiones.promoUbicacion;
		String k=MyConstantsConexiones.promoPrecio;
		String l=MyConstantsConexiones.promoFechaDeVigencia;
		String m=MyConstantsConexiones.promolComidas;
		String n=MyConstantsConexiones.tweet;
		String o=MyConstantsConexiones.conexionExternaTwitter;
		String p=MyConstantsConexiones.tweetStub1;
		String q=MyConstantsConexiones.tweetStub2;
		
	}
	@Test
    public void constants2Test(){
		String a=MyConstantsModelo.promoLocal;
		String b=MyConstantsModelo.promoUbicacion;
		String c=MyConstantsModelo.promoProducto;
		String d=MyConstantsModelo.promoFechaDeVigencia;
		String e=MyConstantsModelo.promoPrecio;
		
		String f=MyConstantsBo.tagChatarras;
		String g=MyConstantsBo.tagPostres;
		String h=MyConstantsBo.tagSanas;
		String i=MyConstantsBo.tagPastas;
	}
}
