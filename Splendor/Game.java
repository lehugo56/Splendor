import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
/*
 * Game Class, allows to play the game. That's it.
 * 
 * @author Matéo Ginier
 */
public class Game {
    /* L'affichage et la lecture d'entrée avec l'interface de jeu se fera entièrement via l'attribut display de la classe Game.
     * Celui-ci est rendu visible à toutes les autres classes par souci de simplicité.
     * L'intéraction avec la classe Display est très similaire à celle que vous auriez avec la classe System :
     *    - affichage de l'état du jeu (méthodes fournies): Game.display.outBoard.println("Nombre de joueurs: 2");
     *    - affichage de messages à l'utilisateur: Game.display.out.println("Bienvenue sur Splendor ! Quel est ton nom?");
     *    - demande d'entrée utilisateur: new Scanner(Game.display.in);
     */
    private static final int ROWS_BOARD=36, ROWS_CONSOLE=8, COLS=82;
    public static final  Display display = new Display(ROWS_BOARD, ROWS_CONSOLE, COLS);

    private Board board;
    private List<Player> players;

    public static void main(String[] args) {
        //-- à modifier pour permettre plusieurs scénarios de jeu
        display.outBoard.println("Bienvenue sur Splendor !");
        Game game = new Game(2); 
        game.play();
        display.close();
    }

    public Game(int nbOfPlayers){
        if (nbOfPlayers < 2 || nbOfPlayers > 4) {
            throw new IllegalArgumentException("Le nombre de joueurs doit être entre 2 et 4.");
        }

        this.board = new Board();
        this.players = new ArrayList<>();

        for (int i = 0; i < nbOfPlayers; i++) {
            if (i == 0) {
                players.add(new HumanPlayer());
            } else {
                players.add(new DumbRobotPlayer());
            }
        }
    }

    public int getNbPlayers(){
        return players.size();
    }

    private void display(int currentPlayer){
        String[] boardDisplay = board.toStringArray();
        String[] playerDisplay = Display.emptyStringArray(0, 0);
        for(int i=0;i<players.size();i++){
            String[] pArr = players.get(i).toStringArray();
            if(i==currentPlayer){
                pArr[0] = "\u27A4 " + pArr[0];
            }
            playerDisplay = Display.concatStringArray(playerDisplay, pArr, true);
            playerDisplay = Display.concatStringArray(playerDisplay, Display.emptyStringArray(1, COLS-54, "┉"), true);
        }
        String[] mainDisplay = Display.concatStringArray(boardDisplay, playerDisplay, false);

        display.outBoard.clean();
        display.outBoard.print(String.join("\n", mainDisplay));
    }

    public void play(){
        int currentPlayerIndex = 0;
        while (!isGameOver()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            Action action = currentPlayer.chooseAction();
            action.process();
            display.actionPerformed(currentPlayer, action);

            if (currentPlayer.getTokens().size() > 10) {
                DiscardTokensAction discardAction = new DiscardTokensAction();
                discardAction.process(currentPlayer);
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        gameOver();
    }

    private void move(Player player){
        Action action = player.chooseAction();
        action.process(this.board, player);
        display.actionPerformed(player, action);
    }

    private void discardToken(Player player){
        while (player.getTokens().size() > 10) {
            Token tokenToDiscard = player.chooseTokenToDiscard();
            player.discardToken(tokenToDiscard);
        }
    }

    public boolean isGameOver(){
        for (Player player : players) {
        if (player.getPrestigePoints() >= 15) {
            return true;
        }
    }
    return false;
    }

    private void gameOver(){
        Player winner = null;
        int maxPrestige = 0;

        for (Player player : players) {
            int prestige = player.getPrestigePoints();
            if (prestige > maxPrestige) {
                maxPrestige = prestige;
                winner = player;
            } else if (prestige == maxPrestige) {
                winner = null; // In case of a tie
            }
        }
        if (winner != null) {
            display.out.println("Le gagnant est " + winner.getName() + " avec " + maxPrestige + " points de prestige!");
        } else {
            display.out.println("Il y a une égalité!");
        }
        
    }
}
