package controller;

import static controller.PackageRangeFunctions.scannInt;
import static controller.PackageRangeFunctions.scannString;

import article.ArticleStatus;
import article.types.Book;
import article.types.Film;
import article.types.Magazine;
import user.UserProfile;
import user.Address;
import user.PersonalData;

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
                    break;
                case 2:
                    deleteArticleById(books, magazines, films);
                    break;
                default:
                    System.err.println("Podaj prawidłową wartość\n\t:");
                    break;
            }
            System.out.printf("WYBIERZ" + "\n\t1 - aby usunąć artykuły o danym tytule"
                    + "\n\t2 - aby usunąć artykuły o danym numerze id" + "\n\t0 - aby cofnąć\n\t:");

        } while (inputNumber != 0);
    }

    public static void addUser(List<UserProfile> userProfiles) {
        String tmp = "";
        Long newId = Long.valueOf(userProfiles.size() + 1);
        String name;
        String surname;
        String pesel;
        String phoneNumber;
        String city;
        String street;
        String buldingNumber;
        String zipCode;
        boolean valid = true;
        do {
            System.out.printf("Wpisz swoje imię\n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyChar(tmp, false))
                valid = false;
            else {
                System.out.printf("Złe imię, wpisz ponownie\n\t:");
            }

        } while (valid);
        name = tmp;
        valid = true;
        do {
            System.out.printf("Wpisz swoje nazwisko\n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyChar(tmp, false))
                valid = false;
            else {
                System.out.printf("Złe nazwisko, wpisz ponownie\n\t:");
            }

        } while (valid);
        surname = tmp;
        valid = true;
        do {
            System.out.printf("Wpisz swój Pesel \n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyIntAndGoodLenght(tmp, 11))
                valid = false;
            else {
                System.out.printf("Zły Pesel, wpisz ponownie\n\t:");
            }

        } while (valid);
        pesel = tmp;

        valid = true;
        do {
            System.out.printf("Wpisz swój numer Telefonu bez kierunkowego \n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyIntAndGoodLenght(tmp, 9))
                valid = false;
            else {
                System.out.printf("Zły numer telefonu, wpisz ponownie\n\t:");
            }

        } while (valid);

        phoneNumber = tmp;

        valid = true;
        do {
            System.out.printf("Wpisz swoje miasto/miejscowość zamieszkania \n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyChar(tmp, false))
                valid = false;
            else {
                System.out.printf("Złe miasto/miejscowość , wpisz ponownie\n\t:");
            }

        } while (valid);
        city = tmp;
        valid = true;
        do {
            System.out.printf("Wpisz swoją ulice lub pomiń gdy nie masz takowej \n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyChar(tmp, true))
                valid = false;
            else {
                System.out.printf("Zła ulica , wpisz ponownie\n\t:");
            }
            if (tmp.length() == 0) {
                tmp = " ";
            }

        } while (valid);
        street = tmp;
        valid = true;
        do {
            System.out.printf(
                    "Wpisz swój numer domu w formacie 3 cyfr. Jeśli twój numer domu to przykładowo 20 to wpisz 020\n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyIntAndGoodLenght(tmp, 3))
                valid = false;
            else {
                System.out.printf("Zły numer domu , wpisz ponownie\n\t:");
            }

        } while (valid);
        buldingNumber = tmp;
        valid = true;
        do {
            System.out.printf("Wpisz swój kod pocztowy składający się z samych cyfr\n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyIntAndGoodLenght(tmp, 5))
                valid = false;
            else {
                System.out.printf("Zły kod pocztowy, wpisz ponownie\n\t:");
            }

        } while (valid);
        zipCode = tmp;
        Address address = new Address(city, street, buldingNumber, zipCode);
        PersonalData personalData = new PersonalData(name, surname, pesel, phoneNumber, address);
        userProfiles.add(new UserProfile(newId, personalData));
        System.err.println("Poprawnie dodano użytkownika!");

    }

    public static void lendArticle(List<Book> books, List<Magazine> magazines, List<Film> films,
            List<UserProfile> userProfiles) {
        UserProfile user;

        if (userProfiles.isEmpty()) {
            System.out.printf("Nie ma żadnego użytkownika w bazie przekierowuje do rejestracji nowego użytkownika\n\t");
            ArticleController.addUser(userProfiles);
            user = UserController.pickUserByPesel(userProfiles);
        } else {
            user = UserController.pickUserByPesel(userProfiles);
        }

        System.out.printf("Wybierz typ artykułu jaki do wypożyczenia:" + "1.Książke, 2.Magazyn, 3 Film\n\t");
        boolean valid = true;
        int inputNumber;
        do {
            inputNumber = scannInt();
            if (inputNumber > 0 && inputNumber < 4) {
                valid = false;

            } else {
                System.out.print("Zla wartość! Podaj jeszcze raz");
            }
        } while (valid);
        valid = true;
        System.out.print("Podaj id artykułu :");
        int id;
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
                System.out.print("Zla wartość! Podaj jeszcze raz");
            }
        } while (valid);

        switch (inputNumber) {
            case 0:
                break;
            case 1:
                lendBook(books, user, id - 1);
                break;
            case 2:
                lendMagazine(magazines, user, id - 1);
                break;
            case 3:
                lendFilm(films, user, id - 1);
                break;

            default:
                System.err.println("Podaj prawidłową wartość!");
                break;
        }

    }

    public static void returnArticle(List<Book> books, List<Magazine> magazines, List<Film> films,
            List<UserProfile> userProfiles) {
        System.out.printf("Wybierz typ artykułu do zwrotu:" + "1.Książke, 2.Magazyn, 3 Film\n\t");
        boolean valid = true;
        int inputNumber;
        do {
            inputNumber = scannInt();
            if (inputNumber > 0 && inputNumber < 4) {
                valid = false;

            } else {
                System.out.print("Zla wartość! Podaj jeszcze raz");
            }
        } while (valid);
        valid = true;
        System.out.print("Podaj id artykułu do zwrotu:");
        int atricleId;
        do {
            atricleId = scannInt();
            if (atricleId > 0 && inputNumber == 1 && books.size() >= atricleId) {
                valid = false;
            }
            if (atricleId > 0 && inputNumber == 2 && magazines.size() >= atricleId) {
                valid = false;
            }
            if (atricleId > 0 && inputNumber == 3 && films.size() >= atricleId) {
                valid = false;
            }
            if (valid) {
                System.out.print("Zla wartość! Podaj jeszcze raz");
            }
        } while (valid);
        do {
            inputNumber = scannInt();
            switch (inputNumber) {
                case 0:
                    break;
                case 1:
                    returnBook(books, atricleId - 1, userProfiles);
                    break;
                case 2:
                    returnMagazine(magazines, atricleId - 1, userProfiles);
                    break;
                case 3:
                    returnFilm(films, atricleId - 1, userProfiles);
                    break;

                default:
                    System.err.println("Podaj prawidłową wartość!");
                    break;
            }

        } while (inputNumber < 0 || inputNumber > 3);
    }

    public static void printArticlesList(List<Book> books, List<Magazine> magazines, List<Film> films) {
        System.out.printf("Wybierz typ artykułów do wyświetlenia:" + "1.Ksiązki, 2.Magazyny, 3.Filmy \n\t");
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

    public static void showUserInfo(List<UserProfile> userProfiles, List<Book> books, List<Magazine> magazines,
            List<Film> films) {
        System.out.printf("\tWyświetlam użytkowników\n\t");
        int numberOfUser = 1;
        for (UserProfile user : userProfiles) {
            PersonalData actualDataOfUser = user.getPersonalData();
            System.out.printf(numberOfUser + ". " + actualDataOfUser.getFirstName() + " "
                    + actualDataOfUser.getLastName() + "\n\t");
            numberOfUser++;
        }

        System.out.printf("Wybierz użytkownika do wyświetlenia\n\t:");
        int inputNumber;
        do {
            inputNumber = scannInt();
            if (inputNumber > 0 && inputNumber <= numberOfUser) {
                break;
            }
            System.out.printf("Podałeś zły numer!\n\t:");

        } while (true);
        UserProfile chooseUserProfile = userProfiles.get(inputNumber - 1);
        PersonalData chooseUserPersonalData = chooseUserProfile.getPersonalData();
        Address chooseUserAddress = chooseUserPersonalData.getAddress();
        System.out.printf("\n\t Wyświetlam użytkownika: \n\t " + chooseUserPersonalData.getFirstName() + " "
                + chooseUserPersonalData.getLastName() + " Pesel:" + chooseUserPersonalData.getPesel()
                + "\n\t Numer telefonu:" + chooseUserPersonalData.getPhoneNumber() + " Adres:"
                + chooseUserAddress.getCity() + " " + chooseUserAddress.getZipCodeWithFormat() + " "
                + chooseUserAddress.getStreet() + " " + chooseUserAddress.getBuldingNumber() + "\n\t");
        System.out.printf("Wyświetlam pożyczone  książki\n\t:");
        for (Integer id : chooseUserProfile.getBorrowedBooksId()) {
            System.out.printf("Id:" + id + ". " + books.get(id) + " \n\t");
        }
        System.out.printf("\n\tWyświetlam pożyczone magazyny\n\t:");
        for (Integer id : chooseUserProfile.getBorrowedMagazinesId()) {
            System.out.printf("Id:" + id + ". " + magazines.get(id) + " \n\t");
        }
        System.out.printf("\n\tWyświetlam pożyczone filmy \n\t:");
        for (Integer id : chooseUserProfile.getBorrowedFilmsId()) {
            System.out.printf("Id:" + id + ". " + films.get(id) + " \n\t");
        }
        System.out.printf("\n\t");

    }

    private static boolean checkIfWordHaveOnlyChar(String forCheck, boolean triggerForAllowZeroLength) {
        if (!triggerForAllowZeroLength && forCheck.length() == 0)
            return false;
        else if (triggerForAllowZeroLength && forCheck.length() == 0) {
            forCheck = " ";
            return true;
        }
        for (int i = 0; i < forCheck.length(); i++) {
            char tmpChar = forCheck.charAt(i);
            if (!Character.isLetter(tmpChar)) {
                return false;
            }

        }

        return true;
    }

    private static boolean checkIfWordHaveOnlyIntAndGoodLenght(String forCheck, int length) {
        if (length != forCheck.length())
            return false;
        for (int i = 0; i < forCheck.length(); i++) {
            if (!Character.isDigit(forCheck.charAt(i))) {
                return false;
            }

        }

        return true;
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
                    books.stream().filter(e -> !e.getTitle().equals(articleTittle)).collect(Collectors.toList());
                    if (booksBeforeRemoving == books.size()) {
                        System.out.println("Nie ma książki o wprowadzonym tytule w bibliotece.");
                    }
                    break;
                case 2:
                    int magazinesBeforeRemoving = magazines.size();
                    magazines.stream().filter(e -> !e.getTitle().equals(articleTittle)).collect(Collectors.toList());
                    if (magazinesBeforeRemoving == magazines.size()) {
                        System.out.println("Nie ma magazynu o wprowadzonym tytule w bibliotece.");
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
                    System.err.println("Podaj prawidłową wartość!");
                    break;
            }

        } while (inputNumber < 0 || inputNumber > 3);
    }

    private static void deleteArticleById(List<Book> books, List<Magazine> magazines, List<Film> films) {
        System.out.printf("Wybierz typ artykułu jaki chcesz usunąć:" + "1. Książke, 2.Magazyn, 3.Film \n\t");
        boolean valid = true;
        int inputNumber;
        do {
            inputNumber = scannInt();
            if (inputNumber > 0 && inputNumber < 4) {
                valid = false;

            } else {
                System.out.print("Zla wartość! Podaj jeszcze raz");
            }
        } while (valid);
        valid = true;
        System.out.print("Podaj id artykułu do usunięcia:");
        int id;
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
                System.out.print("Zla wartość! Podaj jeszcze raz");
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
    }

    private static void returnFilm(List<Film> films, int id, List<UserProfile> userProfiles) {
        Film tempFilm = films.get(id);
        tempFilm.setArticleStatus(ArticleStatus.AVAILABLE);
        tempFilm.setBorrowerId(null);
        films.set(id, tempFilm);
        deleteIdElementFromUserBorrowedFilms(userProfiles, tempFilm);
    }

    private static void deleteIdElementFromUserBorrowedFilms(List<UserProfile> userProfiles, Film tempFilm) {
        UserProfile user = UserController.pickUserById(userProfiles, tempFilm.getBorrowerId());
        List<Integer> tempBorrowedIdList = user.getBorrowedFilmsId();
        Integer t = tempBorrowedIdList.stream().filter(e -> e.intValue() != tempFilm.getId()).findFirst().get();
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

    private static void deleteIdElementFromUserBorrowedMagazines(List<UserProfile> userProfiles,
            Magazine tempMagazine) {
        UserProfile user = UserController.pickUserById(userProfiles, tempMagazine.getBorrowerId());
        List<Integer> tempBorrowedIdList = user.getBorrowedMagazinesId();
        Integer t = tempBorrowedIdList.stream().filter(e -> e.intValue() != tempMagazine.getId()).findFirst().get();
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

    private static void deleteIdElementFromUserBorrowedBooks(List<UserProfile> userProfiles, Book tempBook) {
        UserProfile user = UserController.pickUserById(userProfiles, tempBook.getBorrowerId());
        List<Integer> tempBorrowedIdList = user.getBorrowedBooksId();
        Integer t = tempBorrowedIdList.stream().filter(e -> e.intValue() != tempBook.getId()).findFirst().get();
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

        Long newBookId;
        try {
            newBookId = books.get(books.size() - 1).getId() + 1;
        } catch (IndexOutOfBoundsException exception) {
            newBookId = Long.valueOf(1);
        }
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

        Long newMagazineId;
        try {
            newMagazineId = magazines.get(magazines.size() - 1).getId() + 1;
        } catch (IndexOutOfBoundsException exception) {
            newMagazineId = Long.valueOf(1);
        }
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

        Long newFilmId;
        try {
            newFilmId = films.get(films.size() - 1).getId() + 1;
        } catch (IndexOutOfBoundsException exception) {
            newFilmId = Long.valueOf(1);
        }
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

    private final static String pickMessage = "\n\t-wybierz 1 aby dodać książkę\n\t-wybierz 2 aby dodać czasopismo\n\t"
            + "-wybierz 3 aby dodać film\n\t-wybierz 0 aby cofnąć\n\t:";

}
