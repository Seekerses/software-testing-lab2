package trigonometry;

import functions.trigonometry.Cos;
import logarithm.Filler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CosStub {
    public final Cos cosMock = mock(Cos.class);

    public CosStub(){
        Filler.fillStub("./src/test/resources/in/Cos.csv", cosMock);
    }


//    @Test
//    public void create() throws IOException {
//        Cos cos = new Cos(new Sin());
//        cos.exportValue(Path.of("./src/test/resources/in/Cos.csv"), List.of(
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
    @CsvFileSource(resources = "/out/Cos.csv")
    public void testStub(BigDecimal x, BigDecimal precision, BigDecimal expect){
            BigDecimal result = cosMock.calculate(x, precision);
            assertEquals(expect.doubleValue(), result.doubleValue());
    }
}
