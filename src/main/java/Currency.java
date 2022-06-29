import java.time.LocalDate;

public class Currency {
    private LocalDate date;
    private Double currency;
    private CurrencyName name;

    public Currency(LocalDate date, Double currency) {
        this.currency = currency;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getCurrency() {
        return currency;
    }


}
