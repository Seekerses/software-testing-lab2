package functions.logarithm;

import functions.util.ExporterMathFunction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Log implements ExporterMathFunction {

    private final Ln ln;
    private final BigDecimal base;

    public Log(Ln ln, BigDecimal base){
        this.ln = ln;
        this.base = base;
    }

    public BigDecimal calculateWithBase(BigDecimal x, BigDecimal base, BigDecimal precision){
        int scale = precision.scale();
        return ln.calculate(x, precision)
                .divide(ln.calculate(base, precision), scale + 2, RoundingMode.DOWN)
                .setScale(scale - 1, RoundingMode.DOWN);
    }


    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        int scale = precision.scale();
        return ln.calculate(x, precision)
                .divide(ln.calculate(base, precision), scale + 2, RoundingMode.DOWN)
                .setScale(scale - 1, RoundingMode.DOWN);
    }

    public void exportRangeWithBase(Path path, BigDecimal base, BigDecimal from, BigDecimal to, BigDecimal step, BigDecimal precision) throws IOException {
        if(!Files.exists(path)){
            Files.createFile(path);
        }
        try(CSVPrinter printer = CSVFormat.DEFAULT.print(path, StandardCharsets.UTF_8)){
            for(BigDecimal i = from; i.compareTo(to) <= 0; i = i.add(step)){
                printer.printRecord(i, base, precision, calculateWithBase(i, base, precision));
            }
        }
    }

    public void exportValuesWithBase(Path path, List<BigDecimal> values, List<BigDecimal> base, List<BigDecimal> precisions) throws IOException{
        if (values.size() != precisions.size()) throw new IllegalArgumentException("Number of values doesnt match number of precisions or bases!");
        if(!Files.exists(path)){
            Files.createFile(path);
        }
        try(CSVPrinter printer = CSVFormat.DEFAULT.print(path, StandardCharsets.UTF_8)){
            for(int i = 0; i < values.size(); i++){
                printer.printRecord(values.get(i), base.get(i),  precisions.get(i), calculateWithBase(values.get(i), base.get(i), precisions.get(i)));
            }
        }
    }
}
