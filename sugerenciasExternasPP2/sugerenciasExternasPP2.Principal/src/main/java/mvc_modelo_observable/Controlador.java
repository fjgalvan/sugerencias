package mvc_modelo_observable;


import mvc_modelo_observable.Modelo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTextArea;

import bo.UsuariosBo;


/**
 * Controlador del MVC. Se encuentra dentro de la vista y es llamado por esta para controlar a la lógica.
 * El controlador contiene un modelo, el cual es la logica de nuestro programa, y se encarga de llamar a este modelo para que realice las acciones necesarias sobre el programa.
 * @author fon
 */
public class Controlador {
    Modelo m; //Nuestra lógica del programa
    Vista2 v;
    boolean c;
    boolean po;
    boolean s;
    boolean pa;
    
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
        v.addRegistroListener(new RegistroNombreUsuarioListener());
        v.addRegistroListener(new RegistroEmailUsuarioListener());
        v.addRegistroNuevoUsuarioListener(new RegistroNuevoUsuarioListener());
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
    
//    public void filtroU1(){
//        m.filtroChatarras();
//    }
    
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
			v.getBtn_refrescar().addMouseListener(new MouseAdapter() {
				@Override
	            public void mouseClicked(MouseEvent e) {
					v.getChckbx_filtrosSanas().setSelected(false);
	            	v.getChckbx_filtrosPostres().setSelected(false);
	            	v.getChckbx_filtrosChatarras().setSelected(false);
	            	v.getChckbx_filtrosPastas().setSelected(false);
	            	controladorDeCheckbox();
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
		}
    
		public void controladorDeCheckbox(){
			c= v.getChckbx_filtrosChatarras().isSelected();
			po= v.getChckbx_filtrosPostres().isSelected();
			s= v.getChckbx_filtrosSanas().isSelected();
			pa= v.getChckbx_filtrosPastas().isSelected(); 
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
		class RegistroNombreUsuarioListener implements ActionListener{
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			String s1= v.getTextField_usuario().getText();
   			 	System.out.println("v.getTextField_usuario(): "+s1);
   			 
   			 	UsuariosBo uBo= new UsuariosBo();
   			 	if(uBo.caracteresValidosUsuario(s1)){
   			 		v.getTextArea_ValidezUsuario().setText("Formato OK");
   			 	}else{
   			 		v.getTextArea_ValidezUsuario().setText("Formato Incorrecto");
   			 	}

    		}
		}
		
		class RegistroEmailUsuarioListener implements ActionListener{
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			String s1= v.getTextField_Email().getText();
   			 	System.out.println("v.getTextField_Email(): "+s1);
   			 
   			 	UsuariosBo uBo= new UsuariosBo();
   			 	if(uBo.validarEmail(s1)){
   			 		v.getTextArea_validezEmail().setText("Formato OK");
   			 	}else{
   			 		v.getTextArea_validezEmail().setText("Formato Incorrecto");
   			 	}

    		}
		}
		
		class RegistroNuevoUsuarioListener implements ActionListener{
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			v.getBtnRegistrarse().addMouseListener(new MouseAdapter() {
    				boolean registro= false;
    				@Override
    	            public void mouseClicked(MouseEvent e) {
    					UsuariosBo uBo= new UsuariosBo();
    					try {
    						registro=uBo.agregarNuevoUsuario(v.getTextField_usuario().getText(), v.getTextField_Email().getText());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    					if(registro){
    						v.getTextArea_registroNuevoUsuario().setText("Registro OK");
    					}else{
    						v.getTextArea_registroNuevoUsuario().setText("El Usuario Ya Existe!!");
    					}
    				}
    			});
    		}
		}
		
   }

   

