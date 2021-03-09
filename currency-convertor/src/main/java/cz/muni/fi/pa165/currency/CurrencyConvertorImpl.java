package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;


/**
 * This is base implementation of {@link CurrencyConvertor}.
 *
 * @author petr.adamek@embedit.cz
 */
public class CurrencyConvertorImpl implements CurrencyConvertor {

    private final ExchangeRateTable exchangeRateTable;
    //private final Logger logger = LoggerFactory.getLogger(CurrencyConvertorImpl.class);

    public CurrencyConvertorImpl(ExchangeRateTable exchangeRateTable) {
        this.exchangeRateTable = exchangeRateTable;
    }

    @Override
    public BigDecimal convert(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceAmount) {
        if (sourceAmount == null) {
            throw new IllegalArgumentException("The sourceAmount argument is null");
        }

        try {
            BigDecimal exchangeRate = exchangeRateTable.getExchangeRate(sourceCurrency, targetCurrency);

            if (exchangeRate == null) {
                throw new UnknownExchangeRateException("Exchange rate is not known");
            }

            return sourceAmount.multiply(exchangeRate);
        } catch (ExternalServiceFailureException e) {
            throw new UnknownExchangeRateException("Lookup for current exchange rate failed", e);
        }
    }

}
