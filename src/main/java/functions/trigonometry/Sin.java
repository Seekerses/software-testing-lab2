package functions.trigonometry;

import functions.util.ExporterMathFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sin implements ExporterMathFunction {
    public BigDecimal calculate(BigDecimal val, BigDecimal precision){
        int n = 3;
        int scale = precision.scale();
        BigDecimal factorial = new BigDecimal(1);
        BigDecimal stepVal = val;
        BigDecimal sum = stepVal;
        BigDecimal doubleVal = val.multiply(val);
        BigDecimal curr = precision.add(BigDecimal.ONE);
        while (curr.abs().compareTo(precision) > 0){
            factorial = factorial.
                    multiply(BigDecimal.valueOf(n - 1)).
                    multiply(BigDecimal.valueOf(n));
            stepVal = stepVal.multiply(doubleVal)
                    .multiply(BigDecimal.valueOf(-1));
            curr = stepVal
                    .divide(factorial, scale + 2, RoundingMode.HALF_EVEN);
            sum = sum.add(curr);
            n += 2;
        }
        return sum.setScale(scale, RoundingMode.DOWN);
    }
}
