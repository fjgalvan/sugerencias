package validaciones;

public class ValidarTwitter {
	String twitterString;
	
	public ValidarTwitter(String twitterString){
		this.twitterString= twitterString;
	}
	
	public boolean cond1(){
		if(twitterString.startsWith("#promo:")){ return true;}
		else{ return false;}
	}
	
	public boolean cond2(){
		int cont=0;
		for(int i=0; i<twitterString.length(); i++){
			if(twitterString.charAt(i) == '_'){ cont= cont +1;}
		}
		if (cont==3){ return true;}
		else{return false;}
	}
	
	public boolean cond3(){
		if(twitterString.contains("_lista(")){
			return true;}
		else{return false;}
	}
	
	public boolean cond4(){
		if(twitterString.contains(")_")){
			return true;}
		else{return false;}
	}
	
	public boolean cond5(){
		int cont1=0;
		int cont2=0;
		for(int i=0; i<twitterString.length(); i++){
			if(twitterString.charAt(i) == '/'){ cont1= cont1 +1;}
			if(twitterString.charAt(i) == ','){ cont2= cont2 +1;}
		}
		if (cont1==(cont2 +1)){ return true;}
		else{return false;}
	}
	
	//Verifico si se respeta el formato final del string ")_dd-mm-aaaa" corespondiente a fecha
	public boolean cond6(){
		
		if(Character.isDigit(twitterString.charAt(twitterString.length()-1)) &&
		   Character.isDigit(twitterString.charAt(twitterString.length()-2)) &&
		   Character.isDigit(twitterString.charAt(twitterString.length()-3)) &&
		   Character.isDigit(twitterString.charAt(twitterString.length()-4)) &&
		   ((twitterString.charAt(twitterString.length()-5)) == '-') &&
		   Character.isDigit(twitterString.charAt(twitterString.length()-6)) &&
		   Character.isDigit(twitterString.charAt(twitterString.length()-7)) &&
		   ((twitterString.charAt(twitterString.length()-8)) == '-') &&
		   Character.isDigit(twitterString.charAt(twitterString.length()-9)) &&
		   Character.isDigit(twitterString.charAt(twitterString.length()-10)) &&
		   ((twitterString.charAt(twitterString.length()-11)) == '_') && 
		   ((twitterString.charAt(twitterString.length()-12)) == ')')  ) 	
			{return true;}
		else{return false;}
	}
	
	public boolean twitterStringValido(){
		if(cond1() && cond2() && cond3() && cond4() && cond5() && cond6() ){
			return true;
		}else{
			return false;
		}
	}
}
