package controller;

import article.types.Book;
import article.types.Film;
import article.types.Magazine;
import user.UserProfile;

import static controller.FunctionsToUseInThisPackage.scannInt;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    public void appController() {
        List<UserProfile> userProfiles = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        List<Magazine> magazines = new ArrayList<>();
        List<Film> films = new ArrayList<>();

        int inputNumber;
        do {
            GreetingController.randomlySelectClassToUse();
            System.out.printf(selectProgramActionMessage);
            inputNumber = scannInt();
            selectProgramAction(inputNumber, userProfiles, books, magazines, films);
        } while (inputNumber != 0);
    }

    private void selectProgramAction(int inputNumber, List<UserProfile> userProfiles, List<Book> books,
            List<Magazine> magazines, List<Film> films) {

        switch (inputNumber) {
            case 0:
                break;
            case 1:
                ArticleController.addArticle(books, magazines, films);
                break;
            case 2:
                ArticleController.deleteArticle(books, magazines, films);
                break;
            case 3:
                UserController.addUser(userProfiles);
                break;
            case 4:
                ArticleController.lendArticle(books, magazines, films, userProfiles);
                break;
            case 5:
                ArticleController.returnArticle(books, magazines, films, userProfiles);
                break;
            case 6:
                ArticleController.printArticlesList(books, magazines, films);
                break;
            case 7:
                UserController.showUserInfo(userProfiles, books, magazines, films);
                break;
            default:
                System.err.println("Podaj prawidłową wartość!");
                break;
        }
    }

    private String selectProgramActionMessage = "WYBIERZ" + "\n\t1 - aby dodać artykuł do biblioteki"
            + "\n\t2 - aby usunąć artykuł z biblioteki" + "\n\t3 - aby dodać użytkownika"
            + "\n\t4 - aby wypożyczyć artykuł" + "\n\t5 - aby zwrócić artykuł"
            + "\n\t6 - aby wyświetlić listę artykułów" + "\n\t7 - aby wyświetlić informację o użytkowniku"
            + "\n\t0 - aby zakończyć działanie programu" + "\n\t:";
}
