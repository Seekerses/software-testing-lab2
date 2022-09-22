package functions.logarithm;

import functions.util.ExporterMathFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Ln implements ExporterMathFunction {

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision){
        if (x.compareTo(BigDecimal.ZERO) <= 0) throw new ArithmeticException("Ln parameter must be greater than 0.");
        int n = 1;
        final int scale = precision.scale();
        final BigDecimal value = x.negate().add(BigDecimal.ONE)
                .divide(x.add(BigDecimal.ONE), scale, RoundingMode.HALF_EVEN);
        BigDecimal stepValue = value;
        BigDecimal sum = stepValue;
        precision = precision
                .divide(BigDecimal.valueOf(10), scale + 1, RoundingMode.HALF_EVEN);
        BigDecimal curr = precision.add(BigDecimal.ONE);
        while(curr.abs().compareTo(precision) > 0){
            n += 2;
            stepValue = stepValue
                    .multiply(value)
                    .multiply(value);
            curr = stepValue
                    .divide(BigDecimal.valueOf(n), scale + 1, RoundingMode.HALF_EVEN);
            sum = sum.add(curr);
        }
        return sum.multiply(BigDecimal.valueOf(-2)).setScale(scale - 1, RoundingMode.DOWN);
    }
}
