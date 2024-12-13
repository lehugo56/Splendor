import java.util.Scanner;
import java.util.ArrayList;

public class DevCard implements Displayable {
    
    private int tier;
    private Resources resource; 
    private int point;
    private Resource resourceType;
    
    
    /**
     * Constructeur de la classe DevCard
     * input : int tier (niveau de la carte) int coutDIAMOND (cout en diament de la carte) int coutSAPPHIRE (cout en sapphire de la carte)
     * int coutEMERALD (cout en emeraude de la carte) int coutRUBY (cout en ruby de la carte) int coutONYX (cout en onyx de la carte)
     * int point (nombre de point de prestige renporté l'or de l'obtention de la carte) String resourceType (type de resource remporté avec la carte)
     */
    public DevCard(int tier ,int coutDIAMOND,int coutSAPPHIRE,int coutEMERALD , int coutRUBY , int coutONYX , int point , String resourceType)
    {
        this.tier = tier;
        resource = new Resources();
        resource.setNbResource(Resource.DIAMOND , coutDIAMOND);
        resource.setNbResource(Resource.SAPPHIRE , coutSAPPHIRE);
        resource.setNbResource(Resource.EMERALD , coutEMERALD);
        resource.setNbResource(Resource.RUBY , coutRUBY);
        resource.setNbResource(Resource.ONYX , coutONYX);
        this.point = point;
        if (resourceType.equals("DIAMOND")){
            this.resourceType = Resource.DIAMOND;
        }else if (resourceType.equals("SAPPHIRE")){
            this.resourceType = Resource.SAPPHIRE;
        }else if (resourceType.equals("EMERALD")){
            this.resourceType = Resource.EMERALD;
        }else if (resourceType.equals("RUBY")){
            this.resourceType = Resource.RUBY;
        }else if (resourceType.equals("ONYX")){
            this.resourceType = Resource.ONYX;
        }else{
            System.out.println("Vous avez mal écrit le type de la ressource ");
            resourceType = null;
        }
    }
    
    public String[] toStringArray(){
        /** EXAMPLE
         * ┌────────┐
         * │①    ♠S│
         * │        │
         * │        │
         * │2 ♠S    │
         * │2 ♣E    │
         * │3 ♥R    │
         * └────────┘
         */
        String pointStr = "  ";
 
        if(getPoints()>0){
            pointStr = new String(new int[] {getPoints()+9311}, 0, 1);
        }
        String[] cardStr = {"\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510",
                            "\u2502"+pointStr+"    "+ resourceType.toSymbol() +"\u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518"};

        int i=6;
        for(Resource res :  Resource.values()){ //-- parcourir l'ensemble des resources (res)en utilisant l'énumération Resource
            if( resource.getNbResource(res)>0){
                cardStr[i] = "\u2502"+ resource.getNbResource(res) +" "+res.toSymbol()+"    \u2502";
                i--;
            }
        }
        return cardStr;
    }

    public static String[] noCardStringArray(){
        /** EXAMPLE
         * ┌────────┐
         * │ \    / │
         * │  \  /  │
         * │   \/   │
         * │   /\   │
         * │  /  \  │
         * │ /    \ │
         * └────────┘
         */
        String[] cardStr = {"\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510",
                            "\u2502 \\    / \u2502",
                            "\u2502  \\  /  \u2502",
                            "\u2502   \\/   \u2502",
                            "\u2502   /\\   \u2502",
                            "\u2502  /  \\  \u2502",
                            "\u2502 /    \\ \u2502",
                            "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518"};
        
        return cardStr;
    }

    public String toString(){
        String cardStr = "";
      
        
        cardStr = getPoints()+"pts, type "+resourceType.toSymbol()+" | coût: ";
        for(Resource res :  Resource.values()){ 
            if(resource.getNbResource(res)>0){
                cardStr += resource.getNbResource(res)+res.toSymbol()+" ";
            }
        }
        
        return cardStr;
    }
    
    /**
     * Accesseur de ressource
     * 
     * @return une liste contenant le nombre de ressources nécessaires pour chaque type
     */
    public Resources getRes(){
        return resource;
    }
    
    /**
     * Accesseur du nombre de points (de prestige) de la carte
     * 
     * @return nombre de point associé à la carte
     */
    public int getPoints(){
        return point;
    }
    
    /**
     * Accesseur du type de ressource de la carte 
     * 
     * @return le type du bonus conféré par la carte
     */
    public Resource getRessourceType(){
        return resourceType;
    }
}
