package article;

import java.util.Date;

public abstract class Article {
    private Long id;
    private String title;
    private String authors;
    private ArticleStatus articleStatus;
    private Long borrowerId = null;
    private Date dateOfLastLoan = null;

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public ArticleStatus getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Date getDateOfLastLoan() {
        return dateOfLastLoan;
    }

    public void setDateOfLastLoan(Date dateOfLastLoan) {
        this.dateOfLastLoan = dateOfLastLoan;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " TYTUŁ: " + title +
                " AUTORZY: " + authors +
                " STATUS: " + articleStatus +
                " ID WYPOŻYCZAJĄCEJO: " + borrowerId +
                " DATA OSTATNIEGO WYPOŻYCZENIA: " + dateOfLastLoan;
    }
}
