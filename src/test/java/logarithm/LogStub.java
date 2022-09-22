package logarithm;

import functions.logarithm.Ln;
import functions.logarithm.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class LogStub {
    public final Log logMock = mock(Log.class);

    public LogStub(){
        Filler.fillStub("./src/test/resources/out/Ln.csv", logMock);
    }


//    @Test
//    public void create() throws IOException {
//        Log log = new Log(new Ln(), BigDecimal.valueOf(2D));
//        log.exportValuesWithBase(Path.of("./src/test/resources/in/Log.csv"), List.of(
//                        new BigDecimal(14D),
//                        new BigDecimal(2D),
//                        new BigDecimal(3D),
//                        new BigDecimal(5D),
//                        new BigDecimal(14D),
//                        new BigDecimal(2D),
//                        new BigDecimal(3D),
//                        new BigDecimal(5D),
//                        new BigDecimal(14D),
//                        new BigDecimal(2D),
//                        new BigDecimal(3D),
//                        new BigDecimal(5D)),
//                List.of(
//                        new BigDecimal(2),
//                        new BigDecimal(2),
//                        new BigDecimal(2),
//                        new BigDecimal(2),
//                        new BigDecimal(3),
//                        new BigDecimal(3),
//                        new BigDecimal(3),
//                        new BigDecimal(3),
//                        new BigDecimal(5),
//                        new BigDecimal(5),
//                        new BigDecimal(5),
//                        new BigDecimal(5)
//                ),
//                List.of(
//                        BigDecimal.valueOf(1E-16),
//                        new BigDecimal(1E-23),
//                        new BigDecimal(1E-64),
//                        new BigDecimal(1E-16),
//                        BigDecimal.valueOf(1E-16),
//                        new BigDecimal(1E-23),
//                        new BigDecimal(1E-64),
//                        new BigDecimal(1E-16),
//                        BigDecimal.valueOf(1E-16),
//                        new BigDecimal(1E-23),
//                        new BigDecimal(1E-64),
//                        new BigDecimal(1E-16)
//                ));
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/Log.csv")
    public void testStub(BigDecimal x, BigDecimal base, BigDecimal precision, BigDecimal expect){
        try {
            BigDecimal result = logMock.calculateWithBase(x,base, precision);
            assertEquals(expect.doubleValue(), result.doubleValue());
        } catch (ArithmeticException e) {
            assertEquals("x is less than or equals to 0", e.getMessage());
        }
    }
}
