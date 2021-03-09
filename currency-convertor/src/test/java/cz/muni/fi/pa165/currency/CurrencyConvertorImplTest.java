package cz.muni.fi.pa165.currency;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyConvertorImplTest {

    private final Currency eur = Currency.getInstance("EUR");
    private final Currency czk = Currency.getInstance("CZK");

    @Test
    public void testConvert() {
        var exchangeRate = new BigDecimal("25.21");

        var mock = createMock(eur, czk, exchangeRate);
        var convertor = new CurrencyConvertorImpl(mock);

        var amount = new BigDecimal("100");
        assertThat(convertor.convert(eur, czk, amount))
                .isEqualTo(amount.multiply(exchangeRate));
    }

    @Test
    public void testConvertWithNullSourceCurrency() {
        var exchangeRate = new BigDecimal("27.22");

        var mock = createMock(null, czk, exchangeRate);
        var convertor = new CurrencyConvertorImpl(mock);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> convertor.convert(null, czk, new BigDecimal("200")));
    }

    @Test
    public void testConvertWithNullTargetCurrency() {
        var exchangeRate = new BigDecimal("26.22");

        var mock = createMock(eur, null, exchangeRate);
        var convertor = new CurrencyConvertorImpl(mock);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> convertor.convert(eur, null, new BigDecimal("200")));
    }

    @Test
    public void testConvertWithNullSourceAmount() {
        var exchangeRate = new BigDecimal("26.22");

        var mock = createMock(eur, czk, exchangeRate);
        var convertor = new CurrencyConvertorImpl(mock);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> convertor.convert(eur, czk, null));
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
            if (source == null || target == null) {
                when(exchange.getExchangeRate(source, target))
                        .thenThrow(new IllegalArgumentException("One of the argument is null."));
            }
            return exchange;
        } catch (ExternalServiceFailureException e) {
            throw new RuntimeException("Mock configuration failed", e);
        }
    }
}
