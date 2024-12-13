import java.util.Scanner;
import java.util.ArrayList;

/**
 * Décrivez votre classe HumanPlayer ici.
 *
 * @author Manon
 * @version (un numéro de version ou une date)
 */
public class HumanPlayer extends Player
{
    
    /**
     * Constructeur d'objets de classe HumanPlayer
     */
    public HumanPlayer(String name)
    {
        super(name);
    }
    
    /**
     * Convertis la réponse du joueur (String) en type Resource
     * 
     * @param La réponse du joueur à une question posée
     * @return Le type Resource associé à la rep
     */
    public Resource TypeResourcPlayer (String reponse) {
        Resource resource;
        switch(reponse)
            {
                case "DIAMOND":
                    resource = Resource.DIAMOND;
                case "SAPPHIRE":
                    resource = Resource.SAPPHIRE;
                case "EMERALD":
                    resource = Resource.EMERALD;
                case "RUBY":
                    resource = Resource.RUBY;
                case "ONYX":
                    resource = Resource.ONYX;
                default:
                    System.out.println("Vous avez mal écrit le type de la ressource");
                    resource = null;
            }
        return resource;
    }
    
    /**
     * Choix de l'action faites
     * 
     * @param L'action choisie par le joueur
     * 
     */
    public Action chooseAction() {
        Scanner scanner = new Scanner( System.in );
        System.out.println("Quelle action souhaitez-vous effectuer ?");
        System.out.println("A : Acheter une carte");
        System.out.println("B : Retirer 2 jetons du meme type de ressource");
        System.out.println("C : Retirer 3 jetons de type différents");
        System.out.println("D : Passer son tour");
        String action = scanner.nextLine();
        switch(action)
        {   
           case "A": // Acheter une carte
               System.out.println("Quelle est la colonne de la carte que vous voulez acheter ?");
               int column = scanner.nextInt();
               
               System.out.println("Quelle est le niveau de la carte que vous voulez acheter ?");
               int level = scanner.nextInt();
               
               return new BuyCardAction(level, column);
  
               
           case "B": // Retirer 2 jetons du meme type de ressource
               System.out.println("Pour quelle ressource voulez-vous récuperer 2 jetons ?");
               String reponse = scanner.nextLine();
               
               return new PickSameTokensAction(TypeResourcPlayer(reponse));
 
               
           case "C": // Retirer 3 jetons de type diff
               ArrayList<Resource> listeRes = new ArrayList<Resource>();
               for (int i = 0; i<4 ; i++){
                   System.out.println("Quelle ressource voulez-vous prendre ?");
                   reponse = scanner.nextLine();
                   listeRes.add(TypeResourcPlayer(reponse));
               }
               return new PickDiffTokensAction(listeRes);
               

                
           default : // Passer l'action
               return new PassAction();

        }

    }
    /**
     * Permet de défausser un nombre n de Tokens.
     * 
     * @param nbTokens le nombre de tokens dont on veut se débarasser
     */
    public DiscardTokensAction chooseDiscardingTokens(int nbTokens) {
        Scanner scanner = new Scanner( System.in );
        
        System.out.println("Quelle ressource voulez-vous supprimer?");
        String reponse = scanner.nextLine();
        
        return new DiscardTokensAction(TypeResourcPlayer(reponse), nbTokens);
    }
}
