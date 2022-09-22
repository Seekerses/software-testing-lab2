import functions.logarithm.Ln;
import functions.logarithm.Log;
import logarithm.LnStub;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogTest {

    private static LnStub lnStub;

    @BeforeAll
    public static void init(){
        lnStub = new LnStub();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Log.csv")
    public void testWithMock(BigDecimal x, BigDecimal base, BigDecimal precision, BigDecimal expected){
        Log log = new Log(lnStub.lnMock, base);
        assertEquals(expected, log.calculateWithBase(x, base, precision));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Log.csv")
    public void testWithMoMock(BigDecimal x, BigDecimal base, BigDecimal precision, BigDecimal expected){
        Log log = new Log(new Ln(), base);
        assertEquals(expected, log.calculateWithBase(x, base, precision));
    }
}
