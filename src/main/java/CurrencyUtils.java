import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CurrencyUtils {

    /**
     * Считывает последние 7 курсов из файла валют и возвращет их
     *
     * @param nameCurrency - валюта, например = USD, TRY,EUR
     * @return последние 7 курсов валют номиналом 1
     */
    public LinkedList<Double> readerCurrencyFromFile(String nameCurrency) {
        String fileName = "./src/main/resources/RC_F01_06_2002_T17_06_2022_" + nameCurrency + ".csv";

        LinkedList<Double> lastSevenCurrency = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            br.readLine();
            int countCurrency = 7;
            for (int i = 0; i < countCurrency; i++) {
                lastSevenCurrency.add(parseCurrency(br.readLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastSevenCurrency;
    }

    /**
     * Разбирает переданную строку из файла и вычисляет курс валюты 1 номинала
     *
     * @param currency - строка из файла, включающая номинал, дату, курс валюты,название валюты
     * @return - курс валюты 1 номинала
     */
    private Double parseCurrency(String currency) {
        double result = 0.0;
        try {
            String[] mas = currency.split(";");
            double d = Double.parseDouble(mas[2].replace("\"", "").replace(" ", "").replace(",", "."));
            result = d / Double.parseDouble(mas[0]);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Высчитывает среднее арифметическое чисел из списка
     *
     * @param list - переданный список
     * @return - среднее арифметическое
     */
    public Double arithmeticalMean(@NotNull List<Double> list) {
        Double result = 0.0;
        for (Double aDouble : list) {
            result += aDouble;
        }
        return result / list.size();
    }

    /**
     * Распечатывает переданную дату и курс валюты за этот день
     *
     * @param date     - дата
     * @param currency - курс валюты
     */
    public void printCurrency(@NotNull Calendar date, Double currency) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd.MM.yyyy");
        String currency2 = String.format("%.2f", currency);
        System.out.println(simpleDateFormat.format(date.getTime()) + " - " + currency2);
    }

    /**
     * Расчитывает дату завтрашнего дня
     *
     * @return - дату завтра
     */
    public Calendar getTommorow() {
        Date date = new Date();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTime(date);
        tomorrow.add(Calendar.DATE, 1);
        return tomorrow;
    }
}
