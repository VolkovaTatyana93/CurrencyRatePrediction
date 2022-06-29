import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;

public class ReaderCurrency {
    private int indexNominal;
    private int indexCurs;
    private static final String NOMINAL = "nominal";
    private static final String CURS = "curs";

    /**
     * Считывает последние 7 курсов из файла валют и возвращет их
     *
     * @param nameCurrency - валюта, например = USD, TRY,EUR
     * @return последние 7 курсов валют номиналом 1
     */
    public LinkedList<Double> readerCurrencyFromFile(String nameCurrency) {
        LinkedList<Double> lastSevenCurrency = new LinkedList<>();
        try (InputStream resource = this.getClass().getResourceAsStream("RC_F01_06_2002_T17_06_2022_" + nameCurrency + ".csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(resource))) {
            String[] header = (br.readLine()).split(";");
            indexNominal = Arrays.asList(header).indexOf(NOMINAL);
            indexCurs = Arrays.asList(header).indexOf(CURS);
            int countCurrency = 7;
            for (int i = 0; i < countCurrency; i++) {
                lastSevenCurrency.add(parseCurrency(br.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при считывании из файла");
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
        try {
            String[] mas = currency.split(";");
            String curs = mas[indexCurs].replace("\"", "").trim();
            DecimalFormat decimalFormat = new DecimalFormat("##,####");
            double cursDouble = (double) decimalFormat.parse(curs);
            return cursDouble / Double.valueOf(mas[indexNominal]);

        } catch (NumberFormatException | ParseException e) {
            throw new NumberFormatException("Ошибка - в файле номинал или валюта не является числом");
        }
    }
}
