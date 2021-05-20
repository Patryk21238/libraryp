package controller;

import user.UserProfile;

import static controller.PackageRangeFunctions.scannString;

import java.util.List;

//stream trzeba wrzućić do try catch i obsłużyć nullpointerexception/nosuchelementexception
public class UserController {
    public static UserProfile pickUserByPesel(List<UserProfile> userProfiles) {
        UserProfile userProfile = new UserProfile();
        System.out.print("Podaj numer pesel użytkownika: ");
        String userPeselNumber = scannString();

        userProfile = userProfiles.stream()
                .filter(u ->
                        u.getPersonalData()
                                .getPesel()
                                .equals(userPeselNumber))
                .findAny()
                .get();

        return userProfile;
    }

    public static UserProfile pickUserById(List<UserProfile> userProfiles, long userId) {
        UserProfile userProfile = new UserProfile();
        System.out.print("Podaj numer pesel użytkownika: ");


        userProfile = userProfiles.stream()
                .filter(u ->
                        u.getId()
                                .equals(userId))
                .findAny()
                .get();

        return userProfile;
    }


}
