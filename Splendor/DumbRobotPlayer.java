
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
    
    public Action chooseAction (){
        return new PickSameTokensAction(Resource.ONYX);
    }
    
    /**
     * Permet de défausser un nombre n de Tokens.
     * 
     * @param nbTokens le nombre de tokens dont on veut se débarasser
     * 
     */
    public DiscardTokensAction chooseDiscardingTokens(int nbTokens){
        return new DiscardTokensAction(Resource.ONYX , nbTokens);
    }
}
