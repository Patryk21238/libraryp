package controller;

import user.UserProfile;

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
                                userProfile = userProfiles.stream()
                                                .filter(u -> u.getPersonalData().getPesel().equals(userPeselNumber))
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

}
