import java.time.LocalDate;
import java.util.LinkedList;

public class CurrencyRatePredicationTomorrow {
    private LinkedList<Double> currencies;
    private final CurrencySupportApproaches currencySupportApproaches = new CurrencySupportApproaches();

    /**
     * Прогнозирует курс переданной валюты на завтра
     *
     * @param NameCurrency - наименование валюты
     */
    public double getCurrencyRateTomorrow(String NameCurrency) {
        ReaderCurrency readerCurrency = new ReaderCurrency();
        currencies = readerCurrency.readerCurrencyFromFile(NameCurrency);
        return currencySupportApproaches.arithmeticalMean(currencies);
    }

    /**
     * Распечатывает курс на завтра
     *
     * @param curs
     */
    public void printCurrencyRateTomorrow(double curs) {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        currencySupportApproaches.printCurrency(tomorrow, curs);
    }
}
