package galgeleg;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author Khurram Saeed Malik
 */
public class GameClient {

    private static final String REMOTEURL = "rmi://localhost/rmicalls";
    private static GalgeI gameCalls;

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, IOException {
        gameCalls = (GalgeI) Naming.lookup(REMOTEURL);
        Scanner sc = new Scanner(System.in);
        //Do your calls

        System.out.println("**********************************************");
        System.out.println("**                                          **");
        System.out.println("**              Spil Startede               **");
        System.out.println("**                                          **");
        System.out.println("**********************************************\n");

        while (true) {
            System.out.println("Gæt følgende ord: " + gameCalls.getVisibleWords());
            System.out.println("Du har nu brugt: " + gameCalls.getUserWords());
            System.out.println("Forkert gæt: " + gameCalls.getTotalWrongGuess());

            int temp = gameCalls.getTotalWrongGuess();
            if (temp <= 6) {
                // todo
                // print hangman out here instead of pictures
                // hangman state depends on temp
            }

            if (gameCalls.isGameWon()) {
                System.out.println("**********************************************");
                System.out.println("**                                          **");
                System.out.println("**              Spil Afsluttede             **");
                System.out.println("**           Du har vundet spillet          **");
                System.out.println("**                                          **");
                System.out.println("**********************************************\n");
                gameCalls.resetGame();
                return;
            } else if (!gameCalls.isGameLost()) {
                System.out.println("Skriv dit gæt: \n");
                String guess = sc.nextLine();
                gameCalls.guessWord(guess);
            } else {
                gameCalls.resetGame();
                System.out.println("**********************************************");
                System.out.println("**                                          **");
                System.out.println("**              Spil Afsluttede             **");
                System.out.println("**            Du har tabt spillet           **");
                System.out.println("**                                          **");
                System.out.println("**********************************************\n");
                return;
            }

        }

    }

}
