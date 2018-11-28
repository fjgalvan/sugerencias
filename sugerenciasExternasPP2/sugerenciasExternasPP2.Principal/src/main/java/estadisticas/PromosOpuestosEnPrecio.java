package estadisticas;

import sugerencias.Sugerencias;
import util_.Date;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import configurables.MyConstantsEstadisticas;

public class PromosOpuestosEnPrecio {

	public PromosOpuestosEnPrecio() {

	}

	public String getPromoMasCara(DBCollection promos) {
		Double elMasCaro = 0.0;
		Sugerencias promoMasCara = null;
		String promoString = "";
		System.out.println("promos estadisticas size: " + promos.count());

		DBCursor cursor = promos.find();
		try {
			//cursor.next();
			
			while (cursor.hasNext()) {
				
				DBObject doc = cursor.next();
				String local = (String) doc.get(MyConstantsEstadisticas.promoLocal);
				String ubicacion = (String) doc.get(MyConstantsEstadisticas.promoUbicacion);
				String producto = (String) doc.get(MyConstantsEstadisticas.promoProducto);
				Double preciod = (Double) doc.get(MyConstantsEstadisticas.promoPrecio);
				String d= doc.get("fechaDeVigencia").toString();
				
				String[] parts = d.split(":");
				String part1 = parts[1]; 
				String[] parts1 = part1.split(",");
				String ddString= ""+parts1[0].charAt(1)+parts1[0].charAt(2);
				int dd = Integer.parseInt(ddString);

				String part2 = parts[2]; 
				String[] parts2 = part2.split(",");
				int mm = Integer.parseInt(""+parts2[0].charAt(1)+parts2[0].charAt(2));
				
				String part3 = parts[3]; 
				String[] parts3 = part3.split("}");
				int aaaa = Integer.parseInt(""+parts3[0].charAt(1)+parts3[0].charAt(2)+parts3[0].charAt(3)+parts3[0].charAt(4));
				
				Date fechaDeVigencia= new Date(dd,mm,aaaa);

				System.out.println("precio double: " + preciod);

				if (preciod >= elMasCaro) {
					elMasCaro = preciod;
					promoMasCara = new Sugerencias(local, ubicacion, producto,
							preciod, fechaDeVigencia);
					promoString = promoString(promoMasCara);
				}
			}
		} finally {
			cursor.close();
		}

		return promoString;

	}

	public String getPromoMasEconomica(DBCollection promos) {

		Double elMasEconomico = 1000.0;
		Sugerencias promoMasEconomico = null;
		String promoString = "";
		DBCursor cursor = promos.find();
		try {
			//cursor.next();
			
			while (cursor.hasNext()) {
				
				DBObject doc = cursor.next();
				String local = (String) doc.get(MyConstantsEstadisticas.promoLocal);
				String ubicacion = (String) doc.get(MyConstantsEstadisticas.promoUbicacion);
				String producto = (String) doc.get(MyConstantsEstadisticas.promoProducto);
				Double preciod = (Double) doc.get(MyConstantsEstadisticas.promoPrecio);
				String d= doc.get("fechaDeVigencia").toString();
				
				String[] parts = d.split(":");
				String part1 = parts[1];
				String[] parts1 = part1.split(",");
				String ddString= ""+parts1[0].charAt(1)+parts1[0].charAt(2);
				int dd = Integer.parseInt(ddString);

				String part2 = parts[2]; 
				String[] parts2 = part2.split(",");
				int mm = Integer.parseInt(""+parts2[0].charAt(1)+parts2[0].charAt(2));
				
				String part3 = parts[3];
				String[] parts3 = part3.split("}");
				int aaaa = Integer.parseInt(""+parts3[0].charAt(1)+parts3[0].charAt(2)+parts3[0].charAt(3)+parts3[0].charAt(4));
				
				Date fechaDeVigencia= new Date(dd,mm,aaaa);

				if ((preciod) <= (elMasEconomico)) {
					elMasEconomico = preciod;
					promoMasEconomico = new Sugerencias(local, ubicacion,
							producto, preciod, fechaDeVigencia);
					promoString = promoString(promoMasEconomico);
				}
			}
		} finally {
			cursor.close();
		}

		return promoString;

	}

	public String promoString(Sugerencias promo) {
		String promoS = promo.getLocal().toString() + " - "
				+ promo.getUbicacion().toString() + " - "
				+ promo.getProducto().toString() + " - "
				+ String.valueOf(promo.getPrecio()) + " - "
				+ promo.getFechaDeVigencia().getDate();

		return promoS;

	}
}
