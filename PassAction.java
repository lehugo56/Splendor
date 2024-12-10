/**
 * Représente une action où un joueur passe son tour.
 */
public class PassAction implements Action {

    /**
     * Exécute l'action de passer son tour.
     * 
     * @param board le plateau de jeu.
     * @param player le joueur qui passe son tour.
     */
    @Override
    public void process(Board board, Player player) {
        // Pas de modification du plateau ou du joueur
    }

    /**
     * Retourne une description de l'action.
     * 
     * @return une chaîne décrivant l'action.
     */
    @Override
    public String toString() {
        return "Passer son tour";
    }
}

