import java.util.Map;

/**
 * Représente une action où un joueur prend deux jetons de la même ressource.
 */
public class PickSameTokensAction implements Action {
    private Resource resource;

    /**
     * Constructeur de l'action.
     * 
     * @param resource la ressource choisie.
     */
    public PickSameTokensAction(Resource resource) {
        this.resource = resource;
    }

    /**
     * Exécute l'action de prendre deux jetons de la même ressource.
     * 
     * @param board le plateau de jeu.
     * @param player le joueur qui effectue l'action.
     */
    @Override
    public void process(Board board, Player player) {
        if (board.canGiveSameTokens(resource)) {
            board.updateNbResource(resource, -2);
            player.addTokens(resource, 2);
        } else {
            throw new IllegalStateException("Pas assez de jetons disponibles pour en prendre deux de la même ressource.");
        }
    }

    /**
     * Retourne une description de l'action.
     * 
     * @return une chaîne décrivant l'action.
     */
    @Override
    public String toString() {
        return "Prendre 2 " + resource.toSymbol();
    }
}
