package controller;

import article.types.Book;
import article.types.Film;
import article.types.Magazine;
import user.Address;
import user.PersonalData;
import user.UserProfile;

import static controller.PackageRangeFunctions.scannInt;
import static controller.PackageRangeFunctions.scannString;

import java.util.List;
import java.util.NoSuchElementException;

// do ogarniecia jeszcze 
public class UserController {
    public static UserProfile pickUserByPesel(List<UserProfile> userProfiles) {
        UserProfile userProfile = new UserProfile();
        System.out.print("Podaj numer pesel użytkownika: ");

        boolean valid = true;
        do {
            try {
                String userPeselNumber = scannString();
                userProfile = userProfiles.stream().filter(u -> u.getPersonalData().getPesel().equals(userPeselNumber))
                        .findAny().get();
                valid = false;
            } catch (NoSuchElementException exception) {
                System.out.println("Zły numer, wpisz ponownie \n\t");
            }
        } while (valid);

        return userProfile;
    }

    public static UserProfile pickUserById(List<UserProfile> userProfiles, long userId) {
        UserProfile userProfile = new UserProfile();
        System.out.print("Podaj numer pesel użytkownika: ");

        userProfile = userProfiles.stream().filter(u -> u.getId().equals(userId)).findAny().get();

        return userProfile;
    }

    public static void addUser(List<UserProfile> userProfiles) {
        PersonalData personalData = new PersonalData();
        Address address = new Address();

        personalData.setFirstName(prepareFirstName());
        personalData.setLastName(prepareLastName());
        personalData.setPesel(preparePeselNumber(userProfiles));
        personalData.setPhoneNumber(preparePhoneNumber());

        address.setCity(prepareCity());
        address.setStreet(prepareStreet());
        address.setBuldingNumber(prepareBuildingNumber());
        address.setZipCode(prepareZipCode());

        personalData.setAddress(address);
        Long newId = Long.valueOf(userProfiles.size() + 1);
        userProfiles.add(new UserProfile(newId, personalData));
        System.err.println("Poprawnie dodano użytkownika!");

    }

    private static String preparePeselNumber(List<UserProfile> userProfiles) {
        String tmp;
        boolean valid = true;
        do {
            System.out.printf("Wpisz swój Pesel \n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyIntAndGoodLenght(tmp, 11) && checkForExisPesel(userProfiles, tmp))
                valid = false;
            else {
                System.out.printf("Zły Pesel, wpisz ponownie\n\t:");
            }

        } while (valid);
        return tmp;
    }

    private static String prepareLastName() {
        boolean valid = true;
        String tmp;
        do {
            System.out.printf("Wpisz swoje nazwisko\n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyChar(tmp, false))
                valid = false;
            else {
                System.out.printf("Złe nazwisko, wpisz ponownie\n\t:");
            }

        } while (valid);

        return tmp;
    }

    private static String prepareFirstName() {
        String tmp = "";
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

        return tmp;
    }

    private static String preparePhoneNumber() {
        String tmp;
        boolean valid = true;
        do {
            System.out.printf("Wpisz swoje miasto/miejscowość zamieszkania \n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyChar(tmp, false))
                valid = false;
            else {
                System.out.printf("Złe miasto/miejscowość , wpisz ponownie\n\t:");
            }

        } while (valid);
        return tmp;

    }

    private static String prepareCity() {
        String tmp;
        boolean valid = true;
        do {
            System.out.printf("Wpisz swoje miasto/miejscowość zamieszkania \n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyChar(tmp, false))
                valid = false;
            else {
                System.out.printf("Złe miasto/miejscowość , wpisz ponownie\n\t:");
            }

        } while (valid);
        return tmp;
    }

    private static String prepareStreet() {
        String tmp;
        boolean valid = true;
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
        return tmp;
    }

    private static String prepareBuildingNumber() {
        String tmp;
        boolean valid = true;
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
        return tmp;
    }

    private static String prepareZipCode() {
        String tmp;
        boolean valid = true;
        do {
            System.out.printf("Wpisz swój kod pocztowy składający się z samych cyfr\n\t:");
            tmp = scannString();
            if (checkIfWordHaveOnlyIntAndGoodLenght(tmp, 5))
                valid = false;
            else {
                System.out.printf("Zły kod pocztowy, wpisz ponownie\n\t:");
            }

        } while (valid);
        return tmp;
    }

    public static boolean checkForExisPesel(List<UserProfile> userProfiles, String pesel) {

        for (UserProfile user : userProfiles) {
            PersonalData personalDataOfUser = user.getPersonalData();
            if (pesel == personalDataOfUser.getPesel()) {
                return false;
            }

        }

        return true;

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

}
