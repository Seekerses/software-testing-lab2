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
        if (x.compareTo(BigDecimal.ZERO) == 0) throw new ArithmeticException("infinite");
        if (x.compareTo(BigDecimal.ONE) == 0) throw new ArithmeticException("infinite");
        if(x.compareTo(BigDecimal.ZERO) < 0){
            BigDecimal sinVal = sin.calculate(x, precision);
            BigDecimal cosVal = cos.calculate(x, precision);
            return sec.calculate(x, precision)
                    .multiply(cosVal)
                    .divide(sinVal, precision.scale() + 2, RoundingMode.DOWN)
                    .add(cosVal)
                    .add(sinVal)
                    .multiply(csc.calculate(x, precision));
        }else {
            BigDecimal log5 = log.calculateWithBase(x, BigDecimal.valueOf(5), precision);
            return log.calculateWithBase(x, BigDecimal.TEN, precision)
                    .add(log.calculateWithBase(x, BigDecimal.valueOf(3), precision))
                    .multiply(log.calculateWithBase(x, BigDecimal.valueOf(2), precision))
                    .divide(ln.calculate(x, precision), precision.scale() + 2, RoundingMode.DOWN)
                    .add(log5)
                    .add(log5);
        }
    }
}
