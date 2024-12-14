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
        reponse = reponse.toUpperCase();
        if (reponse.equals("DIAMOND")){
            resource = Resource.DIAMOND;
        }else if (reponse.equals("SAPPHIRE")){
            resource = Resource.SAPPHIRE;
        }else if (reponse.equals("EMERALD")){
            resource = Resource.EMERALD;
        }else if (reponse.equals("RUBY")){
            resource = Resource.RUBY;
        }else if (reponse.equals("ONYX")){
            resource = Resource.ONYX;
        }else{
            Game.display.out.println("Vous avez mal écrit le type de la ressource " + reponse);
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
    public Action chooseAction(Board board) {
        Scanner scanner = new Scanner( Game.display.in );
        Game.display.out.println("Quelle action souhaitez-vous effectuer ?");
        Game.display.out.println("A : Acheter une carte");
        Game.display.out.println("B : Retirer 2 jetons du meme type de ressource");
        Game.display.out.println("C : Retirer 3 jetons de type différents");
        Game.display.out.println("D : Passer son tour");
        String action = scanner.nextLine();
        switch(action.toUpperCase())
        {   
           case "A": // Acheter une carte
               Game.display.out.println("Quelle est la colonne de la carte que vous voulez acheter ?");
               Game.display.out.println("Entrez : 1, 2, 3 ou 4");
               int column = scanner.nextInt();
               Game.display.out.println(" ");
               Game.display.out.println("Quelle est le niveau de la carte que vous voulez acheter ?");
               Game.display.out.println("Entrez : 1, 2 ou 3");
               int level = scanner.nextInt();
               Game.display.out.println(" ");
               return new BuyCardAction(level - 1, column - 1);
  
               
           case "B": // Retirer 2 jetons du meme type de ressource
               Game.display.out.println("Pour quelle ressource voulez-vous récuperer 2 jetons ?");
               Game.display.out.println("Diamond, Sapphire, Emerald, Ruby ou Onyx");
               String reponse = scanner.nextLine();
               Game.display.out.println(" ");
               return new PickSameTokensAction(TypeResourcPlayer(reponse));
 
               
           case "C": // Retirer 3 jetons de type diff
               ArrayList<Resource> listeRes = new ArrayList<Resource>();
               for (int i = 1; i<4 ; i++){
                   Game.display.out.println("Quelle ressource voulez-vous prendre ?");
                   Game.display.out.println("Entrez : Diamond, Sapphire, Emerald, Ruby ou Onyx");
                   reponse = scanner.nextLine();
                   listeRes.add(TypeResourcPlayer(reponse));
                   if (listeRes != null){
                       Game.display.out.println("Vous avez déja pris : " + listeRes);
                   }
                   Game.display.out.println(" ");
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
        Scanner scanner = new Scanner( Game.display.in );
        
        Game.display.out.println("Quelle ressource voulez-vous supprimer?");
        String reponse = scanner.nextLine();
        
        return new DiscardTokensAction(TypeResourcPlayer(reponse), nbTokens);
    }
}
