import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;

public class CurrencyRatePredication {

    private LinkedList<Double> currencies;
    private final CurrencyUtils currencyUtils = new CurrencyUtils();

    /**
     * Считывает команду с консоли и запускает нужный метод
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String[] command = scanner.nextLine().split(" ");
            CurrencyRatePredication currencyRatePredication = new CurrencyRatePredication();
            if (command[2].equals("tomorrow")) {
                currencyRatePredication.currencyRateTomorrow(command[1]);
            } else if (command[2].equals("week")) {
                currencyRatePredication.currencyRateWeek(command[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Прогнозирует курс переданной валюты на неделю и выводит в консоль
     *
     * @param nameCurrency - наименование валюты
     */
    public void currencyRateWeek(String nameCurrency) {
        CurrencyUtils currencyUtils = new CurrencyUtils();
        currencies = currencyUtils.readerCurrencyFromFile(nameCurrency);
        Calendar date = currencyUtils.getTommorow();
        for (int i = 0; i < 7; i++) {
            currencies.add(currencyUtils.arithmeticalMean(currencies));
            currencies.removeFirst();
            currencyUtils.printCurrency(date, currencyUtils.arithmeticalMean(currencies));
            date.add(Calendar.DATE, 1);
        }
    }

    /**
     * Прогнозирует курс переданной валюты на завтра и выводит в консоль
     *
     * @param NameCurrency - наименование валюты
     */
    public void currencyRateTomorrow(String NameCurrency) {
        CurrencyUtils currencyUtils = new CurrencyUtils();
        currencies = currencyUtils.readerCurrencyFromFile(NameCurrency);
        Calendar tomorrow = currencyUtils.getTommorow();
        currencyUtils.printCurrency(tomorrow, currencyUtils.arithmeticalMean(currencies));
    }

}
