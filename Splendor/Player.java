import java.util.ArrayList;
import java.util.Set;

public abstract class Player implements Displayable {

    private static int id_cpt=0;
    private int id;
    private String name;
    private int points;
    private ArrayList<DevCard> purchaseCards;
    private Resources resources;
    
    public  Player(String name){
        this.name = name;
        points = 0;
        purchaseCards = new ArrayList<DevCard>();
        resources = new Resources();
        id_cpt++; //permet d'augmenter l'id a chaque initialisation.
        id=id_cpt;
    }
    
    /**
     * Accesseur de l'attribut nom
     * 
     * @return nom du joueur
     */
    public String getName(){
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
     * Accesseur du nombre total de ressources achetées
     * 
     * @return nombre de ressource total
     */
    public int getNbTokens(){ 
        return resources.getNbResource(Resource.DIAMOND) + resources.getNbResource(Resource.SAPPHIRE) + resources.getNbResource(Resource.EMERALD) 
        + resources.getNbResource(Resource.RUBY) + resources.getNbResource(Resource.ONYX);
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
     * Accesseur du nombre de ressources (jetons + bonus) du joueurpour un type donné
     * 
     * @return nombre de ressources
     */
    public  int getNbResource(Resource elements_resources){
         return resources.getNbResource(elements_resources);
    }
    
    /**
     * Accesseur de la liste des ressources (en jetons) disponibles du joueur
     * 
     * @return liste des indices des ressources dispo
     */
    public  int[] getAvailableResources(){
        return resources.getAvailableResources();
    }
    
    /**
     * Vérifie si le joueur a assez de ressources pour acheter une carte.
     * 
     * @return true si le joueur peut acheter la carte, false sinon
     */
    public boolean canBuyCard(DevCard card) {
        for (Resource resource : Resource.values()) {
            int cost = card.getRes().getNbResource(resource);
            int available = getNbResource(resource) + getResFromCards(resource);
            if (available < cost) {
                return false;
            }
        }
        return true;
    }
        public void buyCard(int level,int column,Board board){
        DevCard card=board.getCard(level,column);
        if (canBuyCard( card)){
            for (Resource resource : Resource.values()) {
                int res = card.getRes().getNbResource(resource);
                board.updateNbResource(resource, res);//ajoute les ressources sur le plateau
                resources.updateNbResource(resource,-res);//eleve les ressource au joueur
            }
            purchaseCards.add(card);
            board.updateCard(level,column);
        }
        
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
        return count;
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

        
        strPlayer[0] = "Player "+(id)+": "+name;
        strPlayer[1] = pointStr + "pts";
        strPlayer[2] = "";
        for(Resource res : Resource.values()){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            strPlayer[3+(Resource.values().length-1-res.ordinal())] = res.toSymbol() + " ("+resources.getNbResource(res)+") ["+getResFromCards(res)+"]";
        }
        
        return strPlayer;
    }
    
    /**
     * Choix de l'action faites
     * 
     * @param L'action choisie par le joueur
     * 
     */
    abstract Action chooseAction (Board board);
    
    /**
     * Permet de défausser un nombre n de Tokens.
     * 
     * @param nbTokens le nombre de tokens dont on veut se débarasser
     * 
     */
    abstract DiscardTokensAction chooseDiscardingTokens(int nbTokens);
}
