import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Représente le plateau de jeu de Splendor.
 * La classe gère les ressources, les piles de cartes de développement,
 * les cartes visibles et les interactions avec le plateau.
 */
public class Board implements Displayable {
    private Stack<DevCard>[] deck; // Piles de cartes de développement par niveau
    private DevCard[][] visibleCards; // Cartes visibles par niveau (4 par niveau)
    private Resources resources; // Quantité de ressources disponibles sur le plateau

    /**
     * Constructeur de la classe `Board`.
     * Initialise les piles de cartes, les cartes visibles et les ressources en fonction du nombre de joueurs.
     * 
     * @param numPlayers le nombre de joueurs (entre 2 et 4).
     */
    @SuppressWarnings("unchecked")
    public Board(int numPlayers) {
        deck = new Stack[3]; // Trois niveaux de cartes
        visibleCards = new DevCard[3][4]; // Quatre cartes visibles par niveau
        resources = new Resources();

        for (int i = 0; i < 3; i++) {
            deck[i] = new Stack<>();
        }

        initializeResources(numPlayers);
        initializeDecks();
        initializeVisibleCards();
    }

    private String[] deckToStringArray(int tier){
        /** EXAMPLE
         * ┌────────┐
         * │         │╲ 
         * │ reste:  │ │
         * │   16    │ │
         * │ cartes  │ │
         * │ tier 3  │ │
         * │         │ │
         * └────────┘ │
         *  ╲__________╲│
         */
        int nbCards = deck[tier - 1].size();
        String[] deckStr = {"\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510  ",
                            "\u2502        \u2502\u2572 ",
                            "\u2502 reste: \u2502 \u2502",
                            "\u2502   "+String.format("%02d", nbCards)+"   \u2502 \u2502",
                            "\u2502 carte"+(nbCards>1 ? "s" : " ")+" \u2502 \u2502",
                            "\u2502 tier "+tier+" \u2502 \u2502",
                            "\u2502        \u2502 \u2502",
                            "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518 \u2502",
                            " \u2572________\u2572\u2502"};
        return deckStr;
    }
    
    /**
     * Initialise les ressources en fonction du nombre de joueurs.
     * 
     * @param numPlayers le nombre de joueurs.
     */
    private void initializeResources(int numPlayers) {
        int count = switch (numPlayers) {
            case 2 -> 4; // 4 ressources par type pour 2 joueurs
            case 3 -> 5; // 5 ressources par type pour 3 joueurs
            case 4 -> 7; // 7 ressources par type pour 4 joueurs
            default -> throw new IllegalArgumentException("Nombre de joueurs invalide");
        };

        for (Resource resource : Resource.values()) {
            resources.updateNbResource(resource, count);
        }
    }
    
    public int StringToInt(String number){
        if(number.equals("0")){
            return 0;
        }else if(number.equals("1")){
            return 1;
        }else if(number.equals("2")){
            return 2;
        }else if(number.equals("3")){
            return 3;
        }else if(number.equals("4")){
            return 4;        
        }else if(number.equals("5")){
            return 5;
        }else if(number.equals("6")){
            return 6;
        }else if(number.equals("7")){
            return 7;
        }else{
            return 8;
        }
    }
    
    
     /**
     * Initialise les piles de cartes de développement.
     * Les cartes sont chargées dans les piles selon leur niveau.
     * (À compléter avec des détails spécifiques au projet)
     */
    private void initializeDecks() {
      try{
      File CSVfile = new File("stats.csv");
      Scanner myReader = new Scanner(CSVfile);
      String data = myReader.nextLine();
      while (myReader.hasNextLine()) {
        data = myReader.nextLine();
        String[] datas = data.split(",");
        System.out.println(Arrays.toString(datas));
        if (!datas[0].equals("0")){     // evite les carte noble
            DevCard card = new DevCard(
                    StringToInt(datas[0]),                  // Niveau
                    StringToInt(datas[1]),              // Coût en DIAMOND
                    StringToInt(datas[2]),          // Coût en SAPPHIRE
                    StringToInt(datas[3]),          // Coût en EMERALD
                    StringToInt(datas[4]),          // Coût en RUBY
                    StringToInt(datas[5]),          // Coût en ONYX
                    StringToInt(datas[6]),                  // Points de prestige
                    datas[7] //Resource.values()[i % 5].toString() // Type de ressource (en rotation)
                );
                deck[StringToInt(datas[0]) - 1].push(card);
        }
      }
      myReader.close();
        // // Mélanger les piles pour aléatoiriser les cartes
      for (Stack<DevCard> stack : deck) {
          Collections.shuffle(stack);
      }
      }catch(FileNotFoundException e){}
      
    } 


