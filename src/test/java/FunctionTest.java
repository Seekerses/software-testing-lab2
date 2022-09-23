import functions.MysteryFunction;
import functions.logarithm.Ln;
import functions.logarithm.Log;
import functions.trigonometry.Cos;
import functions.trigonometry.Csc;
import functions.trigonometry.Sec;
import functions.trigonometry.Sin;
import logarithm.LnStub;
import logarithm.LogStub;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import trigonometry.CosStub;
import trigonometry.CscStub;
import trigonometry.SecStub;
import trigonometry.SinStub;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionTest {

    private static SecStub secStub;
    private static CscStub cscStub;
    private static SinStub sinStub;
    private static CosStub cosStub;
    private static LnStub lnStub;
    private static LogStub logStub;

    @BeforeAll
    public static void init() throws IOException {
        secStub = new SecStub();
        cosStub = new CosStub();
        sinStub = new SinStub();
        cscStub = new CscStub();
        lnStub = new LnStub();
        logStub = new LogStub();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Function.csv")
    public void fullMockTest(BigDecimal x, BigDecimal precision, BigDecimal expected){
        MysteryFunction mysteryFunction = new MysteryFunction(sinStub.sinMock, cosStub.cosMock, secStub.secMock,
                cscStub.cscMock, lnStub.lnMock, logStub.logMock);
        assertEquals(expected, mysteryFunction.calculate(x, precision));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Function.csv")
    public void lnMockTest(BigDecimal x, BigDecimal precision, BigDecimal expected){
        MysteryFunction mysteryFunction = new MysteryFunction(new Sin(), new Cos(new Sin()), new Sec(new Cos(new Sin())),
                new Csc(new Sin()), lnStub.lnMock, new Log(new Ln(), BigDecimal.valueOf(2D)));
        assertEquals(expected, mysteryFunction.calculate(x, precision));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Function.csv")
    public void logMockTest(BigDecimal x, BigDecimal precision, BigDecimal expected){
        MysteryFunction mysteryFunction = new MysteryFunction(new Sin(), new Cos(new Sin()), new Sec(new Cos(new Sin())),
                new Csc(new Sin()), new Ln(), logStub.logMock);
        assertEquals(expected, mysteryFunction.calculate(x, precision));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Function.csv")
    public void cosMockTest(BigDecimal x, BigDecimal precision, BigDecimal expected){
        MysteryFunction mysteryFunction = new MysteryFunction(new Sin(), cosStub.cosMock, new Sec(new Cos(new Sin())),
                new Csc(new Sin()), new Ln(), new Log(new Ln(), BigDecimal.valueOf(2D)));
        assertEquals(expected, mysteryFunction.calculate(x, precision));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Function.csv")
    public void sinMockTest(BigDecimal x, BigDecimal precision, BigDecimal expected){
        MysteryFunction mysteryFunction = new MysteryFunction(sinStub.sinMock, new Cos(new Sin()), new Sec(new Cos(new Sin())),
                new Csc(new Sin()), new Ln(), new Log(new Ln(), BigDecimal.valueOf(2D)));
        assertEquals(expected, mysteryFunction.calculate(x, precision));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Function.csv")
    public void secMockTest(BigDecimal x, BigDecimal precision, BigDecimal expected){
        MysteryFunction mysteryFunction = new MysteryFunction(new Sin(), new Cos(new Sin()), secStub.secMock,
                new Csc(new Sin()), new Ln(), new Log(new Ln(), BigDecimal.valueOf(2D)));
        assertEquals(expected, mysteryFunction.calculate(x, precision));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/out/Function.csv")
    public void cscMockTest(BigDecimal x, BigDecimal precision, BigDecimal expected){
        MysteryFunction mysteryFunction = new MysteryFunction(new Sin(), new Cos(new Sin()), new Sec(new Cos(new Sin())),
                cscStub.cscMock, new Ln(), new Log(new Ln(), BigDecimal.valueOf(2D)));
        assertEquals(expected, mysteryFunction.calculate(x, precision));
    }
}
