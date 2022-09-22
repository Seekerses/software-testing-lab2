package functions.trigonometry;

import functions.util.ExporterMathFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Sec implements ExporterMathFunction {

    private final Cos cos;

    public Sec(Cos cos){
        this.cos = cos;
    }

    public BigDecimal calculate(BigDecimal x, BigDecimal precision){
        return cos.calculate(x, precision
                .divide(BigDecimal.valueOf(20), precision.scale() + 2, RoundingMode.DOWN))
                .pow(-1, new MathContext(precision.scale(), RoundingMode.DOWN));
    }
}
