package listeners;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.swing.Timer;

import mvc.Vista2;
import mvc_modelo_observable.Modelo;
import conexiones.Interfaz.RecolectorPromos;
import dao.mongoDB.MongoConcrete;
import dao.mongoDB.MongoConcreteStub;
import twitter4j.TwitterException;

public class ActualizacionPromos {
	private Timer tm;
	//private MongoConcreteStub basePromosActual;
	private MongoConcrete basePromosActual;
	private Modelo m;
	private String fechaActualizacion = "";
	private String nombreColl;

	public ActualizacionPromos(Modelo m) { 
		this.m = m;
		nombreColl=m.getMongo().getMyConstants_DB_NAME();
	}

//	public MongoConcreteStub actualizarPromos(Integer tiempoDeActualizacion,
//			final Vista2 v) {
	public MongoConcrete actualizarPromos(Integer tiempoDeActualizacion,
			final Vista2 v) {
		tm = new Timer(tiempoDeActualizacion, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
//					if(basePromosActual !=null){
//						basePromosActual.finish();
//					}
					//System.out.println("m.getMongo().getMyConstants_DB_NAME(): "+nombreColl);
					m.ConectarMongoDBreal(nombreColl);// VEEEERR
					m.cargarRecomendacionesGenerales(m.getMapRecomendaciones());
					basePromosActual = cargarTodasLasPromos();
					fechaActualizacion = mostrarFecha_Horario_deActualizacion();
					m.setHoraActualizacion(fechaActualizacion);
					v.getTextArea_fechaActualizacion().setText(
							fechaActualizacion);// se modifica el modelo,
												// entonces actúa el Observer
					m.setMongo(basePromosActual);// Le paso la nueva base
				} catch (ClassNotFoundException | NoSuchMethodException
						| SecurityException | InstantiationException
						| IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		return basePromosActual;
	}

	public String runTimer() {
		String fechaYhoraDeActualizacion = "";
		// Funciona con Timer
		try {
			tm.start(); // COMIENZA A CARGAR TODAS LAS PROMOS CADA CIERTO TIEMPO
			m.setMongo(basePromosActual);
			m.cargarRecomendacionesGenerales(m.getMapRecomendaciones());
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | TwitterException e1) {
			e1.printStackTrace();
		}
		return fechaYhoraDeActualizacion;
	}

	public void stopTimer() {
		tm.stop(); // DENTIENE LA BUSQUEDA DE CARGAS Y PROMOS
	}

	//public MongoConcreteStub cargarTodasLasPromos()
	public MongoConcrete cargarTodasLasPromos()
			throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			TwitterException {
		//RecolectorPromos c = new RecolectorPromos(); //sin argumento
		RecolectorPromos c = new RecolectorPromos(this.m.getMongo());
		
		c.cargarListaConectores();
		c.buscarPromociones();
//		System.out.println("c.getMongoDB().getPromos().count(): "
//				+ c.getMongoDB().getPromos().count());
		c.getMongoDB().leerColeccion();

		return c.getMongoDB();
	}

	public String mostrarFecha_Horario_deActualizacion() {
		Date fecha = new Date();
		//System.out.println(fecha.toString());
		return fecha.toString();
	}

	public Timer getTm() {
		return tm;
	}

	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

}
