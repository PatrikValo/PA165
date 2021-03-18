package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class MainAnnotations {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("cz.muni.fi.pa165.currency");
        CurrencyConvertor currencyConvertor = applicationContext.getBean(CurrencyConvertor.class);

        var eur = Currency.getInstance("EUR");
        var czk = Currency.getInstance("CZK");
        System.out.println(currencyConvertor.convert(eur, czk, BigDecimal.valueOf(1)));
    }
}