    /**
     * Initialise les cartes visibles en tirant les premières cartes de chaque pile.
     */
    private void initializeVisibleCards() {
        for (int i = 0; i < 3; i++) { // Parcours des niveaux
            for (int j = 0; j < 4; j++) { // 4 colonnes par niveau
                if (!deck[i].isEmpty()) {
                    visibleCards[i][j] = deck[i].pop();
                }
            }
        }
    }

    /**
     * Retourne les types de ressources disponibles (quantité > 0).
     * 
     * @return un ensemble des ressources disponibles.
     */
    public int[] getAvailableResources() {
        return resources.getAvailableResources();
    }

    /**
     * Retourne le nombre de jetons disponibles pour une ressource donnée.
     * 
     * @param resource le type de ressource.
     * @return la quantité de la ressource.
     */
    public int getNbResource(Resource resource) {
        return resources.getNbResource(resource);
    }

    /**
     * Définit une nouvelle quantité pour une ressource donnée.
     * 
     * @param resource le type de ressource.
     * @param count la nouvelle quantité.
     */
    public void setNbResource(Resource resource, int count) {
        resources.updateNbResource(resource, count - resources.getNbResource(resource)); 
    }

    /**
     * Met à jour la quantité d'une ressource (ajoute ou retire des jetons).
     * 
     * @param resource le type de ressource.
     * @param delta la quantité à ajouter (positive) ou retirer (négative).
     */
    public void updateNbResource(Resource resource, int delta) {
        resources.updateNbResource(resource , delta);
    }

    /**
     * Retourne une carte visible à un niveau et une colonne donnés.
     * 
     * @param level le niveau de la carte (0 à 2).
     * @param column la colonne de la carte (0 à 3).
     * @return la carte correspondante, ou `null` si la position est vide.
     */
    public DevCard getCard(int level, int column) {
        return visibleCards[level][column];
    }

    /**
     * Met à jour une carte visible en tirant une nouvelle carte de la pile.
     * Si la pile est vide, la position devient vide (`null`).
     * 
     * @param level le niveau de la carte.
     * @param column la colonne de la carte.
     */
    public void updateCard(int level, int column) {
        if (!deck[level].isEmpty()) {
            visibleCards[level][column] = deck[level].pop();
        } else {
            visibleCards[level][column] = null;
        }
    }

    /**
     * Vérifie si un joueur peut prendre deux jetons de la même ressource.
     * 
     * @param resource le type de ressource.
     * @return `true` si au moins 4 jetons sont disponibles, sinon `false`.
     */
    public boolean canGiveSameTokens(Resource resource) {
        return getNbResource(resource) >= 4;
    }

    /**
     * Vérifie si un joueur peut prendre trois jetons de ressources différentes.
     * 
     * @param requiredResources la liste des ressources demandées.
     * @return `true` si chaque ressource est disponible, sinon `false`.
     */
    public boolean canGiveDiffTokens(List<Resource> requiredResources) {
        for (Resource resource : requiredResources) {
            if (getNbResource(resource) < 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Représente les ressources disponibles sous forme de chaîne.
     * 
     * @return un tableau avec la représentation des ressources.
     */
    private String[] resourcesToStringArray(){
        StringBuilder resStr = new StringBuilder("Ressources disponibles : ");
        for (Resource resource : Resource.values()) {
            resStr.append(getNbResource(resource)).append(resource.toSymbol()).append(" ");
        }
        return new String[]{resStr.toString()};
    }

    /**
     * Représente le plateau sous forme de tableau de chaînes.
     * 
     * @return un tableau décrivant le plateau.
     */
    public String[] boardToStringArray(){
        String[] deckDisplay = Display.emptyStringArray(0, 0);
        for (int i = 2; i >= 0; i--) {
            deckDisplay = Display.concatStringArray(deckDisplay, deckToStringArray(i + 1), true);
        }

        String[] cardDisplay = Display.emptyStringArray(0, 0);
        for (int i = 2; i > -1; i--) {
            String[] tierCardsDisplay = Display.emptyStringArray(8, 0);
            for (int j = 0; j < 4; j++) {
                tierCardsDisplay = Display.concatStringArray(tierCardsDisplay,
                        visibleCards[i][j] != null ? visibleCards[i][j].toStringArray() : DevCard.noCardStringArray(), false);
            }
            cardDisplay = Display.concatStringArray(cardDisplay, Display.emptyStringArray(1, 40), true);
            cardDisplay = Display.concatStringArray(cardDisplay, tierCardsDisplay, true);
        }

        String[] boardDisplay = Display.concatStringArray(deckDisplay, cardDisplay, false);
        boardDisplay = Display.concatStringArray(boardDisplay, resourcesToStringArray(), true);

        return boardDisplay;
    }

    /**
     * Retourne une représentation du plateau sous forme de tableau de chaînes.
     * 
     * @return un tableau décrivant le plateau.
     */
    @Override
    public String[] toStringArray() {
        return boardToStringArray();
    }
}