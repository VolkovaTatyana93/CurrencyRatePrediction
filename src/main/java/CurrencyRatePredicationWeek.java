import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class CurrencyRatePredicationWeek {
    private LinkedList<Double> currencies;
    private final CurrencySupportApproaches currencySupportApproaches = new CurrencySupportApproaches();

    /**
     * Прогнозирует курс переданной валюты на неделю
     *
     * @param nameCurrency - наименование валюты
     */
    public List<Currency> getCurrencyRateWeek(String nameCurrency) {
        ReaderCurrency readerCurrency = new ReaderCurrency();
        currencies = readerCurrency.readerCurrencyFromFile(nameCurrency);
        List<Currency> cursWeek = new LinkedList<Currency>();
        LocalDate date = LocalDate.now().plusDays(1);
        for (int i = 0; i < 7; i++) {
            Currency curs = new Currency(date, currencySupportApproaches.arithmeticalMean(currencies));
            cursWeek.add(curs);
            date = date.plusDays(1);
            currencies.add(currencySupportApproaches.arithmeticalMean(currencies));
            currencies.removeFirst();
        }
        return cursWeek;
    }

    /**
     * Распечатывает прогнозирование валюты на неделю
     *
     * @param curses - мапа, где ключ - дата, значение - курс валюты
     */
    public void printCurrencyRateWeek(List<Currency> curses) {
        for (Currency curs : curses) {
            currencySupportApproaches.printCurrency(curs.getDate(), curs.getCurrency());
        }
    }
}
