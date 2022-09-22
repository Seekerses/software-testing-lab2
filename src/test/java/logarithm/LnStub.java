package logarithm;

import functions.logarithm.Ln;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class LnStub{

    public final Ln lnMock = mock(Ln.class);

    public LnStub(){
        Filler.fillStub("./src/test/resources/in/Ln.csv", lnMock);
    }

//    @Test
//    public void create() throws IOException {
//        Ln ln = new Ln();
//        ln.exportValue(Path.of("./src/test/resources/in/Ln.csv"), List.of(
//                BigDecimal.valueOf(14D),
//                new BigDecimal(2D),
//                new BigDecimal(3D),
//                new BigDecimal(5D)),
//                List.of(
//                        BigDecimal.valueOf(1E-16),
//                        new BigDecimal(1E-23),
//                        new BigDecimal(1E-64),
//                        new BigDecimal(1E-16)
//                ));
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/Ln.csv")
    public void testStub(BigDecimal x, BigDecimal precision, BigDecimal expect){
        try {
            BigDecimal result = lnMock.calculate(x, precision);
            assertEquals(expect.doubleValue(), result.doubleValue());
        } catch (ArithmeticException e) {
            assertEquals("x is less than or equals to 0", e.getMessage());
        }
    }
}
