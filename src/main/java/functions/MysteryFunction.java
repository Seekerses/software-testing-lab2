package functions;

import functions.logarithm.Ln;
import functions.logarithm.Log;
import functions.trigonometry.Cos;
import functions.trigonometry.Csc;
import functions.trigonometry.Sec;
import functions.trigonometry.Sin;
import functions.util.ExporterMathFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MysteryFunction implements ExporterMathFunction {

    private final Sin sin;
    private final Cos cos;
    private final Sec sec;
    private final Csc csc;
    private final Ln ln;
    private final Log log;

    public MysteryFunction(Sin sin, Cos cos, Sec sec, Csc csc, Ln ln, Log log) {
        this.sin = sin;
        this.cos = cos;
        this.sec = sec;
        this.csc = csc;
        this.ln = ln;
        this.log = log;
    }

    public BigDecimal calculate(BigDecimal x, BigDecimal precision){
        if (x.compareTo(BigDecimal.ONE) == 0) throw new ArithmeticException("Infinity");
        BigDecimal biggerPrecision = precision.divide(BigDecimal.valueOf(100), precision.scale() + 10, RoundingMode.DOWN);
        if(x.compareTo(BigDecimal.ZERO) < 0){
            BigDecimal sinVal = sin.calculate(x, biggerPrecision);
            BigDecimal cosVal = cos.calculate(x, biggerPrecision);
            return sec.calculate(x, biggerPrecision)
                    .multiply(cosVal)
                    .divide(sinVal, precision.scale() + 10, RoundingMode.DOWN)
                    .add(cosVal)
                    .add(sinVal)
                    .multiply(csc.calculate(x, biggerPrecision)).setScale(precision.scale(), RoundingMode.HALF_UP);
        }else {
            BigDecimal log5 = log.calculateWithBase(x, BigDecimal.valueOf(5), biggerPrecision);
            return log.calculateWithBase(x, BigDecimal.valueOf(10), biggerPrecision)
                    .add(log.calculateWithBase(x, BigDecimal.valueOf(3), biggerPrecision))
                    .multiply(log.calculateWithBase(x, BigDecimal.valueOf(2), biggerPrecision))
                    .divide(ln.calculate(x, biggerPrecision), precision.scale() + 2, RoundingMode.HALF_UP)
                    .add(log5)
                    .add(log5)
                    .setScale(precision.scale(), RoundingMode.HALF_EVEN);
        }
    }
}
