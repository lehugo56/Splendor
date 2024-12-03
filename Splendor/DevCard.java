import java.util.Scanner;
import java.util.ArrayList;

public class DevCard implements Displayable {
    
    private int tier;
    private Resources res; 
    private int point;
    private String resourceType;
    
    public DevCard(int tier ,int coutDIAMOND,int coutSAPPHIRE,int coutEMERALD , int coutRUBY , int coutONYX , int point , String resourceType)
    {
        this.tier = tier;
        res.setNbResource(1 , coutDIAMOND);
        res.setNbResource(1 , coutSAPPHIRE);
        res.setNbResource(1 , coutEMERALD);
        res.setNbResource(1 , coutRUBY);
        res.setNbResource(1 , coutONYX);
        this.point = point;
        this.resourceType = resourceType;    
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
        String[] cardStr = {}; //-- ASUPPRIMER
        /*
         * Ce code est à décommenter une fois que la classe DevCard a été implémentée
        if(getPoints()>0){
            pointStr = new String(new int[] {getPoints()+9311}, 0, 1);
        }
        String[] cardStr = {"\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510",
                            "\u2502"+pointStr+"    "+resourceType.toSymbol()+"\u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518"};
        //update cost of the repr
        int i=6;
        for(ACOMPLETER){ //-- parcourir l'ensemble des resources (res)en utilisant l'énumération Resource
            if(getCost().getNbResource(res)>0){
                cardStr[i] = "\u2502"+getCost().getNbResource(res)+" "+res.toSymbol()+"    \u2502";
                i--;
            }
        } */
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
        /*
         * Ce code est à décommenter une fois que la classe DevCard a été implémentée
              
        cardStr = getPoints()+"pts, type "+resourceType.toSymbol()+" | coût: ";
        for(ACOMPLETER){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            if(getCost().getNbResource(res)>0){
                cardStr += getCost().getNbResource(res)+res.toSymbol()+" ";
            }
        }
        */
        return cardStr;
    }
    
    
    public Resources getres(){
        return res;
    }
    
    public int getPoints(){
        return point;
    }
    
    public String getresourceType(){
        return resourceType;
    }
}