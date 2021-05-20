package article.types;

import article.Article;

public class Magazine extends Article {
    private String publisher;
    private String issueNumber;

    public Magazine() {
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public String toString() {
        return super.toString() +
                " WYDAWNICTWO: " + publisher +
                " NUMER WYDANIA: " + issueNumber;
    }
}
