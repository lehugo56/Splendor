import java.util.ArrayList;
public abstract class Player implements Displayable {

    private int id;
    private String name;
    private int prestige;
    private ArrayList<DevCard> purchaseCards;
    private Resources resources;
    
    public  Player(int id, String name){
        this.id=id;
        this.name=name;
        prestige=0;
        purchaseCards=new ArrayList<DevCard>();
        resources=new resources();
    }
    public String getnom(){
        return nom;
    }
    public int getpoints(){
        return prestige;
    }
    public  int getNbTokens(){
        
    }
    public  int getAvailableResources(){
        getAvailableResources();
    }
    public String[] toStringArray(){
        /** EXAMPLE. The number of resource tokens is shown in brackets (), and the number of cards purchased from that resource in square brackets [].
         * Player 1: Camille
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
        for(ACOMPLETER){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            strPlayer[3+(Resource.values().length-1-res.ordinal())] = res.toSymbol() + " ("+resources.getNbResource(res)+") ["+getResFromCards(res)+"]";
        }
        
        return strPlayer;
    }
}
