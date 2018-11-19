package estadisticas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public final class MoneyCalculation {
	private BigDecimal amountOne;
	private BigDecimal amountTwo;
	private static RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	private static int DECIMALS = 2;
	private static int EXTRA_DECIMALS = 4;
	private static final BigDecimal TWO = new BigDecimal("2");
	private static BigDecimal HUNDRED = new BigDecimal("100");
	private static BigDecimal PERCENTAGE = new BigDecimal("5.25");
	
	
	
	
//	public static void main(String[] args) {
//		double d1 = 374.56;
//		double d2 = 374.26;
//		BigDecimal amountOne = new BigDecimal("374.56");
//		BigDecimal amountTwo = new BigDecimal("374.26");
//		MoneyCalculation calc = new MoneyCalculation(amountOne, amountTwo);
//		calc.doCalculations();
//	}

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
		return getSum().divide(TWO, ROUNDING_MODE);
	}

	private BigDecimal getPercentage() {
		BigDecimal result = amountOne.multiply(PERCENTAGE);
		result = result.divide(HUNDRED, ROUNDING_MODE);
		return rounded(result);
	}

	private BigDecimal getPercentageChange() {
		BigDecimal fractionalChange = getDifference().divide(amountOne,
				EXTRA_DECIMALS, ROUNDING_MODE);
		return rounded(fractionalChange.multiply(HUNDRED));
	}

	private BigDecimal rounded(BigDecimal number) {
		return number.setScale(DECIMALS, ROUNDING_MODE);
	}
	
}
