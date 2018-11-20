package listeners;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Properties;

import com.mongodb.DBCollection;

import estadisticas.PromosOpuestosEnPrecio;
import mvc.Controlador;
import mvc.Vista2;
import mvc_modelo_observable.Modelo;
import properties.Constants;
import twitter4j.TwitterException;


@SuppressWarnings("unused")
public class FiltroListener implements ActionListener{
	Vista2 v;
	Modelo m;
	public FiltroListener(Vista2 v, Modelo m){
		this.v= v;
		this.m= m;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		v.getBtnFiltrarPreferencia().addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
				String Item="";
				Item = v.getComboBox_eleccionDeUsuario().getSelectedItem().toString();
				if(Item.equalsIgnoreCase("Usuario A")){
					v.getTextArea_Recomendaciones().setText("");
	            	m.filtroA();
	            	
	            	v.getChckbx_filtrosChatarras().setSelected(true);
	            	v.getChckbx_filtrosPostres().setSelected(true);
	            	v.getChckbx_filtrosSanas().setSelected(false);
	            	v.getChckbx_filtrosPastas().setSelected(false);
	            	
				}
				if(Item.equalsIgnoreCase("Usuario B")){
					v.getTextArea_Recomendaciones().setText("");
	            	m.filtroB();
	            	
	            	v.getChckbx_filtrosSanas().setSelected(true);
	            	v.getChckbx_filtrosPostres().setSelected(true);
	            	v.getChckbx_filtrosChatarras().setSelected(false);
	            	v.getChckbx_filtrosPastas().setSelected(false);
	            	
				}
				Properties propiedades= new Properties();
				try {
					propiedades.load(new FileReader(Constants.ROUTE_USUARIOS_PREFERENCIAS));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Enumeration<Object> keys = propiedades.keys();

				while (keys.hasMoreElements()){
				   Object key = keys.nextElement();
				   System.out.println(key + " = "+ propiedades.get(key));
				}
			}
		});
		v.getBtn_refrescar().addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
				v.getChckbx_filtrosSanas().setSelected(false);
            	v.getChckbx_filtrosPostres().setSelected(false);
            	v.getChckbx_filtrosChatarras().setSelected(false);
            	v.getChckbx_filtrosPastas().setSelected(false);
            	Controlador.controladorDeCheckbox(m,v,false,false,false,false);
            	try {
					cargarUsuarios();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		v.getBtnEstadisticasPrecios().addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
				DBCollection coll = null;
				try {
//            		m.getMongo().finish();
//            		m.ConectarMongoDBStub();
					coll=m.cargarTodasLasPromos();
				} catch (ClassNotFoundException | NoSuchMethodException
						| SecurityException | InstantiationException
						| IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				PromosOpuestosEnPrecio estadistica= new PromosOpuestosEnPrecio();
				v.getTextArea_masCara().setText(estadistica.getPromoMasCara(coll).toString()); 
				v.getTextArea_masEconomico().setText(estadistica.getPromoMasEconomica(coll).toString());
			}
		});
		
		v.getBtn_actualizarPromos().addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
				v.getChckbx_filtrosSanas().setSelected(false);
            	v.getChckbx_filtrosPostres().setSelected(false);
            	v.getChckbx_filtrosChatarras().setSelected(false);
            	v.getChckbx_filtrosPastas().setSelected(false);
            	Controlador.controladorDeCheckbox(m,v,false,false,false,false);
            	try {
					cargarUsuarios();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            	//Funciona sin Timer
//            	try {
//            		m.getMongo().finish();
//            		m.ConectarMongoDBStub();
//					m.cargarRecomendaciones();
//				} catch (ClassNotFoundException | NoSuchMethodException
//						| SecurityException | InstantiationException
//						| IllegalAccessException | IllegalArgumentException
//						| InvocationTargetException | TwitterException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
            	
            	//Funciona con Timer
            	try {
            		m.getMongo().finish();
            		v.getTm().start(); //COMIENZA A CARGAR TODAS LAS PROMOS CADA CIERTO TIEMPO
                	m.setMongo(v.getBasePromosActual()); //Obtengo la colleccion de Promos actual
					m.cargarRecomendaciones();
				} catch (ClassNotFoundException | NoSuchMethodException
						| SecurityException | InstantiationException
						| IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	//v.getTm().stop(); //DENTIENE LA BUSQUEDA DE CARGAS Y PROMOS
			}
		});
		
		 v.getChckbx_filtrosChatarras().addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	if(v.getChckbx_filtrosChatarras().isSelected()){
	            		System.out.println("selecciono filtro chatarra");
	            		m.filtroChatarras();
	            		
	            	}else{
	            		System.out.println("Deselecciono filtro chatarra");
	            		m.setFiltroEspecial("");
	            		m.setValorString("");
	            	}
	            	Controlador.controladorDeCheckbox(m,v,false,false,false,false);
	            }
	        });
		 v.getChckbx_filtrosPostres().addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	if(v.getChckbx_filtrosPostres().isSelected()){
	            		m.filtroPostres();
	            	}else{
	            		m.setFiltroEspecial("");
	            		m.setValorString("");
	            	}
	            	Controlador.controladorDeCheckbox(m,v,false,false,false,false);
	            }
	        });
		 v.getChckbx_filtrosSanas().addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	if(v.getChckbx_filtrosSanas().isSelected()){
	            		m.filtroSanas();
	            	}else{
	            		m.setFiltroEspecial("");
	            		m.setValorString("");
	            	}
	            	Controlador.controladorDeCheckbox(m,v,false,false,false,false);
	            }
	        });
		 v.getChckbx_filtrosPastas().addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	if(v.getChckbx_filtrosPastas().isSelected()){
	            		m.filtroPastas();
	            	}else{
	            		m.setFiltroEspecial("");
	            		m.setValorString("");
	            	}
	            	Controlador.controladorDeCheckbox(m,v,false,false,false,false);
	            }
	        });

		 }
	@SuppressWarnings("unchecked")
	public void cargarUsuarios() throws FileNotFoundException, IOException{

		String aux=""; 
		v.getComboBox_eleccionDeUsuario().removeAllItems();//VER
		Properties propiedades = new Properties();
		propiedades.load(new FileReader(Constants.ROUTE_USUARIOS));
		Enumeration<Object> keys = propiedades.keys();
		while (keys.hasMoreElements()){
		   Object key = keys.nextElement();
		   System.out.println(key + " = "+ propiedades.get(key));
		   if(!key.toString().equals(aux)){
			   v.getComboBox_eleccionDeUsuario().addItem(key.toString());
			   aux=key.toString();
			   System.out.println("aux: "+aux);
		   }
		   
		}
		v.getComboBox_eleccionDeUsuario().addActionListener(this);
	}
}