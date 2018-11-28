package estadisticas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

import configurables.MyConstantsEstadisticas;

public final class MoneyCalculation {
	private BigDecimal amountOne;
	private BigDecimal amountTwo;
	

	public MoneyCalculation(BigDecimal amountOne, BigDecimal amountTwo) {
		this.amountOne = rounded(amountOne);
		this.amountTwo = rounded(amountTwo);
	}

	public void doCalculations() {
		log("Amount One: " + amountOne);
		log("Amount Two: " + amountTwo);
		log("Sum : " + getSum());
		log("Difference : " + getDifference());
		log("Average : " + getAverage());
		//log("5.25% of Amount One: " + getPercentage());
		log("Percent Change From Amount One to Two: " + getPercentageChange());
	}

	private void log(String text) {
		System.out.println(Objects.toString(text));
	}

	private BigDecimal getSum() {
		return amountOne.add(amountTwo);
	}

	public BigDecimal getDifference() {
		return amountTwo.subtract(amountOne);
	}

	private BigDecimal getAverage() {
		return getSum().divide(MyConstantsEstadisticas.TWO, MyConstantsEstadisticas.ROUNDING_MODE);
	}

	private BigDecimal getPercentageChange() {
		BigDecimal fractionalChange = getDifference().divide(amountOne,
				MyConstantsEstadisticas.EXTRA_DECIMALS, MyConstantsEstadisticas.ROUNDING_MODE);
		return rounded(fractionalChange.multiply(MyConstantsEstadisticas.HUNDRED));
	}

	private BigDecimal rounded(BigDecimal number) {
		return number.setScale(MyConstantsEstadisticas.DECIMALS, MyConstantsEstadisticas.ROUNDING_MODE);
	}
	
}
