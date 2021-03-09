package cz.muni.fi.pa165.currency;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyConvertorImplTest {

    @Test
    public void testConvert() {
        var eur = Currency.getInstance("EUR");
        var czk = Currency.getInstance("CZK");
        var exchangeRate = new BigDecimal("25.21");

        var mock = createMock(eur, czk, exchangeRate);
        var convertor = new CurrencyConvertorImpl(mock);

        var amount = new BigDecimal("100");
        assertThat(convertor.convert(eur, czk, amount))
                .isEqualTo(amount.multiply(exchangeRate));
    }

    @Test
    public void testConvertWithNullSourceCurrency() {
        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithNullTargetCurrency() {
        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithNullSourceAmount() {
        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithUnknownCurrency() {
        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithExternalServiceFailure() {
        fail("Test is not implemented yet.");
    }

    public ExchangeRateTable createMock(Currency source, Currency target, BigDecimal result) {
        try {
            var exchange = mock(ExchangeRateTable.class);
            when(exchange.getExchangeRate(source, target)).thenReturn(result);
            return exchange;
        } catch (ExternalServiceFailureException e) {
            throw new RuntimeException("Mock configuration failed", e);
        }
    }
}
