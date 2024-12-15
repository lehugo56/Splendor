import java.util.*;

/**
 * Représente le déroulement du jeu Splendor.
 * Cette classe gère les joueurs, le plateau, les tours de jeu, et la condition de victoire.
 */
public class Game {
    private static final int ROWS_BOARD = 36, ROWS_CONSOLE = 8, COLS = 82;
    public static final Display display = new Display(ROWS_BOARD, ROWS_CONSOLE, COLS);

    private Board board; // Le plateau de jeu
    private List<Player> players; // Liste des joueurs

    /**
     * Méthode principale du jeu. Initialise une partie avec 2 joueurs par défaut.
     */
    public static void main(String[] args) {
        int reponse = 0;
        
        while (reponse < 2 || reponse > 4){
            Scanner scanner = new Scanner( Game.display.in );
            Game.display.out.println("Combien de joueur voulez vous au tour de la table (2 a 4 joueur) ?");
            reponse = scanner.nextInt();
        }
        Game game = new Game(reponse); // Démarrer une partie avec 2 joueurs
        game.play();
        display.close();
    }

    /**
     * Constructeur de la classe `Game`.
     * Initialise le plateau et la liste des joueurs.
     * 
     * @param nbOfPlayers le nombre de joueurs (entre 2 et 4).
     */
    public Game(int nbOfPlayers) {
        if (nbOfPlayers < 2 || nbOfPlayers > 4) {
            throw new IllegalArgumentException("Le nombre de joueurs doit être entre 2 et 4.");
        }

        this.board = new Board(nbOfPlayers);
        this.players = new ArrayList<>();

        // Ajouter les joueurs (le premier joueur est un humain, les autres sont des robots simples)
        for (int i = 0; i < nbOfPlayers; i++) {
            if (i == 0) {
                players.add(new HumanPlayer("Joueur Humain"));
            } else {
                players.add(new DumbRobotPlayer("Robot " + i));
            }
        }
    }

    /**
     * Retourne le nombre de joueurs dans la partie.
     * 
     * @return le nombre de joueurs.
     */
    public int getNbPlayers() {
        return players.size();
    }

    /**
     * Affiche l'état actuel du jeu (plateau et joueurs).
     * 
     * @param currentPlayer l'indice du joueur actuellement en train de jouer.
     */
    private void display(int currentPlayer) {
        String[] boardDisplay = board.toStringArray();
        String[] playerDisplay = Display.emptyStringArray(0, 0);

        for (int i = 0; i < players.size(); i++) {
            String[] pArr = players.get(i).toStringArray();
            if (i == currentPlayer) {
                pArr[0] = "\u27A4 " + pArr[0]; // Flèche pour indiquer le joueur actif
            }
            playerDisplay = Display.concatStringArray(playerDisplay, pArr, true);
            playerDisplay = Display.concatStringArray(playerDisplay, Display.emptyStringArray(1, COLS - 54, "┉"), true);
        }

        String[] mainDisplay = Display.concatStringArray(boardDisplay, playerDisplay, false);

        display.outBoard.clean();
        display.outBoard.print(String.join("\n", mainDisplay));
    }

    /**
     * Gère le déroulement d'une partie de jeu.
     */
    public void play() {
        int currentPlayerIndex = 0;

        while (!isGameOver()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            
            Game.display.out.clean();
            
            display(currentPlayerIndex);
            
            // Le joueur choisit une action et l'exécute
            boolean havetoplay= true;
            while(havetoplay){
            try{
                Action action = currentPlayer.chooseAction(board);
                action.process(board, currentPlayer);
                havetoplay = false;
            }catch(IllegalArgumentException e){
                Game.display.out.println("Action impossible, veuillez recommencer!");
            }catch(IllegalStateException e){
                Game.display.out.println("Action impossible, veuillez recommencer!");
            }
            
            }

            // Si le joueur dépasse la limite de jetons, il doit en défausser
            while (currentPlayer.getNbTokens() > 10) {
                try{
                    DiscardTokensAction discardAction = currentPlayer.chooseDiscardingTokens(1);
                    discardAction.process(board, currentPlayer);
                }catch(IllegalStateException e){
                    Game.display.out.println("Action impossible, veuillez recommencer!");
                }
            }

            // Passer au joueur suivant
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        gameOver(); // Fin de la partie
    }

    /**
     * Vérifie si la condition de victoire est atteinte (un joueur avec au moins 15 points de prestige).
     * 
     * @return `true` si la partie est terminée, sinon `false`.
     */
    public boolean isGameOver() {
        for (Player player : players) {
            if (player.getPoints() >= 15) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Affiche le gagnant ou déclare une égalité si nécessaire.
     */
    private void gameOver() {
        Player winner = null;
        int maxPrestige = 0;

        for (Player player : players) {
            int prestige = player.getPoints();
            if (prestige > maxPrestige) {
                maxPrestige = prestige;
                winner = player;
            } else if (prestige == maxPrestige) {
                winner = null; // Égalité
            }
        }

        if (winner != null) {
            display.out.println("Le gagnant est " + winner.getName() + " avec " + maxPrestige + " points de prestige !");
        } else {
            display.out.println("Il y a une égalité !");
        }
    }
}