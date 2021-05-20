package article.types;

import article.Article;

public class Book extends Article {
    private int numberOfPages;
    private int isbnNumber;

    public Book() {
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(int isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    @Override
    public String toString() {
        return super.toString() +
                " ILOŚC STRON: " + numberOfPages +
                " NUMER ISBN: " + isbnNumber;
    }
}
