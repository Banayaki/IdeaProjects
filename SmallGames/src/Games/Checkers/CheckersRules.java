package Games.Checkers;

import java.util.ArrayList;

public class CheckersRules {

    static ArrayList<Checker> emptyChecker;
    static ArrayList<Checker> blackChecker;
    static ArrayList<Checker> whiteChecker;

    public static void newGame(GamePanel panel) {

        whiteChecker = new ArrayList<>();
        whiteChecker.add(new Checker("A8", Checker.WHITE));
        whiteChecker.add(new Checker("C8", Checker.WHITE));
        whiteChecker.add(new Checker("E8", Checker.WHITE));
        whiteChecker.add(new Checker("G8", Checker.WHITE));
        whiteChecker.add(new Checker("B7", Checker.WHITE));
        whiteChecker.add(new Checker("D7", Checker.WHITE));
        whiteChecker.add(new Checker("F7", Checker.WHITE));
        whiteChecker.add(new Checker("H7", Checker.WHITE));
        whiteChecker.add(new Checker("A6", Checker.WHITE));
        whiteChecker.add(new Checker("C6", Checker.WHITE));
        whiteChecker.add(new Checker("E6", Checker.WHITE));
        whiteChecker.add(new Checker("G6", Checker.WHITE));

        blackChecker = new ArrayList<>();
        blackChecker.add(new Checker("B1", Checker.BLACK));
        blackChecker.add(new Checker("D1", Checker.BLACK));
        blackChecker.add(new Checker("F1", Checker.BLACK));
        blackChecker.add(new Checker("H1", Checker.BLACK));
        blackChecker.add(new Checker("A2", Checker.BLACK));
        blackChecker.add(new Checker("C2", Checker.BLACK));
        blackChecker.add(new Checker("E2", Checker.BLACK));
        blackChecker.add(new Checker("G2", Checker.BLACK));
        blackChecker.add(new Checker("B3", Checker.BLACK));
        blackChecker.add(new Checker("D3", Checker.BLACK));
        blackChecker.add(new Checker("F3", Checker.BLACK));
        blackChecker.add(new Checker("H3", Checker.BLACK));

        emptyChecker = new ArrayList<>();
        emptyChecker.add(new Checker("A4", Checker.EMPTY));
        emptyChecker.add(new Checker("C4", Checker.EMPTY));
        emptyChecker.add(new Checker("E4", Checker.EMPTY));
        emptyChecker.add(new Checker("G4", Checker.EMPTY));
        emptyChecker.add(new Checker("B5", Checker.EMPTY));
        emptyChecker.add(new Checker("D5", Checker.EMPTY));
        emptyChecker.add(new Checker("F5", Checker.EMPTY));
        emptyChecker.add(new Checker("H5", Checker.EMPTY));




    }
}
