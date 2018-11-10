package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import mvc.Vista2;
import mvc_modelo_observable.Modelo;
import properties.Constants;
import properties.PropertiesPrincipal;

public class CambioDeIdiomaListener implements ActionListener{
	Vista2 v;
	Modelo m;
	public CambioDeIdiomaListener(Vista2 v, Modelo m){
		this.v= v;
		this.m= m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		v.getBtnCambiarIdioma().addMouseListener(new MouseAdapter() {
			@Override
	        public void mouseClicked(MouseEvent e) {
				String Item="";
				Item = v.getComboBox_Idioma().getSelectedItem().toString();
				if(Item.equalsIgnoreCase("Espanol")){
					PropertiesPrincipal pEspanol= new PropertiesPrincipal(Constants.ROUTE_ESPANOL);
					cambiarLbl(pEspanol);
					cambiarBtn(pEspanol);
					cambiarCheckBox(pEspanol);
	            	
				}
				if(Item.equalsIgnoreCase("Ingles")){
					PropertiesPrincipal pIngles= new PropertiesPrincipal(Constants.ROUTE_INGLES);
					cambiarLbl(pIngles);
					cambiarBtn(pIngles);
					cambiarCheckBox(pIngles);
				}
			}
			
			public void cambiarLbl(PropertiesPrincipal pp){
				v.getLbl_registrarUsuario().setText(pp.leerValorDeUnaClave("registrarUsuario"));
				v.getLbl_registrarEmail().setText(pp.leerValorDeUnaClave("registrarEmail"));
				v.getLbl_ValidezUsuario().setText(pp.leerValorDeUnaClave("ValidezUsuario"));
            	v.getLbl_ValidezEmail().setText(pp.leerValorDeUnaClave("validezEmail"));
            	v.getLblElijaIdioma().setText(pp.leerValorDeUnaClave("elijaIdioma"));
            	v.getLbl_ElijaUsuario().setText(pp.leerValorDeUnaClave("elijaUsuario"));
            	v.getLblElijaFiltros().setText(pp.leerValorDeUnaClave("elijaFiltros"));
            	v.getLblRecomendaciones().setText(pp.leerValorDeUnaClave("recomendaciones"));
			}
			
			public void cambiarBtn(PropertiesPrincipal pp){
				v.getBtnRegistrarse().setText(pp.leerValorDeUnaClave("registrarse"));
				v.getBtnCambiarIdioma().setText(pp.leerValorDeUnaClave("cambiarIdioma"));
				v.getBtnFiltrarPreferencia().setText(pp.leerValorDeUnaClave("filtrarPreferencia"));
				v.getBtn_refrescar().setText(pp.leerValorDeUnaClave("refrescar"));
			}
			
			public void cambiarCheckBox(PropertiesPrincipal pp){
				v.getChckbx_filtrosChatarras().setText(pp.leerValorDeUnaClave("filtroChatarras"));
				v.getChckbx_filtrosPostres().setText(pp.leerValorDeUnaClave("filtroPostres"));
				v.getChckbx_filtrosSanas().setText(pp.leerValorDeUnaClave("filtroSanas"));
				v.getChckbx_filtrosPastas().setText(pp.leerValorDeUnaClave("filtroPastas"));
			}
		});
	}
}

