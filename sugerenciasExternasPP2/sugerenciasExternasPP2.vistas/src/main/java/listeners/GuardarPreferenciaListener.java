package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

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
				guardarRecomendación();
				guardarRecomendacionMongo();
			}
			public DBCollection guardarRecomendacionMongo(){
				Properties pr = new Properties();
				try {
					pr.load(new FileReader(
							Constants.ROUTE_USUARIOS_RECOMENDACIONES));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				MongoConcreteStub mongoReco= new MongoConcreteStub("RecomendacionesDB");
				Enumeration<Object> keys = pr.keys();
				
				BasicDBObject doc1;
				while (keys.hasMoreElements()){
					doc1 = new BasicDBObject();
				   Object key = keys.nextElement();
				   //System.out.println(key + " = "+ propiedades.get(key));
				   doc1.append(key.toString(), pr.get(key.toString()));
				   mongoReco.getPromos().insert(doc1);
				}
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
