import java.util.ArrayList;
public abstract class Player implements Displayable {

    private static int id_cpt=0;
    private int id;
    private String name;
    private int points;
    private ArrayList<DevCard> purchaseCards;
    private Resources resources;
    
    public  Player(String name){
        this.name=name;
        points=0;
        purchaseCards=new ArrayList<DevCard>();
        resources=new Resources();
        id_cpt++;//permet d'augmenter l'id a chaque initialisation.
        id=id_cpt;
    }

    /**
     * Accesseur de l'attribut nom
     * 
     * @return nom du joueur
     */
    public String getNom(){
        return name;
    }

    /**
     * Accesseur du prestige (nombre de points) du joueur
     * 
     * @return nombre de points
     */
    public int getPoints(){
        return points;
    }

    /**
     * Accesseur du nombre de tokens 
     * 
     * @return 
     */
    public Resources getNbTokens(){//peut etre un probleme ici
        return resources;
    }

    /**
     *  Accesseur du nombre de cartes achetées
     *  
     *  @return nombre de cartes achetées
     */
    public ArrayList<DevCard> getNbPurchasedCards(){
        return purchaseCards;
    }
    
    /**
     * Accesseur du nombre de ressources (jetons) du joueur
     * 
     * @return nombre de ressources
     */
    public  int getNbResource(Resource elements_resources){
         return resources.getNbResource(elements_resources);
    }

    /**
     * Accesseur la liste des ressources disponibles
     * 
     * @return 
     */
    public int[] getAvailableResources(){
        resources.getAvailableResources();
    }
    
    /**
     *  Retourne le nombre de jetons bonus parmi les cartes achetées pour un type donné
     *  
     *  @param type de la ressource
     *  @return le nombre de jetons bonus
     */
    public int getResFromCards (Resource type){
        int count = 0;
        for (DevCard card : purchaseCards){
            if (card.getRessourceType() == type){
                count++;
            }
        }
    }

    /**
     * Vérifie si le joueur a assez de ressources pour acheter une carte.
     * 
     * @return true si le joueur peut acheter la carte, false sinon
     */
    private boolean canBuyCard() {
        return getNbResource() >= getRes(); 
    }

    /**
     * Met à jour le nombre de ressource (pour un type donné) en fonction de v
     * si v > 0, ajoute
     * si v < 0, supprime
     * 
     * @param le type de ressource & v 
     * @return le nombre de ressource actualisé
     */
    public void updateNbResource(Resource elements_resources, int v){
        resources.updateNbResource(elements_resources, v);
    }

    /**
    * Met à jour le nombre de points du joueur
    *
    * @param le nombre de points ajoutés
    * @return le nombre de points actualisés du joueur
    */
    public void updatePoints(int points){
        prestige+=points;
    }

    /**
    * Ajoute une carte achetée à la liste
    *
    * @param la carte achetée
    */
    public void addPurchasedCard(DevCard card) {
        purchaseCards.add(card);
    }
    public String[] toStringArray(){
        /** EXAMPLE. The number of resource tokens is shown in brackets (), and the number of cards purchased from that resource in square brackets [].
         * Player 1: Camille
         * 
         * ⓪pts
         * 
         * ♥R (0) [3]
         * ●O (0) [4]
         * ♣E (0) [5]
         * ♠S (0) [6]
         * ♦D (0) [7]
         */
        String pointStr = " ";
        String[] strPlayer = new String[8];
        
        if(points>0){
            pointStr = new String(new int[] {getPoints()+9311}, 0, 1);
        }else{
            pointStr = "\u24EA";
        }

        
        strPlayer[0] = "Player "+(id+1)+": "+name;
        strPlayer[1] = pointStr + "pts";
        strPlayer[2] = "";
        for(Resources res : resources){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            strPlayer[3+(Resource.values().length-1-res.ordinal())] = res.toSymbol() + " ("+resources.getNbResource(res)+") ["+getResFromCards(res)+"]";
        }
        
        return strPlayer;
    }
}
