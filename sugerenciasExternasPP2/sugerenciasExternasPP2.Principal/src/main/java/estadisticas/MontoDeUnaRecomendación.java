package estadisticas;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import modelo.Usuario;
import properties.Constants;

public class MontoDeUnaRecomendación {

	public MontoDeUnaRecomendación(){
		
	}
	
	public String getRecomendacion(Usuario usuario){
		String recomendacion="";
		Properties propiedades = new Properties();
		try {
			propiedades.load(new FileReader(
					Constants.ROUTE_USUARIOS_RECOMENDACIONES));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		//Recorro el Archivo Properties y busco al usuario
		Enumeration<Object> keys = propiedades.keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if (key.equals(usuario.getUsuario())) {
				recomendacion= propiedades.getProperty(usuario.getUsuario());
			}
		}
		return recomendacion;
		
	}
	
	public double getPrecioRecomendacion(String recomendacion){
		double doubleRes =0.0;
		String[] parts= recomendacion.split("}");
		if(recomendacion.length()==0){
			return 0.0;
		}
		if(parts.length>=1){
			String p1=parts[1];
			String[] parts1= p1.split(":");
			doubleRes = doubleRes +Double.parseDouble(parts1[2]);
		}
		
		if(parts.length>=3){
			String p2=parts[3];
			String[] parts2= p2.split(":");
			doubleRes = doubleRes +Double.parseDouble(parts2[2]);
		}
		
		if(parts.length>=5){
			String p3=parts[5];
			String[] parts3= p3.split(":");
			doubleRes = doubleRes +Double.parseDouble(parts3[2]);
			
		}
		
		if(parts.length>=7){
			String p4=parts[7];
			String[] parts4= p4.split(":");
			doubleRes = doubleRes +Double.parseDouble(parts4[2]);
		}
		return doubleRes;
		
	}
}
