package trigonometry;

import functions.logarithm.Ln;
import functions.trigonometry.Cos;
import functions.trigonometry.Sec;
import functions.trigonometry.Sin;
import logarithm.Filler;
import logarithm.LnStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class SecStub {
    public final Sec secMock = mock(Sec.class);

    public SecStub(){
        Filler.fillStub("./src/test/resources/out/Sec.csv", secMock);
    }


//    @Test
//    public void create() throws IOException {
//        Sec sec = new Sec(new Cos(new Sin()));
//        sec.exportValue(Path.of("./src/test/resources/in/Sec.csv"), List.of(
//                        BigDecimal.valueOf(14D),
//                        new BigDecimal(2D),
//                        new BigDecimal(3D),
//                        new BigDecimal(5D)),
//                List.of(
//                        BigDecimal.valueOf(1E-16),
//                        new BigDecimal(1E-23),
//                        new BigDecimal(1E-64),
//                        new BigDecimal(1E-16)
//                ));
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/Sec.csv")
    public void testStub(BigDecimal x, BigDecimal precision, BigDecimal expect){
        try {
            BigDecimal result = secMock.calculate(x, precision);
            assertEquals(expect.doubleValue(), result.doubleValue());
        } catch (ArithmeticException e) {
            assertEquals("x is less than or equals to 0", e.getMessage());
        }
    }
}
