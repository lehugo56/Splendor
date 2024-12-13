/**
 * Représente une action où un joueur achète une carte de développement.
 */
public class BuyCardAction implements Action {
    private int level;
    private int column;

    /**
     * Constructeur de l'action.
     * 
     * @param level le niveau de la carte à acheter.
     * @param column la colonne de la carte à acheter.
     */
    public BuyCardAction(int level, int column) {
        this.level = level;
        this.column = column;
    }

    /**
     * Exécute l'action d'achat d'une carte.
     * 
     * @param board le plateau de jeu.
     * @param player le joueur qui effectue l'achat.
     */
    @Override
    public void process(Board board, Player player) {
        DevCard card = board.getCard(level, column);
        if (card != null && player.canBuyCard(card)) {
            //player.buyCard(card);
            board.updateCard(level, column);
        } else {
            throw new IllegalStateException("Impossible d'acheter cette carte.");
        }
    }

    /**
     * Retourne une description de l'action.
     * 
     * @return une chaîne décrivant l'action.
     */
    @Override
    public String toString() {
        return "Acheter une carte du niveau " + level + ", colonne " + column;
    }
}