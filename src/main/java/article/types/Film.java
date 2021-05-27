package article.types;

import article.Article;

public class Film extends Article {
    private int durationInMinutes;
    private int yearOfProduction;

    public Film() {
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public String toString() {
        return super.toString() + " CZAS TRWANIA: " + durationInMinutes + "min" + " ROK PRODUKCJI: " + yearOfProduction;
    }
}
