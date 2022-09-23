package trigonometry;

import functions.trigonometry.Sin;
import logarithm.Filler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class SinStub {
    public final Sin sinMock = mock(Sin.class);

    public SinStub(){
        Filler.fillStub("./src/test/resources/in/Sin.csv", sinMock);
    }


//    @Test
//    public void create() throws IOException {
//        Sin sin = new Sin();
//        sin.exportValue(Path.of("./src/test/resources/in/Sin.csv"), List.of(
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
    @CsvFileSource(resources = "/out/Sin.csv")
    public void testStub(BigDecimal x, BigDecimal precision, BigDecimal expect){
        try {
            BigDecimal result = sinMock.calculate(x, precision);
            assertEquals(expect.doubleValue(), result.doubleValue());
        } catch (ArithmeticException e) {
            assertEquals("x is less than or equals to 0", e.getMessage());
        }
    }
}
