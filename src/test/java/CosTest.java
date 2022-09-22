import functions.trigonometry.Cos;
import functions.trigonometry.Sin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import trigonometry.SinStub;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CosTest {

    private static SinStub sinStub;

    @BeforeAll
    public static void init(){
        sinStub = new SinStub();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Cos.csv")
    public void testWithMock(BigDecimal x, BigDecimal precision, BigDecimal result){
        Cos cos = new Cos(sinStub.sinMock);
        assertEquals(result, cos.calculate(x, precision));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Cos.csv")
    public void testWithNoMock(BigDecimal x, BigDecimal precision, BigDecimal result){
        Cos cos = new Cos(new Sin());
        assertEquals(result, cos.calculate(x, precision));
    }
}
