package estadisticas;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
//import java.util.Locale;

public class Money {
	Currency currency;
	BigDecimal amount;
	
	public Money(double amount2, Currency curr) {//curr= ARS es para Argentina
		this.currency = curr;
		BigDecimal amountOne = new BigDecimal(String.valueOf(amount2));
		this.amount = amountOne;
		System.out.println("curr: "+currency +", amount: "+amount);
	}

	
	public static Currency getCurrencyArgentina(){
		Locale locale = new Locale("es", "AR"); // elegimos Argentina
		NumberFormat nf2 = NumberFormat.getCurrencyInstance(locale);
		//System.out.println("El monto en moneda Argentina a pagar es de: " + nf2.format(amount));
		Currency curr2 = Currency.getInstance(locale);
		//System.out.println("The currency of Argentina is "+ curr2.getCurrencyCode());	
		return curr2;
	}
	public Currency getCurrency() {
		return currency;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	
}
