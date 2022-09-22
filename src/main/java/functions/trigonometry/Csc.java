package functions.trigonometry;

import functions.util.ExporterMathFunction;
import functions.util.MathFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Csc implements ExporterMathFunction {

    private final Sin sin;

    public Csc(Sin sin){
        this.sin = sin;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision){
        return sin.calculate(x, precision
                .divide(BigDecimal.valueOf(20), precision.scale() + 2, RoundingMode.DOWN))
                .pow(-1, new MathContext(precision.scale(), RoundingMode.DOWN));
    }
}
