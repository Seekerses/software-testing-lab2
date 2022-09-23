import functions.trigonometry.Csc;
import functions.trigonometry.Sin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import trigonometry.SinStub;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CscTest {
    private static SinStub sinStub;

    @BeforeAll
    public static void init(){
        sinStub = new SinStub();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Csc.csv")
    public void testWithMock(BigDecimal x, BigDecimal precision, BigDecimal result){
        Csc csc = new Csc(sinStub.sinMock);
        assertEquals(result, csc.calculate(x, precision));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Csc.csv")
    public void testWithNoMock(BigDecimal x, BigDecimal precision, BigDecimal result){
        Csc csc = new Csc(new Sin());
        assertEquals(result, csc.calculate(x, precision));
    }
}
