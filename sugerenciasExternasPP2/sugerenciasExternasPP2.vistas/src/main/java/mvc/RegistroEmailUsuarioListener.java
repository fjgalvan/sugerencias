package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bo.UsuariosBo;

public class RegistroEmailUsuarioListener implements ActionListener{
	Vista2 v;
	public RegistroEmailUsuarioListener(Vista2 v){
		this.v= v;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String s1= v.getTextField_Email().getText();
		 	System.out.println("v.getTextField_Email(): "+s1);
		 
		 	UsuariosBo uBo= new UsuariosBo();
		 	if(uBo.validarEmail(s1)){
		 		v.getTextArea_validezEmail().setText("OK");
		 	}else{
		 		v.getTextArea_validezEmail().setText("Error");
		 	}

	}
}