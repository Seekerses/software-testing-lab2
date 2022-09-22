package functions.trigonometry;

import functions.util.ExporterMathFunction;
import functions.util.MathFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Cos implements ExporterMathFunction {

    private final Sin sin;

    public Cos(Sin sin){
        this.sin = sin;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision){
        double part = Math.abs(x.doubleValue() % (Math.PI * 2));
        boolean negativePart = part > Math.PI / 2 && part < Math.PI * 3 / 2;
        BigDecimal result =  sin.calculate(x, precision
                        .divide(BigDecimal.valueOf(20), precision.scale() + 2, RoundingMode.DOWN))
                .pow(2)
                .negate()
                .add(BigDecimal.ONE)
                .sqrt(new MathContext(precision.scale() - 1, RoundingMode.DOWN));
        if (negativePart && result.compareTo(BigDecimal.ZERO) > 0 ||
                !negativePart && result.compareTo(BigDecimal.ZERO) < 0) result = result.negate();
        return result;
    }
}
