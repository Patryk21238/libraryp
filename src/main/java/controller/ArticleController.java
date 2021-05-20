package controller;

import static controller.PackageRangeFunctions.scannInt;
import static controller.PackageRangeFunctions.scannString;

import article.ArticleStatus;
import article.types.Book;
import article.types.Film;
import article.types.Magazine;
import user.UserProfile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleController {


    public static void addArticle(List<Book> books, List<Magazine> magazines, List<Film> films) {
        System.out.printf("Wybierz typ artykułu jaki chcesz dodać:" + pickMessage);
        int inputNumber;
        do {
            inputNumber = scannInt();
            switch (inputNumber) {
                case 0:
                    break;
                case 1:
                    addBook(books);
                    break;
                case 2:
                    addMagazine(magazines);
                    break;
                case 3:
                    addFilm(films);
                    break;

                default:
                    System.err.println("Podaj prawidłową wartość!");
                    break;
            }

        } while (inputNumber < 0 || inputNumber > 3);
    }

    public static void deleteArticle(List<Book> books, List<Magazine> magazines, List<Film> films) {
        System.out.printf("WYBIERZ" +
                "\n\t1 - aby usunąć artykuły o danym tytule" +
                "\n\t2 - aby usunąć artykuły o danym numerze id" +
                "\n\t0 - aby cofnąć");
        int inputNumber;
        do {
            inputNumber = scannInt();
            switch (inputNumber) {
                case 0:
                    break;
                case 1:
                    deleteAllArticlesByTitle(books, magazines, films);
                    break;
                case 2:
                    deleteArticleById(books, magazines, films);
                    break;
                default:
                    System.err.println("Podaj prawidłową wartość!");
                    break;
            }

        } while (inputNumber != 0);
    }

    public static void lendArticle(List<Book> books, List<Magazine> magazines, List<Film> films, List<UserProfile> userProfiles) {
        UserProfile user = UserController.pickUserByPesel(userProfiles);
        System.out.printf("Wybierz typ artykułu jaki do wypożyczenia:" + pickMessage);
        int inputNumber;
        System.out.print("Podaj id artykułu:");
        int id = scannInt();
        do {
            inputNumber = scannInt();
            switch (inputNumber) {
                case 0:
                    break;
                case 1:
                    lendBook(books, user, id);
                    break;
                case 2:
                    lendMagazine(magazines, user, id);
                    break;
                case 3:
                    lendFilm(films, user, id);
                    break;

                default:
                    System.err.println("Podaj prawidłową wartość!");
                    break;
            }

        } while (inputNumber < 0 || inputNumber > 3);
    }

    public static void returnArticle(List<Book> books, List<Magazine> magazines, List<Film> films, List<UserProfile> userProfiles) {
        System.out.printf("Wybierz typ artykułu jaki do zwrotu:" + pickMessage);
        int inputNumber;
        System.out.print("Podaj id artykułu:");
        int atricleId = scannInt();
        do {
            inputNumber = scannInt();
            switch (inputNumber) {
                case 0:
                    break;
                case 1:
                    returnBook(books, atricleId, userProfiles);
                    break;
                case 2:
                    returnMagazine(magazines, atricleId, userProfiles);
                    break;
                case 3:
                    returnFilm(films, atricleId, userProfiles);
                    break;

                default:
                    System.err.println("Podaj prawidłową wartość!");
                    break;
            }

        } while (inputNumber < 0 || inputNumber > 3);
    }

    public static void printArticlesList(List<Book> books, List<Magazine> magazines, List<Film> films){
        System.out.printf("Wybierz typ artykułów do wyświetlenia:" + pickMessage);
        int inputNumber;
        do {
            inputNumber = scannInt();
            switch (inputNumber) {
                case 0:
                    break;
                case 1:
                    books.stream().forEach(e -> System.out.printf(e.toString() + "\n#\n"));
                    break;
                case 2:
                    magazines.stream().forEach(e -> System.out.printf(e.toString() + "\n#\n"));
                    break;
                case 3:
                    films.stream().forEach(e -> System.out.printf(e.toString() + "\n#\n"));
                    break;

                default:
                    System.err.println("Podaj prawidłową wartość!");
                    break;
            }

        } while (inputNumber < 0 || inputNumber > 3);
    }

    private static void deleteAllArticlesByTitle(List<Book> books, List<Magazine> magazines, List<Film> films) {
        System.out.print("Podaj tytuł artykułu do usunięcia:");
        String articleTittle = scannString();
        System.out.printf("Wybierz typ artykułu jaki chcesz usunąć:" + pickMessage);
        int inputNumber;
        do {
            inputNumber = scannInt();
            switch (inputNumber) {
                case 0:
                    break;
                case 1:
                    int booksBeforeRemoving = books.size();
                    books.stream().filter(e ->
                            !e.getTitle().equals(articleTittle))
                            .collect(Collectors.toList());
                    if(booksBeforeRemoving == books.size()){
                        System.out.println("Nie ma książki o wprowadzonym tytule w bibliotece.");
                    }
                    break;
                case 2:
                    int magazinesBeforeRemoving = magazines.size();
                    magazines.stream().filter(e ->
                            !e.getTitle().equals(articleTittle))
                            .collect(Collectors.toList());
                    if(magazinesBeforeRemoving == magazines.size()){
                        System.out.println("Nie ma magazynu o wprowadzonym tytule w bibliotece.");
                    }
                    break;
                case 3:
                    int filmsBeforeRemoving = magazines.size();
                    films.stream().filter(e ->
                            !e.getTitle().equals(articleTittle))
                            .collect(Collectors.toList());
                    if(filmsBeforeRemoving == films.size()){
                        System.out.println("Nie ma filmu o wprowadzonym tytule w bibliotece.");
                    }
                    break;

                default:
                    System.err.println("Podaj prawidłową wartość!");
                    break;
            }

        } while (inputNumber < 0 || inputNumber > 3);
    }


    private static void deleteArticleById(List<Book> books, List<Magazine> magazines, List<Film> films) {
        System.out.printf("Wybierz typ artykułu jaki chcesz usunąć:" + pickMessage);
        int inputNumber;
        System.out.print("Podaj id artykułu do usunięcia:");
        int id = scannInt();
        do {
            inputNumber = scannInt();
            switch (inputNumber) {
                case 0:
                    break;
                case 1:
                    books.remove(id);
                    break;
                case 2:
                    magazines.remove(id);
                    break;
                case 3:
                    films.remove(id);
                    break;

                default:
                    System.err.println("Podaj prawidłową wartość!");
                    break;
            }

        } while (inputNumber < 0 || inputNumber > 3);
    }







    private static void returnFilm(List<Film> films, int id, List<UserProfile> userProfiles) {
        Film tempFilm = films.get(id);
        tempFilm.setArticleStatus(ArticleStatus.AVAILABLE);
        tempFilm.setBorrowerId(null);
        films.set(id, tempFilm);
        deleteIdElementFromUserBorrowedFilms(userProfiles, tempFilm);
    }

    private static void deleteIdElementFromUserBorrowedFilms(List<UserProfile> userProfiles, Film tempFilm){
        UserProfile user = UserController.pickUserById(userProfiles, tempFilm.getBorrowerId());
        List<Integer> tempBorrowedIdList= user.getBorrowedFilmsId();
        Integer t = tempBorrowedIdList.stream()
                .filter(e -> e.intValue() != tempFilm.getId())
                .findFirst()
                .get();
        tempBorrowedIdList.remove(t);

        user.setBorrowedFilmsId(tempBorrowedIdList);
    }

    private static void returnMagazine(List<Magazine> magazines, int id, List<UserProfile> userProfiles) {
        Magazine tempMagazine = magazines.get(id);
        tempMagazine.setArticleStatus(ArticleStatus.AVAILABLE);
        tempMagazine.setBorrowerId(null);
        magazines.set(id, tempMagazine);
        deleteIdElementFromUserBorrowedMagazines(userProfiles, tempMagazine);
    }

    private static void deleteIdElementFromUserBorrowedMagazines(List<UserProfile> userProfiles, Magazine tempMagazine){
        UserProfile user = UserController.pickUserById(userProfiles, tempMagazine.getBorrowerId());
        List<Integer> tempBorrowedIdList= user.getBorrowedMagazinesId();
        Integer t = tempBorrowedIdList.stream()
                .filter(e -> e.intValue() != tempMagazine.getId())
                .findFirst()
                .get();
        tempBorrowedIdList.remove(t);

        user.setBorrowedMagazinesId(tempBorrowedIdList);
    }

    private static void returnBook(List<Book> books, int id, List<UserProfile> userProfiles) {
        Book tempBook = books.get(id);
        tempBook.setArticleStatus(ArticleStatus.AVAILABLE);
        tempBook.setBorrowerId(null);
        books.set(id, tempBook);
        deleteIdElementFromUserBorrowedBooks(userProfiles, tempBook);
    }

    private static void deleteIdElementFromUserBorrowedBooks(List<UserProfile> userProfiles, Book tempBook){
        UserProfile user = UserController.pickUserById(userProfiles, tempBook.getBorrowerId());
        List<Integer> tempBorrowedIdList= user.getBorrowedBooksId();
        Integer t = tempBorrowedIdList.stream()
                .filter(e -> e.intValue() != tempBook.getId())
                .findFirst()
                .get();
        tempBorrowedIdList.remove(t);

        user.setBorrowedBooksId(tempBorrowedIdList);
    }

    private static void lendBook(List<Book> books, UserProfile user, int id) {
        Book tempBook = books.get(id);
        tempBook.setArticleStatus(ArticleStatus.LENTED);
        tempBook.setDateOfLastLoan(new Date(System.currentTimeMillis()));
        tempBook.setBorrowerId(user.getId());
        books.set(id, tempBook);

        List<Integer> tempUserBorrowedBooksId = user.getBorrowedBooksId();
        tempUserBorrowedBooksId.add(id);
        user.setBorrowedBooksId(tempUserBorrowedBooksId);
    }

    private static void lendMagazine(List<Magazine> magazines, UserProfile user, int id) {
        Magazine tempMagazine = magazines.get(id);
        tempMagazine.setArticleStatus(ArticleStatus.LENTED);
        tempMagazine.setDateOfLastLoan(new Date(System.currentTimeMillis()));
        tempMagazine.setBorrowerId(user.getId());
        magazines.set(id, tempMagazine);

        List<Integer> tempUserBorrowedMagazinesId = user.getBorrowedMagazinesId();
        tempUserBorrowedMagazinesId.add(id);
        user.setBorrowedMagazinesId(tempUserBorrowedMagazinesId);
    }

    private static void lendFilm(List<Film> films, UserProfile user, int id) {
        Film tempFilm = films.get(id);
        tempFilm.setArticleStatus(ArticleStatus.LENTED);
        tempFilm.setDateOfLastLoan(new Date(System.currentTimeMillis()));
        tempFilm.setBorrowerId(user.getId());
        films.set(id, tempFilm);

        List<Integer> tempUserBorrowedFilmsId = user.getBorrowedFilmsId();
        tempUserBorrowedFilmsId.add(id);
        user.setBorrowedFilmsId(tempUserBorrowedFilmsId);
    }

    private static void addBook(List<Book> books) {
        Book newBook = new Book();

        //try catch indexoutofboudsexceptions
        Long newBookId = books.get(books.size() - 1).getId() + 1;
        newBook.setId(newBookId);
        System.out.print("Podaj tytuł: ");
        newBook.setTitle(scannString());
        System.out.print("Podaj autorów: ");
        newBook.setAuthors(scannString());
        newBook.setArticleStatus(ArticleStatus.AVAILABLE);
        System.out.print("Podaj ilość stron: ");
        newBook.setNumberOfPages(scannInt());
        System.out.print("Podaj numer ISBN: ");
        newBook.setIsbnNumber(scannInt());

        books.add(newBook);
    }

    private static void addMagazine(List<Magazine> magazines) {
        Magazine newMagazine = new Magazine();

    //try catch indexoutofboudsexceptions
        Long newMagazineId = magazines.get(magazines.size() - 1).getId() + 1;
        newMagazine.setId(newMagazineId);
        System.out.print("Podaj tytuł: ");
        newMagazine.setTitle(scannString());
        System.out.print("Podaj autorów: ");
        newMagazine.setAuthors(scannString());
        newMagazine.setArticleStatus(ArticleStatus.AVAILABLE);
        System.out.print("Podaj nazwę wydawnictwa: ");
        newMagazine.setPublisher(scannString());
        System.out.print("Podaj numer wydania: ");
        newMagazine.setIssueNumber(scannString());

        magazines.add(newMagazine);

    }

    private static void addFilm(List<Film> films) {
        Film newFilm = new Film();

        //try catch indexoutofboudsexceptions
        Long newFilmId = films.get(films.size() - 1).getId() + 1;
        newFilm.setId(newFilmId);
        System.out.print("Podaj tytuł: ");
        newFilm.setTitle(scannString());
        System.out.print("Podaj autorów: ");
        newFilm.setAuthors(scannString());
        newFilm.setArticleStatus(ArticleStatus.AVAILABLE);
        System.out.print("Podaj czas trwania w minutach: ");
        newFilm.setDurationInMinutes(scannInt());
        System.out.print("Podaj rok produkcji: ");
        newFilm.setYearOfProduction(scannInt());

        films.add(newFilm);
    }


    private final static String pickMessage = "\n\t-wybierz 1 aby dodać książkę\n\t-wybierz 2 aby dodać czasopismo\n\t" +
            "-wybierz 3 aby dodać film\n\t-wybierz 0 aby cofnąć\n";
}
