package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JOptionPane;

import notificaciones.BuscarArchivos;
import notificaciones.CargarArchivoClass;
import notificaciones.CargarLibreriasExternas;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import dao.mongoDB.MongoConcrete;
import dao.mongoDB.MongoConcreteStub;
import mvc.Vista2;
import mvc_modelo_observable.Modelo;
import properties.Constants;

public class GuardarPreferenciaListener implements ActionListener {
	Modelo m;
	Vista2 v;
	Properties propiedades;

	public GuardarPreferenciaListener(Modelo m, Vista2 v) {
		this.m = m;
		this.v = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		v.getBtn_GuardarPreferencia().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Item ="";
				Item = v.getComboBox_eleccionDeUsuario().getSelectedItem().toString();
				String filtrosUsuario = "";
				if (v.getChckbx_filtrosChatarras().isSelected()) {
					filtrosUsuario = filtrosUsuario + "chatarras,";
				}
				if (v.getChckbx_filtrosPostres().isSelected()) {
					filtrosUsuario = filtrosUsuario + "postres,";
				}
				if (v.getChckbx_filtrosSanas().isSelected()) {
					filtrosUsuario = filtrosUsuario + "sanas,";
				}
				if (v.getChckbx_filtrosPastas().isSelected()) {
					filtrosUsuario = filtrosUsuario + "pastas";
				}

				propiedades = new Properties();
				try {
					propiedades.load(new FileReader(
							Constants.ROUTE_USUARIOS_PREFERENCIAS));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				System.out.println("Item: " + Item);
				System.out.println("filtrosUsuario: " + filtrosUsuario);

				Enumeration<Object> keys = propiedades.keys();

				while (keys.hasMoreElements()) {
					Object key = keys.nextElement();
					if (key.equals(Item)) {
						propiedades.setProperty(Item, filtrosUsuario);
						FileOutputStream os = null;

						try {
							os = new FileOutputStream(
									Constants.ROUTE_USUARIOS_PREFERENCIAS);
							propiedades.store(os,
									"Fichero de Propiedades de Usuarios!");
						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
					}
				}
				
				if(v.getTextField_validezLogin().getText().toString().equals("OK Login!")){
					guardarRecomendación();
					//guardarRecomendacionMongo(); //mock
					guardarRecomendacionMongoREAL();
					String usuario= v.getComboBox_eleccionDeUsuario().getSelectedItem().toString();
					
					String notificacion="";
					String contacto="";
					String notificacionClass="";
					try {
						String eleccionModoNotificacion= v.getComboBox_notificacion().getSelectedItem().toString();
						if(eleccionModoNotificacion.equals("notificaciones.NotificacionPorEmail")){
							notificacion= v.getTextArea_Recomendaciones().getText().toString();
							contacto= v.getTextField_validarEmail().getText().toString();
							notificacionClass= "notificaciones.NotificacionPorEmail";
						}else{
							notificacion= v.getTextArea_Recomendaciones().getText().toString();
							contacto= getTwitterUsuario(usuario);
							notificacionClass= "notificaciones.NotificacionPorTwitter";
						}
						try {
							if(!contacto.equals("vacio")){
								CargarArchivoClass cac2= new CargarArchivoClass(eleccionModoNotificacion, contacto, notificacion);
							}
						} catch (ClassNotFoundException | MalformedURLException
								| NoSuchMethodException | SecurityException
								| IllegalAccessException e1) {
							e1.printStackTrace();
						}
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Primero debes hacer Login correctamente!!", "Mensaje De Guardar y Notificar recomendaciones", JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
			
			public String getTwitterUsuario(String usuario){
				String twitter="";
				DBCollection collUsuarios=m.getMongo().db.getCollection("UsuarioDB");
		        BasicDBObject filtroReco = new BasicDBObject();
		        filtroReco.put("usuario", usuario);
		        DBCursor curReco = collUsuarios.find(filtroReco);
		        while (curReco.hasNext()){
		        	twitter= curReco.next().get("twitter").toString();
		        	System.out.println("twitter: "+twitter);
		        	if((twitter.equals("vacio"))){
		        		JOptionPane.showMessageDialog(null, "El usuario no tiene Twitter!!", "Mensaje de Notificacion a usuario registrado", JOptionPane.WARNING_MESSAGE);
		        	}
		        }
				
				
				return twitter;
			}
			
			public DBCollection guardarRecomendacionMongo(){
				Properties pr = new Properties();
				try {
					pr.load(new FileReader(
							Constants.ROUTE_USUARIOS_RECOMENDACIONES));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				MongoConcreteStub mongoReco= new MongoConcreteStub("RecomendacionesDB");// VEERRR
				Enumeration<Object> keys = pr.keys();
				
				BasicDBObject doc1;
				while (keys.hasMoreElements()){
					doc1 = new BasicDBObject();
				   Object key = keys.nextElement();
				   doc1.append(key.toString(), pr.get(key.toString()));
				   mongoReco.getPromos().insert(doc1);
				}
				return mongoReco.getPromos();
			}
			public DBCollection guardarRecomendacionMongoREAL(){
				Properties pr = new Properties();
				try {
					pr.load(new FileReader(
							Constants.ROUTE_USUARIOS_RECOMENDACIONES));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				MongoConcrete mongoReco= new MongoConcrete("RecomendacionesDB");// VEERRR
				mongoReco.eliminarTodaLaColeccion();
				
				Enumeration<Object> keys = pr.keys();

				DBCollection collRecomendacion=m.getMongo().db.getCollection("RecomendacionesDB");
				BasicDBObject doc1;
				doc1 = new BasicDBObject();
				//doc1.append(v.getTextField_usuario().getText(), v.getTextField_Email().getText());
				doc1.append("usuario",v.getComboBox_eleccionDeUsuario().getSelectedItem().toString());
				doc1.append("recomendacion", v.getTextArea_fechaActualizacion().getText()+","+ v.getTextArea_Recomendaciones().getText());
				collRecomendacion.insert(doc1);
				System.out.println("collRecomendacion.count(): "+collRecomendacion.count());
			    
				return mongoReco.getPromos();
			}
			public void guardarRecomendación(){
				Properties pr = new Properties();
				try {
					pr.load(new FileReader(
							Constants.ROUTE_USUARIOS_RECOMENDACIONES));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				pr.setProperty(v.getComboBox_eleccionDeUsuario().getSelectedItem().toString(), v.getTextArea_fechaActualizacion().getText()+"\n"+ v.getTextArea_Recomendaciones().getText());
				FileOutputStream os = null;

				try {
					os = new FileOutputStream(
							Constants.ROUTE_USUARIOS_RECOMENDACIONES);
					pr.store(os,
							"Fichero de Propiedades de Recomendaciones de Usuarios!");
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
	}
}
