package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import cz.muni.fi.pa165.currency.ExchangeRateTableImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("cz.muni.fi.pa165.currency")
public class JavaConfig {
    @Bean
    public CurrencyConvertor currencyConvertor(ExchangeRateTableImpl exchangeRateTable) {
        return new CurrencyConvertorImpl(exchangeRateTable);
    }
}
