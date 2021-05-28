package controller;

import static controller.FunctionsToUseInThisPackage.scannInt;
import static controller.FunctionsToUseInThisPackage.scannString;

import article.ArticleStatus;
import article.types.Book;
import article.types.Film;
import article.types.Magazine;
import user.UserProfile;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ArticleController {

    public static void addArticle(List<Book> books, List<Magazine> magazines, List<Film> films) {
        System.out.printf("Wybierz typ artykułu jaki chcesz dodać:"
                + "\n\t-wybierz 1 aby dodać książkę\n\t-wybierz 2 aby dodać czasopismo\n\t"
                + "-wybierz 3 aby dodać film\n\t-wybierz 0 aby cofnąć\n\t:");
        int inputNumber;
        do {
            inputNumber = scannInt();
            switch (inputNumber) {
                case 0:
                    break;
                case 1:
                    addBook(books);
                    inputNumber = 0;
                    break;
                case 2:
                    addMagazine(magazines);
                    inputNumber = 0;
                    break;
                case 3:
                    addFilm(films);
                    inputNumber = 0;
                    break;

                default:
                    System.err.println("Podaj prawidłową wartość!\n\t:");
                    break;
            }

        } while (inputNumber != 0);
    }

    public static void deleteArticle(List<Book> books, List<Magazine> magazines, List<Film> films) {
        System.out.printf("WYBIERZ" + "\n\t1 - aby usunąć artykuły o danym tytule"
                + "\n\t2 - aby usunąć artykuły o danym numerze id" + "\n\t0 - aby cofnąć\n\t:");
        int inputNumber;
        do {

            inputNumber = scannInt();
            switch (inputNumber) {
                case 0:
                    break;
                case 1:
                    deleteAllArticlesByTitle(books, magazines, films);
                    inputNumber = 0;
                    break;
                case 2:
                    deleteArticleById(books, magazines, films);
                    inputNumber = 0;
                    break;
                default:
                    System.err.println("Podaj prawidłową wartość\n\t:");
                    break;
            }

        } while (inputNumber != 0);
    }

    public static void lendArticle(List<Book> books, List<Magazine> magazines, List<Film> films,
            List<UserProfile> userProfiles) {

        UserProfile user;
        addNewUserIfUserListIsEmpty(userProfiles);
        user = UserController.pickUserByPesel(userProfiles);

        System.out.printf("Wybierz typ artykułu jaki do wypożyczenia:" + "1.Książkę, 2.Czasopismo, 3.Film\n\t:");
        int inputNumber = pickArticleType();
        boolean listIsEmpty = checkIfListIsEmpty(books, magazines, films, inputNumber);

        if (!listIsEmpty) {
            int id = pickId(books, magazines, films, inputNumber, "Podaj id artykułu\n\t:");
            choseArticleToLend(books, magazines, films, user, inputNumber, id);
            System.out.printf("Poprawnie wypożyczono!\n\t");
        } else {
            System.out.printf("Brak pozycji do wypożyczenia!\n\t");

        }

    }

    private static void choseArticleToLend(List<Book> books, List<Magazine> magazines, List<Film> films,
            UserProfile user, int inputNumber, int id) {
        switch (inputNumber) {
            case 0:
                break;
            case 1:
                if (books.get(id - 1).getBorrowerId() == null)
                    lendBook(books, user, id - 1);
                else {
                    System.out.print("Ksiązka została już wypożyczona\n\t");
                }
                break;
            case 2:
                if (magazines.get(id - 1).getBorrowerId() == null)
                    lendMagazine(magazines, user, id - 1);
                else {
                    System.out.print("Czasopismo zostało już wypożyczone\n\t");
                }

                break;
            case 3:
                if (films.get(id - 1).getBorrowerId() == null)
                    lendFilm(films, user, id - 1);
                else {
                    System.out.print("Film został już wypożyczony\n\t");
                }
                break;

            default:
                System.err.println("Podaj prawidłową wartość!\n\t:");
                break;
        }
    }

    private static int pickId(List<Book> books, List<Magazine> magazines, List<Film> films, int inputNumber, String s) {
        System.out.print(s);
        int id;
        boolean valid = true;
        do {
            id = scannInt();
            if (id > 0 && inputNumber == 1 && books.size() >= id) {
                valid = false;
            }
            if (id > 0 && inputNumber == 2 && magazines.size() >= id) {
                valid = false;
            }
            if (id > 0 && inputNumber == 3 && films.size() >= id) {
                valid = false;
            }
            if (valid) {
                System.out.print("Podaj prawidłową wartość!\n\t:");
            }
        } while (valid);
        return id;
    }

    private static boolean checkIfListIsEmpty(List<Book> books, List<Magazine> magazines, List<Film> films,
            int inputNumber) {
        boolean listIsEmpty = false;
        if (inputNumber == 1 && books.isEmpty())
            listIsEmpty = true;
        if (inputNumber == 2 && magazines.isEmpty())
            listIsEmpty = true;
        if (inputNumber == 3 && films.isEmpty())
            listIsEmpty = true;
        return listIsEmpty;
    }

    private static int pickArticleType() {
        int inputNumber;
        boolean valid = true;
        do {
            inputNumber = scannInt();
            if (inputNumber > 0 && inputNumber < 4) {
                valid = false;

            } else {
                System.out.print("Podaj prawidłową wartość!\n\t:");
            }
        } while (valid);

        return inputNumber;
    }

    private static void addNewUserIfUserListIsEmpty(List<UserProfile> userProfiles) {
        if (userProfiles.isEmpty()) {
            System.out.printf("Nie ma żadnego użytkownika w bazie przekierowuje do rejestracji nowego użytkownika\n\t");
            UserController.addUser(userProfiles);
        }
    }

    public static void returnArticle(List<Book> books, List<Magazine> magazines, List<Film> films,
            List<UserProfile> userProfiles) {
        System.out.printf("Wybierz typ artykułu do zwrotu:" + "1.Książke, 2.Czasopismo, 3 Film\n\t:");
        int inputNumber = pickArticleType();
        boolean checkForEmpty = checkIfListIsEmpty(books, magazines, films, inputNumber);

        if (!checkForEmpty) {
            int atricleId = pickId(books, magazines, films, inputNumber, "Podaj id artykułu do zwrotu\n\t:");

            switch (inputNumber) {
                case 1:
                    returnBook(books, atricleId - 1, userProfiles);
                    break;
                case 2:
                    returnMagazine(magazines, atricleId - 1, userProfiles);
                    break;
                case 3:
                    returnFilm(films, atricleId - 1, userProfiles);
                    break;
            }
            System.out.print("Poprawnie zwrócono\n\t");

        } else {
            System.out.print("Nie mamy żadnej pozycji do zwrotu\n\t");
        }

    }

    public static void printArticlesList(List<Book> books, List<Magazine> magazines, List<Film> films) {
        System.out.printf("Wybierz typ artykułów do wyświetlenia:" + "1.Ksiązki, 2.Czasopisma, 3.Filmy \n\t:");
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
                    System.err.println("Podaj prawidłową wartość!\n\t:");
                    break;
            }

        } while (inputNumber < 0 || inputNumber > 3);
    }

    private static void deleteAllArticlesByTitle(List<Book> books, List<Magazine> magazines, List<Film> films) {
        System.out.print("Podaj tytuł artykułu do usunięcia\n\t:");
        String articleTittle = scannString();
        System.out.printf("\n\tWybierz typ artykułu jaki chcesz usunąć:" + "1. Książke, 2.Czasopismo, 3.Film \n\t:");
        int inputNumber;
        do {
            inputNumber = scannInt();
            switch (inputNumber) {
                case 0:
                    break;
                case 1:
                    int booksBeforeRemoving = books.size();
                    books.stream().filter(e -> !e.getTitle().equals(articleTittle)).collect(Collectors.toList());
                    if (booksBeforeRemoving == books.size()) {
                        System.out.println("Nie ma książki o wprowadzonym tytule w bibliotece.");
                    }
                    break;
                case 2:
                    int magazinesBeforeRemoving = magazines.size();
                    magazines.stream().filter(e -> !e.getTitle().equals(articleTittle)).collect(Collectors.toList());
                    if (magazinesBeforeRemoving == magazines.size()) {
                        System.out.println("Nie ma czasopisma o wprowadzonym tytule w bibliotece.");
                    }
                    break;
                case 3:
                    int filmsBeforeRemoving = magazines.size();
                    films.stream().filter(e -> !e.getTitle().equals(articleTittle)).collect(Collectors.toList());
                    if (filmsBeforeRemoving == films.size()) {
                        System.out.println("Nie ma filmu o wprowadzonym tytule w bibliotece.");
                    }
                    break;

                default:
                    System.err.println("Podaj prawidłową wartość!\n\t:");
                    break;
            }

        } while (inputNumber < 0 || inputNumber > 3);
    }

    private static void deleteArticleById(List<Book> books, List<Magazine> magazines, List<Film> films) {
        System.out.printf("Wybierz typ artykułu jaki chcesz usunąć:" + "1. Książke, 2.Czasopismo, 3.Film \n\t:");
        boolean valid = true;
        int inputNumber;
        do {
            inputNumber = scannInt();
            if (inputNumber > 0 && inputNumber < 4) {
                valid = false;

            } else {
                System.out.print("Podaj prawidłową wartość!\n\t:");
            }
        } while (valid);
        valid = true;
        System.out.print("Podaj id artykułu do usunięcia\n\t:");
        int id;
        boolean checkForEmpty = checkIfListIsEmpty(books, magazines, films, inputNumber);
        if (!checkForEmpty) {
            do {
                id = scannInt();
                if (id > 0 && inputNumber == 1 && books.size() >= id) {
                    valid = false;
                }
                if (id > 0 && inputNumber == 2 && magazines.size() >= id) {
                    valid = false;
                }
                if (id > 0 && inputNumber == 3 && films.size() >= id) {
                    valid = false;
                }
                if (valid) {
                    System.out.print("Podaj prawidłową wartość!\n\t:");
                }
            } while (valid);

            switch (inputNumber) {
                case 1:
                    books.remove(id - 1);
                    break;
                case 2:
                    magazines.remove(id - 1);
                    break;
                case 3:
                    films.remove(id - 1);
                    break;
                default:
                    break;
            }
            System.out.print("Poprawnie usunięto!\n\t");
        } else
            System.out.print("Nie ma nic do usunięcia!\n\t");

    }

    private static void returnFilm(List<Film> films, int id, List<UserProfile> userProfiles) {
        Film tempFilm = films.get(id);
        tempFilm.setArticleStatus(ArticleStatus.AVAILABLE);
        tempFilm.setBorrowerId(null);
        films.set(id, tempFilm);
        deleteIdElementFromUserBorrowedFilms(userProfiles, tempFilm);
    }

    private static void deleteIdElementFromUserBorrowedFilms(List<UserProfile> userProfiles, Film tempFilm) {
        try {
            UserProfile user = UserController.pickUserById(userProfiles, tempFilm.getBorrowerId());
            List<Integer> tempBorrowedIdList = user.getBorrowedFilmsId();
            Integer id = tempBorrowedIdList.stream().filter(e -> e.intValue() != tempFilm.getId()).findFirst().get();
            tempBorrowedIdList.remove(id);

            user.setBorrowedFilmsId(tempBorrowedIdList);
        } catch (NullPointerException | NoSuchElementException exception) {
            System.out.println("Nie ma takiego wypożyczonego filmu lub został już oddany, sprawdż status! \n\t");

        }
    }

    private static void returnMagazine(List<Magazine> magazines, int id, List<UserProfile> userProfiles) {
        Magazine tempMagazine = magazines.get(id);
        tempMagazine.setArticleStatus(ArticleStatus.AVAILABLE);
        tempMagazine.setBorrowerId(null);
        magazines.set(id, tempMagazine);
        deleteIdElementFromUserBorrowedMagazines(userProfiles, tempMagazine);
    }

    private static void deleteIdElementFromUserBorrowedMagazines(List<UserProfile> userProfiles,
            Magazine tempMagazine) {
        try {
            UserProfile user = UserController.pickUserById(userProfiles, tempMagazine.getBorrowerId());
            List<Integer> tempBorrowedIdList = user.getBorrowedMagazinesId();
            Integer id = tempBorrowedIdList.stream().filter(e -> e.intValue() != tempMagazine.getId()).findFirst()
                    .get();
            tempBorrowedIdList.remove(id);

            user.setBorrowedMagazinesId(tempBorrowedIdList);
        } catch (NullPointerException | NoSuchElementException exception) {
            System.out.println("Nie ma takiego wypożyczonego czasopisma lub zostało już oddane, sprawdż status! \n\t");

        }
    }

    private static void returnBook(List<Book> books, int id, List<UserProfile> userProfiles) {
        Book tempBook = books.get(id);
        tempBook.setArticleStatus(ArticleStatus.AVAILABLE);
        tempBook.setBorrowerId(null);
        books.set(id, tempBook);
        deleteIdElementFromUserBorrowedBooks(userProfiles, tempBook);
    }

    private static void deleteIdElementFromUserBorrowedBooks(List<UserProfile> userProfiles, Book tempBook) {

        try {
            UserProfile user = UserController.pickUserById(userProfiles, tempBook.getBorrowerId());
            List<Integer> tempBorrowedIdList = user.getBorrowedBooksId();
            Integer id = tempBorrowedIdList.stream().filter(e -> e.intValue() != tempBook.getId()).findFirst().get();
            tempBorrowedIdList.remove(id);
            user.setBorrowedBooksId(tempBorrowedIdList);
        } catch (NullPointerException | NoSuchElementException exception) {
            System.out.println("Nie ma takiej wypożyczonej książki lub została już oddana, sprawdż status! \n\t");

        }
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

        Long newBookId;
        try {
            newBookId = books.get(books.size() - 1).getId() + 1;
        } catch (IndexOutOfBoundsException exception) {
            newBookId = Long.valueOf(1);
        }
        newBook.setId(newBookId);
        System.out.print("Podaj tytuł\n\t:");
        newBook.setTitle(scannString());
        System.out.print("Podaj autorów\n\t:");
        newBook.setAuthors(scannString());
        newBook.setArticleStatus(ArticleStatus.AVAILABLE);
        System.out.print("Podaj ilość stron\n\t:");
        newBook.setNumberOfPages(scannInt());
        System.out.print("Podaj numer ISBN\n\t:");
        newBook.setIsbnNumber(scannInt());

        books.add(newBook);
        System.out.print("Poprawnie dodano książke! \n\t");
    }

    private static void addMagazine(List<Magazine> magazines) {
        Magazine newMagazine = new Magazine();

        Long newMagazineId;
        try {
            newMagazineId = magazines.get(magazines.size() - 1).getId() + 1;
        } catch (IndexOutOfBoundsException exception) {
            newMagazineId = Long.valueOf(1);
        }
        newMagazine.setId(newMagazineId);
        System.out.print("Podaj tytuł\n\t:");
        newMagazine.setTitle(scannString());
        System.out.print("Podaj autorów\n\t:");
        newMagazine.setAuthors(scannString());
        newMagazine.setArticleStatus(ArticleStatus.AVAILABLE);
        System.out.print("Podaj nazwę wydawnictwa\n\t:");
        newMagazine.setPublisher(scannString());
        System.out.print("Podaj numer wydania\n\t:");
        newMagazine.setIssueNumber(scannString());

        magazines.add(newMagazine);
        System.out.print("Poprawnie dodano Czasopismo! \n\t");

    }

    private static void addFilm(List<Film> films) {
        Film newFilm = new Film();

        Long newFilmId;
        try {
            newFilmId = films.get(films.size() - 1).getId() + 1;
        } catch (IndexOutOfBoundsException exception) {
            newFilmId = Long.valueOf(1);
        }
        newFilm.setId(newFilmId);
        System.out.print("Podaj tytuł\n\t:");
        newFilm.setTitle(scannString());
        System.out.print("Podaj autorów\n\t:");
        newFilm.setAuthors(scannString());
        newFilm.setArticleStatus(ArticleStatus.AVAILABLE);
        System.out.print("Podaj czas trwania w minutach\n\t:");
        newFilm.setDurationInMinutes(scannInt());
        System.out.print("Podaj rok produkcji\n\t:");
        newFilm.setYearOfProduction(scannInt());

        films.add(newFilm);
        System.out.print("Poprawnie dodano film! \n\t");
    }

}
