package kata;

public class Tennis {

    private static String[] translation = new String[]{"love", "fifteen", "thirty", "forty"};

    public static String score(int playerOne, int playerTwo) {
        if (playerOne >= 4 || playerTwo >= 4) {
            return winnable(playerOne, playerTwo, "player one");
        }

        if (playerOne == playerTwo && playerOne == 3) {
            return "deuce";
        }
        if (playerOne == playerTwo) {
            return translation[playerOne] + " all";
        }

        return translation[playerOne] + " love";
    }

    private static String winnable(int playerOne, int playerTwo, String leadingPlayerName) {
        if (playerOne < playerTwo)
            return winnable(playerTwo, playerOne, "player two");

        return String.format(new String[]{"deuce", "%s advantage", "%s win"}[playerOne - playerTwo], leadingPlayerName);
    }
}