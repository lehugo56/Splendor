import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Board implements Displayable {
    private Stack<DevCard>[] deck;
    private DevCard[][] visibleCards;
    private Map<Resource, Integer> resources;
    /* --- Stringers --- */

    private String[] deckToStringArray(int tier){
        /** EXAMPLE
         * ┌────────┐
         * │        │╲ 
         * │ reste: │ │
         * │   16   │ │
         * │ cartes │ │
         * │ tier 3 │ │
         * │        │ │
         * └────────┘ │
         *  ╲________╲│
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
    
    @SuppressWarnings("unchecked")
    public Board(int numPlayers) {
        deck = new Stack[3];
        visibleCards = new DevCard[3][4];
        resources = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            deck[i] = new Stack<>();
        }

        initializeResources(numPlayers);
        initializeDecks();
        initializeVisibleCards();
    }
    
    private void initializeResources(int numPlayers) {
        int count = switch (numPlayers) {
            case 2 -> 4;
            case 3 -> 5;
            case 4 -> 7;
            default -> throw new IllegalArgumentException("Nombre de joueurs invalide");
        };

        for (Resource resource : Resource.values()) {
            resources.put(resource, count);
        }
    }
    
    private void initializeVisibleCards() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (!deck[i].isEmpty()) {
                    visibleCards[i][j] = deck[i].pop();
                }
            }
        }
    }
    
    public Set<Resource> getAvailableResources() {
        Set<Resource> available = new HashSet<>();
        for (Map.Entry<Resource, Integer> entry : resources.entrySet()) {
            if (entry.getValue() > 0) {
                available.add(entry.getKey());
            }
        }
        return available;
    }
    
    public int getNbResource(Resource resource) {
        return resources.getOrDefault(resource, 0);
    }

    public void setNbResource(Resource resource, int count) {
        resources.put(resource, count); 
    }
    
    private void initializeDecks() {
        // A faire en fonction de DevCard
        // Ici on charge les cartes en decks/niveau
    }
    
    public DevCard getCard(int level, int column) {
        return visibleCards[level][column];
    }
    
    public boolean canGiveSameTokens(Resource resource) {
        return getNbResource(resource) >= 4;
    }
    
    public boolean canGiveDiffTokens(List<Resource> requiredResources) {
        for (Resource resource : requiredResources) {
            if (getNbResource(resource) < 1) {
                return false;
            }
        }
        return true;
    }
    
    private String[] resourcesToStringArray(){
        /** EXAMPLE
         * Resources disponibles : 4♥R 4♣E 4♠S 4♦D 4●O
         */
        // resStr en StringBuilder, sinon erreur
        StringBuilder resStr = new StringBuilder("Ressources disponibles : ");
        for (Resource resource : Resource.values()) {
            resStr.append(getNbResource(resource)).append(resource.toSymbol()).append(" ");
        }
        return new String[]{resStr.toString()};
    }

    private String[] boardToStringArray(){
        // Affichage deck
        String[] deckDisplay = Display.emptyStringArray(0, 0);
        for (int i = 2; i >= 0; i--) {
            deckDisplay = Display.concatStringArray(deckDisplay, deckToStringArray(i + 1), true);
        }
        // Affichage carte
        String[] cardDisplay = Display.emptyStringArray(0, 0);
        for (int i = 0; i < 3; i++) {
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

    @Override
    public String[] toStringArray() {
        return boardToStringArray();
    }
}
