/**
 * Décrivez votre classe HumanPlayer ici.
 *
 * @author Manon
 * @version (un numéro de version ou une date)
 */
public class HumanPlayer extends Player
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private ArrayList<DevCard> purchasedCards;
    private Resources resources;
    
    private static final String BUYCARD = A;
    private static final String PICKSAMETOKENS = B;
    private static final String PICKDIFFERENTTOKENS = C;
    private static final String PASSACTION = D;
    
    /**
     * Constructeur d'objets de classe HumanPlayer
     */
    public HumanPlayer(String name)
    {
        super(name);
        this.purchasedCards = new ArrayList<DevCards>();
        this.resources = new Resources();
    }
    
    /**
     * Choix de l'action faites
     * 
     * @param L'action choisie par le joueur
     * @return 
     */
    public void chooseAction (int action) {
        
        switch(action)
        {   
           case A: // Acheter une carte
               BuyCardAction.process();  // Doit etre fait pour chaque carte choisie (A FAIRE)  
           case B: // Retirer 2 jetons du meme type de ressources
               PickSameTokenAction.process();
           case C: // Retirer 3 jetons de type diff
               PickDiffTokensAction.process();
           case D : // Passer l'action
               PassAction();
        }
        
        while (getNbResource() > 10){
            DiscardTokensAction();
        }
    
    }
    
    /**
     * Permet de défausser un nombre n de Tokens.
     * 
     * @param nbTokens le nombre de tokens dont on veut se débarasser
     * 
     */
    public void chooseDiscardingTokens(int nbTokens) {
         getNbTokens -= nbTokens;
    }
}
