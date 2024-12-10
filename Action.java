/**
 * Représente une action dans le jeu Splendor.
 * Cette interface définit les actions pouvant être exécutées sur le plateau et par les joueurs.
 */
public interface Action {
    /**
     * Exécute l'action donnée sur le plateau et par un joueur.
     * 
     * @param board le plateau de jeu.
     * @param player le joueur qui effectue l'action.
     */
    void process(Board board, Player player);

    /**
     * Représente l'action sous forme de chaîne de caractères.
     * 
     * @return une description de l'action.
     */
    String toString();
}

