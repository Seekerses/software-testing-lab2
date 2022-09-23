package logarithm;

import functions.util.ExporterMathFunction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class Filler {

    public static void fillStub(String path, ExporterMathFunction mock){
        try {
            CSVParser parser = CSVFormat.DEFAULT.parse(new FileReader(path));
            parser.stream().forEach(v -> when(mock.calculate(eq(new BigDecimal(v.get(0))), any()))
                    .thenReturn(new BigDecimal(v.get(1))));
        } catch (IOException ignore) {}
    }
}
