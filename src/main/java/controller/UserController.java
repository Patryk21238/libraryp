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
        String errorMessage = "Zły pesel, wpisz ponownie\n\t:";
        do {
            System.out.printf("Wpisz swój Pesel \n\t:");
            tmp = scannString();
            try {
                checkIfWordHaveOnlyIntAndGoodLenght(tmp, 11, errorMessage);
                checkForExisPesel(userProfiles, tmp, errorMessage);
                valid = false;
            } catch (InvalidInputDataException string) {
                valid = true;

            }

        } while (valid);
        return tmp;
    }

    private static String prepareLastName() {
        boolean valid = true;
        String tmp;
        String errorMessage = "Złe nazwisko, wpisz ponownie\n\t:";
        do {
            System.out.printf("Wpisz swoje nazwisko\n\t:");
            tmp = scannString();
            try {
                checkIfWordHaveOnlyChar(tmp, false, errorMessage);
                valid = false;
            } catch (InvalidInputDataException string) {
                valid = true;

            }

        } while (valid);

        return tmp;
    }

    private static String prepareFirstName() {
        String tmp;
        boolean valid = true;
        String errorMessage = "Złe imię, wpisz ponownie\n\t:";
        do {
            System.out.printf("Wpisz swoje imię\n\t:");
            tmp = scannString();
            try {
                checkIfWordHaveOnlyChar(tmp, false, errorMessage);
                valid = false;
            } catch (InvalidInputDataException string) {
                valid = true;

            }

        } while (valid);

        return tmp;
    }

    private static String preparePhoneNumber() {
        String tmp;
        boolean valid = true;
        String errorMessage = "Zły numer telefonu, wpisz ponownie\n\t:";
        do {
            System.out.printf("Wpisz swój numer telefonu bez kierunkowego w formacie 9 cyfr \n\t:");
            tmp = scannString();
            try {
                checkIfWordHaveOnlyIntAndGoodLenght(tmp, 9, errorMessage);
                valid = false;
            } catch (InvalidInputDataException string) {
                valid = true;

            }

        } while (valid);
        return tmp;

    }

    private static String prepareCity() {
        String tmp;
        boolean valid = true;
        String errorMessage = "Złe miasto, wpisz ponownie";
        do {
            System.out.printf("Wpisz swoje miasto/miejscowość zamieszkania \n\t:");
            tmp = scannString();
            try {
                checkIfWordHaveOnlyChar(tmp, false, errorMessage);
                valid = false;
            } catch (InvalidInputDataException string) {
                valid = true;

            }

        } while (valid);
        return tmp;
    }

    private static String prepareStreet() {
        String tmp;
        boolean valid = true;
        String errorMessage = "Zła ulica  , wpisz ponownie";
        do {
            System.out.printf("Wpisz swoją ulice lub pomiń gdy nie masz takowej \n\t:");
            tmp = scannString();
            try {
                checkIfWordHaveOnlyChar(tmp, true, errorMessage);
                valid = false;
            } catch (InvalidInputDataException string) {
                valid = true;
            }

        } while (valid);
        return tmp;
    }

    private static String prepareBuildingNumber() {
        String tmp;
        boolean valid = true;
        String errorMessage = "Zły numer domu , wpisz ponownie";
        do {
            System.out.printf(
                    "Wpisz swój numer domu w formacie 3 cyfr. Jeśli twój numer domu to przykładowo 20 to wpisz 020\n\t:");
            tmp = scannString();
            try {
                checkIfWordHaveOnlyIntAndGoodLenght(tmp, 3, errorMessage);
                valid = false;
            } catch (InvalidInputDataException string) {
                valid = true;
            }

        } while (valid);
        return tmp;
    }

    private static String prepareZipCode() {
        String tmp;
        boolean valid = true;
        String errorMessage = "Zły kod pocztowy , wpisz ponownie";
        do {
            System.out.printf("Wpisz swój kod pocztowy składający się z samych cyfr\n\t:");
            tmp = scannString();
            try {
                checkIfWordHaveOnlyIntAndGoodLenght(tmp, 5, errorMessage);
                valid = false;
            } catch (InvalidInputDataException string) {
                valid = true;
            }
        } while (valid);
        return tmp;
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

    public static void checkForExisPesel(List<UserProfile> userProfiles, String pesel, String errorMessage)
            throws InvalidInputDataException {

        for (UserProfile user : userProfiles) {
            PersonalData personalDataOfUser = user.getPersonalData();
            if (pesel == personalDataOfUser.getPesel()) {
                throw new InvalidInputDataException(errorMessage);
            }

        }

    }

    private static void checkIfWordHaveOnlyChar(String forCheck, boolean triggerForAllowZeroLength, String errorMessage)
            throws InvalidInputDataException {
        if (!triggerForAllowZeroLength && forCheck.length() == 0) {
            throw new InvalidInputDataException(errorMessage);
        }
        for (int i = 0; i < forCheck.length(); i++) {
            char tmpChar = forCheck.charAt(i);
            if (!Character.isLetter(tmpChar)) {
                throw new InvalidInputDataException(errorMessage);
            }

        }
        if (triggerForAllowZeroLength && forCheck.length() == 0) {
            forCheck = " ";
        }

    }

    private static void checkIfWordHaveOnlyIntAndGoodLenght(String forCheck, int length, String errorMessage)
            throws InvalidInputDataException {
        if (length != forCheck.length())
            throw new InvalidInputDataException(errorMessage);
        for (int i = 0; i < forCheck.length(); i++) {
            if (!Character.isDigit(forCheck.charAt(i))) {
                throw new InvalidInputDataException(errorMessage);
            }

        }

    }

}
