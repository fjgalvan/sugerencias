package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bo.UsuariosBo;

public class RegistroNombreUsuarioListener implements ActionListener{
	Vista2 v;
	public RegistroNombreUsuarioListener(Vista2 v){
		this.v=v;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String s1= v.getTextField_usuario().getText();
		 	System.out.println("v.getTextField_usuario(): "+s1);
		 
		 	UsuariosBo uBo= new UsuariosBo();
		 	if(uBo.caracteresValidosUsuario(s1)){
		 		v.getTextArea_ValidezUsuario().setText("OK");
		 	}else{
		 		v.getTextArea_ValidezUsuario().setText("Error");
		 	}

	}
}