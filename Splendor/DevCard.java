import java.util.Scanner;
import java.util.ArrayList;

public class DevCard implements Displayable {
    
    private int tier;
    private Resources resource; 
    private int point;
    private Resource resourceType;
    
    public DevCard(int tier ,int coutDIAMOND,int coutSAPPHIRE,int coutEMERALD , int coutRUBY , int coutONYX , int point , String resourceType)
    {
        this.tier = tier;
        resource.setNbResource(Resource.DIAMOND , coutDIAMOND);
        resource.setNbResource(Resource.SAPPHIRE , coutSAPPHIRE);
        resource.setNbResource(Resource.EMERALD , coutEMERALD);
        resource.setNbResource(Resource.RUBY , coutRUBY);
        resource.setNbResource(Resource.ONYX , coutONYX);
        this.point = point;
        switch(resourceType)
        {
            case "DIAMOND":
                this.resourceType = Resource.DIAMOND;
            case "SAPPHIRE":
                this.resourceType = Resource.SAPPHIRE;
            case "EMERALD":
                this.resourceType = Resource.EMERALD;
            case "RUBY":
                this.resourceType = Resource.RUBY;
            case "ONYX":
                this.resourceType = Resource.ONYX;
            default:
                this.resourceType = null;
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
    
    
    public Resources getres(){
        return resource;
    }
    
    public int getPoints(){
        return point;
    }
    
    public Resource getressourceType(){
        return resourceType;
    }
}