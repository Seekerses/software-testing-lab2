package functions.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public interface ExporterMathFunction extends MathFunction{

    default void exportRange(Path path, BigDecimal from, BigDecimal to, BigDecimal step, BigDecimal precision) throws IOException {
        if(!Files.exists(path)){
            Files.createFile(path);
        }
        try(CSVPrinter printer = CSVFormat.DEFAULT.print(path, StandardCharsets.UTF_8)){
            for(BigDecimal i = from; i.compareTo(to) <= 0; i = i.add(step, new MathContext(64, RoundingMode.HALF_EVEN))){
                printer.printRecord(i, precision, calculate(i, precision));
            }
        }
    }

    default void exportValue(Path path, List<BigDecimal> values, List<BigDecimal> precisions) throws IOException{
        if (values.size() != precisions.size()) throw new IllegalArgumentException("Number of values doesnt match number of precisions!");
        if(!Files.exists(path)){
            Files.createFile(path);
        }
        try(CSVPrinter printer = CSVFormat.DEFAULT.print(path, StandardCharsets.UTF_8)){
            for(int i = 0; i < values.size(); i++){
                printer.printRecord(values.get(i), precisions.get(i), calculate(values.get(i), precisions.get(i)));
            }
        }
    }
}
