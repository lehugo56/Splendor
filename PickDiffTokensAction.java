import java.util.List;

/**
 * Représente une action où un joueur prend trois jetons de ressources différentes.
 */
public class PickDiffTokensAction implements Action {
    private List<Resource> resources;

    /**
     * Constructeur de l'action.
     * 
     * @param resources la liste des trois ressources choisies.
     */
    public PickDiffTokensAction(List<Resource> resources) {
        if (resources.size() != 3) {
            throw new IllegalArgumentException("Il faut sélectionner exactement trois ressources différentes.");
        }
        this.resources = resources;
    }

    /**
     * Exécute l'action de prendre trois jetons de ressources différentes.
     * 
     * @param board le plateau de jeu.
     * @param player le joueur qui effectue l'action.
     */
    @Override
    public void process(Board board, Player player) {
        if (board.canGiveDiffTokens(resources)) {
            for (Resource resource : resources) {
                board.updateNbResource(resource, -1);
                player.addTokens(resource, 1);
            }
        } else {
            throw new IllegalStateException("Pas assez de jetons disponibles pour prendre trois ressources différentes.");
        }
    }

    /**
     * Retourne une description de l'action.
     * 
     * @return une chaîne décrivant l'action.
     */
    @Override
    public String toString() {
        return "Prendre 3 jetons différents : " + resources.toString();
    }
}

