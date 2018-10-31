package mvc_modelo_observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import properties.Constants;


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
	public void cargarUsuarios() throws FileNotFoundException, IOException{
		
		for(int i=2; i<v.getComboBox_eleccionDeUsuario().getItemCount();i++){
			v.getComboBox_eleccionDeUsuario().removeItemAt(i);
			System.out.println("v.getComboBox_eleccionDeUsuario().getItemCount(): "+v.getComboBox_eleccionDeUsuario().getItemCount());
		}
		String aux=""; 
		
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