import java.time.LocalDate;
import java.util.*;

public class CurrencyRatePredication {
    private static final String TOMORROW = "tomorrow";
    private static final String WEEK = "week";
    private LinkedList<Double> currencies;
    private final CurrencySupportApproaches currencySupportApproaches = new CurrencySupportApproaches();

    /**
     * Считывает команду с консоли и запускает нужный метод
     */
    public void currencyRate() {
        System.out.println("Введите команду формата : rate EUR/TRY/USD week/tomorrow");
        System.out.println("Например, rate USD week");
        try (Scanner scanner = new Scanner(System.in)) {
            String command = scanner.nextLine();
            while (!command.equals("exit")) {
                String[] commandArr = command.split("\\s+");
                if (commandArr.length != 3) {
                    System.out.println("Неверно введена команда, попробуйте ещё раз");
                } else if (Arrays.stream(CurrencyName.values()).noneMatch((c) -> c.name().equals(commandArr[1]))) {
                    System.out.println("Данной валюты нет в списке вычисляемых валют");
                } else if (!(commandArr[2].equalsIgnoreCase(TOMORROW) || commandArr[2].equalsIgnoreCase(WEEK))) {
                    System.out.println("За данный период невозможно рассчитать");
                } else {
                    if (commandArr[2].equalsIgnoreCase(TOMORROW)) {
                        CurrencyRatePredicationTomorrow rateTomorrow = new CurrencyRatePredicationTomorrow();
                        double curs = rateTomorrow.getCurrencyRateTomorrow(commandArr[1]);
                        rateTomorrow.printCurrencyRateTomorrow(curs);
                    } else if (commandArr[2].equalsIgnoreCase(WEEK)) {
                        CurrencyRatePredicationWeek rateWeek = new CurrencyRatePredicationWeek();
                        List<Currency> currencyWeek = rateWeek.getCurrencyRateWeek(commandArr[1]);
                        rateWeek.printCurrencyRateWeek(currencyWeek);
                    }
                }
                command = scanner.nextLine();
            }
        }
    }

}
