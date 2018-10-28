package mvc_modelo_observable;


import mvc_modelo_observable.Modelo;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;


/**
 * Controlador del MVC. Se encuentra dentro de la vista y es llamado por esta para controlar a la lógica.
 * El controlador contiene un modelo, el cual es la logica de nuestro programa, y se encarga de llamar a este modelo para que realice las acciones necesarias sobre el programa.
 * @author fon
 */
public class Controlador {
    Modelo m; //Nuestra lógica del programa
    Vista2 v;
    
    /**
     * Constructora del controlador. Creará un controlador, y se le asignará el modelo correspondiente.
     * @param m 
     */
    public Controlador(Modelo m, Vista2 v){
        //Asignamos un modelo a nuestro controlador.
        this.m = m;
        this.v= v;
        
        m.addObserver(v);
        v.addFiltroListeners(new FiltroListener());
    }
    
    /**
     * Función sumar. Notifica al modelo que quiere incrementar el valor.
     */
    public void filtroUsuarioA(){
        m.filtroA();
    }
    
    /**
     * Funcion restar. Notifica al modeo que quiere restar al valor.
     */
    public void filtroUsuarioB(){
        m.filtroB();
    }
    
    public void filtroU1(){
        m.filtroChatarras();
    }
    
    class FiltroListener implements ActionListener{

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
		            	controladorDeCheckbox();
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
		            	controladorDeCheckbox();
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
		            	controladorDeCheckbox();
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
		            	controladorDeCheckbox();
		            }
		        });
		}
		public void controladorDeCheckbox(){
			boolean c= v.getChckbx_filtrosChatarras().isSelected();
			boolean po= v.getChckbx_filtrosPostres().isSelected();
			boolean s= v.getChckbx_filtrosSanas().isSelected();
			boolean pa= v.getChckbx_filtrosPastas().isSelected(); 
			JTextArea t= v.getTextArea_Recomendaciones();
			if(c && po && s && pa){//1
				t.setText(m.getFiltroChatarras() +"\n"+m.getFiltroPostres()+"\n"+m.getFiltroSanas()+"\n"+m.getFiltroPastas() );
			}
			if(c && po && s && !pa){//2
				t.setText(m.getFiltroChatarras()+"\n"+m.getFiltroPostres()+"\n"+m.getFiltroSanas() );
			}
			if(c && po && !s && pa){//3
				t.setText(m.getFiltroChatarras() +"\n"+m.getFiltroPostres()+"\n"+m.getFiltroPastas() );
			}
			if(c && po && !s && !pa){//4
				t.setText(m.getFiltroChatarras() +"\n"+m.getFiltroPostres() );
			}
			if(c && !po && s && pa){//5
				t.setText(m.getFiltroChatarras() +"\n"+m.getFiltroSanas()+"\n"+m.getFiltroPastas());
			}
			if(c && !po && s && !pa){//6
				t.setText(m.getFiltroChatarras()+"\n"+m.getFiltroSanas());
			}
			if(c && !po && !s && pa){//7
				t.setText(m.getFiltroChatarras()+"\n"+m.getFiltroPastas() );
			}
			if(c && !po && !s && !pa){//8
				t.setText(m.getFiltroChatarras() );
			}
			if(!c && po && s && pa){//9
				t.setText(m.getFiltroPostres()+"\n"+m.getFiltroSanas()+"\n"+m.getFiltroPastas() );
			}
			if(!c && po && s && !pa){//10
				t.setText(m.getFiltroPostres()+"\n"+m.getFiltroSanas() );
			}
			if(!c && po && !s && pa){//11
				t.setText(m.getFiltroPostres()+"\n"+m.getFiltroPastas() );
			}
			if(!c && po && !s && !pa){//12
				t.setText(m.getFiltroPostres());
			}
			if(!c && !po && s && pa){//13
				t.setText(m.getFiltroSanas()+"\n"+m.getFiltroPastas() );
			}
			if(!c && !po && s && !pa){//14
				t.setText(m.getFiltroSanas());
			}
			if(!c && !po && !s && pa){//15
				t.setText(m.getFiltroPastas() );
			}
			if(!c && !po && !s && !pa){//16
				t.setText("");
			}
		}
    	
    }

   
}
