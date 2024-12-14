import java.util.ArrayList;
import java.util.Random;

/**
 * Décrivez votre classe DumbRobotPlayer ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class DumbRobotPlayer extends Player
{
    public DumbRobotPlayer(String name){
        super(name);
    }
    
    public Resource TypeResourcPlayer (int reponse) {
        Resource resource;

        if (reponse == 1){
            resource = Resource.DIAMOND;
        }else if (reponse == 2){
            resource = Resource.SAPPHIRE;
        }else if (reponse == 3){
            resource = Resource.EMERALD;
        }else if (reponse == 4){
            resource = Resource.RUBY;
        }else if (reponse == 5){
            resource = Resource.ONYX;
        }else{
            Game.display.out.println("Vous avez mal écrit le type de la ressource " + reponse);
            resource = null;
        }
           
        return resource;
    }
    
    public Action chooseAction(Board board){ 
    for(int level = 3 ; level > 0; level--){
        for(int column = 1 ; column <= 4; column++){
            if (canBuyCard(board.getCard(level , column))){
                return new BuyCardAction(level, column);
            }
        }
    }
    
    for(Resource res :  Resource.values()){
        if(board.canGiveSameTokens(res)){
            return new PickSameTokensAction(res);
        }
    }
    
    int[] ressourcedispo = board.getAvailableResources(); 
    
    if(ressourcedispo.length >= 3){
        ArrayList<Resource> listRes = new ArrayList<Resource>(); 
        for(int count = 0; count < 3; count++){
            listRes.add(TypeResourcPlayer(ressourcedispo[count]));
        }
        return new PickDiffTokensAction(listRes);
    }
    
    
    return new PassAction();
    }
    /**
     * Permet de défausser un nombre n de Tokens.
     * 
     * @param nbTokens le nombre de tokens dont on veut se débarasser
     * 
     */
    public DiscardTokensAction chooseDiscardingTokens(int nbTokens){
        int[] ressourcedispo = getAvailableResources();
        
        Random randomNumbers = new Random();
        int random = randomNumbers.nextInt(ressourcedispo.length); 
        
        
        return new DiscardTokensAction( TypeResourcPlayer(random) , nbTokens);
    }
}
