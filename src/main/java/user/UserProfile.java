package user;

import java.util.List;

public class UserProfile {
    private Long id;
    private PersonalData personalData;
    private List<Integer> borrowedBooksId;
    private List<Integer> borrowedMagazinesId;
    private List<Integer> borrowedFilmsId;

    public UserProfile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public List<Integer> getBorrowedBooksId() {
        return borrowedBooksId;
    }

    public void setBorrowedBooksId(List<Integer> borrowedBooksId) {
        this.borrowedBooksId = borrowedBooksId;
    }

    public List<Integer> getBorrowedMagazinesId() {
        return borrowedMagazinesId;
    }

    public void setBorrowedMagazinesId(List<Integer> borrowedMagazinesId) {
        this.borrowedMagazinesId = borrowedMagazinesId;
    }

    public List<Integer> getBorrowedFilmsId() {
        return borrowedFilmsId;
    }

    public void setBorrowedFilmsId(List<Integer> borrowedFilmsId) {
        this.borrowedFilmsId = borrowedFilmsId;
    }
}
