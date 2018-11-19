package estadisticas;

import sugerencias.Sugerencias;
import util_.Date;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

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
			cursor.next();
			while (cursor.hasNext()) {

				DBObject doc = cursor.next();
				String local = (String) doc.get("local");
				String ubicacion = (String) doc.get("ubicacion");
				String producto = (String) doc.get("producto");
				Double preciod = (Double) doc.get("precio");
				//String precio = String.valueOf(preciod);
				// Date fechaDeVigenciaD=(Date)
				// doc.get("dd");//("fechaDeVigencia");
				// System.out.println("dd: "+ fechaDeVigenciaD);
				Date fechaDeVigencia = new Date(31, 12, 2018);

				System.out.println("precio double: " + preciod);

				if (preciod > elMasCaro) {
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
			cursor.next();
			while (cursor.hasNext()) {

				DBObject doc = cursor.next();
				String local = (String) doc.get("local");
				String ubicacion = (String) doc.get("ubicacion");
				String producto = (String) doc.get("producto");
				Double preciod = (Double) doc.get("precio");
				//String precio = String.valueOf(preciod);
				// Date fechaDeVigenciaD=(Date)
				// doc.get("dd");//("fechaDeVigencia");
				// System.out.println("dd: "+ fechaDeVigenciaD);
				Date fechaDeVigencia = new Date(31, 12, 2018);

				if (preciod < elMasEconomico) {
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
