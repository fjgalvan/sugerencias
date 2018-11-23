package configurables;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyConstantsEstadisticas {
	public static RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	public static int DECIMALS = 2;
	public static int EXTRA_DECIMALS = 4;
	public static final BigDecimal TWO = new BigDecimal("2");
	public static BigDecimal HUNDRED = new BigDecimal("100");
	public static BigDecimal PERCENTAGE = new BigDecimal("5.25");
	
	public static final String promoLocal ="local";
	public static final String promoUbicacion ="ubicacion";
	public static final String promoProducto ="producto";
	public static final String promoPrecio ="precio";
	public static final String promoFechaDeVigencia ="local";
}
