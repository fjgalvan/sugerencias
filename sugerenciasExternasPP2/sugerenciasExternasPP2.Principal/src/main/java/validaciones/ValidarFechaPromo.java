package validaciones;

import sugerencias.ConvertirString_a_Sugerencia;



public class ValidarFechaPromo {
	FechaActual f;
	String ts;
	public ValidarFechaPromo(String twitterString){
		this.f= new FechaActual();
		this.ts= twitterString;
	}
	
	public boolean VigenciaPromoValida(){
		boolean vigencia= false;
		ConvertirString_a_Sugerencia c= new ConvertirString_a_Sugerencia(this.ts);
		c.convertirFecha();
		c.getDate();
		
		if(c.getDate().getAaaa()<this.f.getAnnioActual()){//SI EL AÑO DE LA PROMO ES MENOR AL AÑO ACTUAL
			//System.out.println("promo vencida");
			vigencia= false;
		}
		
		if(c.getDate().getAaaa().equals(this.f.getAnnioActual())){//SI EL AÑO DE LA PROMO ES IGUAL AÑO ACTUAL
			if(c.getDate().getMm().equals(this.f.getMesActual())){//SI EL MES DE LA PROMO ES IGUAL AL DEL MES ACTUAL
				if(c.getDate().getDd()>=this.f.getDiaActual()){//SI EL DIA DE LA PROMO ES MAYOY O IGUAL AL DIA ACTUAL
					//System.out.println("promo vigente");
					vigencia= true;
				}if(c.getDate().getDd()<this.f.getDiaActual()){//SI EL DIA DE LA PROMO ES MENOR AL DIA ACTUAL
					//System.out.println("promo vencida");
					vigencia= false;
				}
			}
			if(c.getDate().getMm()>this.f.getMesActual()){//SI EL MES DE LA PROMO ES MAYOR AL DEL MES ACTUAL
				//System.out.println("promo vigente");
				vigencia= true;
			}
			if(c.getDate().getMm()<this.f.getMesActual()){//SI EL MES DE LA PROMO ES MENOR AL DEL MES ACTUAL
				//System.out.println("promo vencida");
				vigencia= false;
			}
			//vigencia= true;
		}
		
		if(c.getDate().getAaaa()>this.f.getAnnioActual()){
			//System.out.println("promo vigente");
			vigencia= true;
		}
		return vigencia;
	}
	
}
