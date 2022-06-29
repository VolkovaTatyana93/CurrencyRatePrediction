import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.DoubleStream;

public class CurrencySupportApproaches {
    /**
     * Высчитывает среднее арифметическое чисел из списка
     *
     * @param list - переданный список
     * @return - среднее арифметическое
     */
    public Double arithmeticalMean(@NotNull List<Double> list) {
        DoubleStream doubleStream = list.stream().mapToDouble(value -> value);
        return doubleStream.average().getAsDouble();
    }


    /**
     * Распечатывает переданную дату и курс валюты за этот день
     *
     * @param date     - дата
     * @param currency - курс валюты
     */
    public void printCurrency(LocalDate date, Double currency) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("E dd.MM.yyyy"));
        String currency2 = String.format("%.2f", currency);
        System.out.println(formattedDate + " - " + currency2);
    }
}
