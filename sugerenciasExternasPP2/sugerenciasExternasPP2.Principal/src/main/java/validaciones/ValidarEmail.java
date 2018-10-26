package validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarEmail {

	public ValidarEmail() {

	}

	// metodo para validar correo electronio
	public boolean isEmail(String correo) {
		Pattern pat = null;
		Matcher mat = null;
		pat = Pattern
				.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
		mat = pat.matcher(correo);
		if (mat.find()) {
			System.out.println("[" + mat.group() + "]");
			return true;
		} else {
			return false;
		}
	}

	public void validator() {
		String correo = "javier@gmail.com";
		if (isEmail(correo)) {
			System.out.println("Mail correcto");
		} else {
			System.out.println("Mail incorrecto");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ValidarEmail ve = new ValidarEmail();
		ve.validator();
	}

}
