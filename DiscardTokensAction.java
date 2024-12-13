/**
 * Représente une action où un joueur défausse des jetons.
 */
public class DiscardTokensAction implements Action {
    private Resource resource;
    private int amount;

    /**
     * Constructeur de l'action.
     * 
     * @param resource la ressource à défausser.
     * @param amount le nombre de jetons à défausser.
     */
    public DiscardTokensAction(Resource resource, int amount) {
        this.resource = resource;
        this.amount = amount;
    }

    /**
     * Exécute l'action de défausse.
     * 
     * @param board le plateau de jeu.
     * @param player le joueur qui effectue la défausse.
     */
    @Override
    public void process(Board board, Player player) {
        if (player.hasTokens(resource, amount)) {
            player.removeTokens(resource, amount);
            board.updateNbResource(resource, amount);
        } else {
            throw new IllegalStateException("Pas assez de jetons à défausser.");
        }
    }

    /**
     * Retourne une description de l'action.
     * 
     * @return une chaîne décrivant l'action.
     */
    @Override
    public String toString() {
        return "Défausser " + amount + " " + resource.toSymbol();
    }
}


