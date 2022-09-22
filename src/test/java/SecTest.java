import functions.trigonometry.Cos;
import functions.trigonometry.Sec;
import functions.trigonometry.Sin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import trigonometry.CosStub;
import trigonometry.SinStub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;

public class SecTest {
    private static CosStub cosStub;

    @BeforeAll
    public static void init(){
        cosStub = new CosStub();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Sec.csv")
    public void testWithMock(BigDecimal x, BigDecimal precision, BigDecimal result){
        Sec sec = new Sec(cosStub.cosMock);
        assertEquals(result, sec.calculate(x, precision));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Sec.csv")
    public void testWithNoMock(BigDecimal x, BigDecimal precision, BigDecimal result){
        Sec sec = new Sec(new Cos(new Sin()));
        assertEquals(result, sec.calculate(x, precision));
    }
}
