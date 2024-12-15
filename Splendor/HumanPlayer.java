import java.util.*;

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
            Game.display.out.println("Vous avez mal écrit le type de la ressource : " + reponse);
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
        String action = scanner.nextLine();
        
        // Tableau de toutes les réponses possibles pour facilité la vérification
        String[] res = new String[5];
               res[0] = "DIAMOND";
               res[1] = "SAPPHIRE";
               res[2] = "EMERALD";
               res[3] = "RUBY";
               res[4] = "ONYX";
        List<String> list_res = Arrays.asList(res);  // Convertis le tableau en liste
        
        String reponse = " "; // Initialisation de la réponse 
        
        switch(action.toUpperCase())
        {   
           case "A": // Acheter une carte

               int level = 5;
               while (level > 3){
                   Game.display.out.println("Quel est le niveau de la carte que vous voulez acheter ?");
                   Game.display.out.println("Entrez : 1, 2 ou 3");
                    level = scanner.nextInt();
                   Game.display.out.println(" ");
               }
               
               int column = 5;
               while (column > 4){
                   Game.display.out.println("Quelle est la colonne de la carte que vous voulez acheter ?");
                   Game.display.out.println("Entrez : 1, 2, 3 ou 4");
                   column = scanner.nextInt();
                   Game.display.out.println(" ");
               }
               
               return new BuyCardAction(level - 1, column - 1);
  
               
           case "B": // Retirer 2 jetons du meme type de ressource
               while (list_res.contains(reponse) == false){
                   Game.display.out.println("Pour quelle ressource voulez-vous récuperer 2 jetons ?");
                   Game.display.out.println("Entrez : Diamond, Sapphire, Emerald, Ruby ou Onyx");
                   reponse = scanner.nextLine().toUpperCase();
                   Game.display.out.println(" ");
               }
               return new PickSameTokensAction(TypeResourcPlayer(reponse));
 
               
           case "C": // Retirer 3 jetons de type diff
               ArrayList<Resource> listeRes = new ArrayList<Resource>(); // Regroupe les 3 ressources choisies
               for (int i = 1; i<4 ; i++){
                   reponse = "";
                   while (list_res.contains(reponse) == false){ // Demande que l'orthographe soit correct
                     Game.display.out.println("Quelle ressource voulez-vous prendre ?");
                     Game.display.out.println("Entrez : Diamond, Sapphire, Emerald, Ruby ou Onyx");
                     reponse = scanner.nextLine().toUpperCase();
                     Resource rep_converti = TypeResourcPlayer(reponse);
                     
                     if (listeRes.contains(rep_converti) == false && list_res.contains(reponse) == true){ // Si la réponse n'est pas déja présente dans la liste
                         listeRes.add(rep_converti);
                         
                     }
                     else { //sinon on redemande
                         Game.display.out.println(" ");
                         Game.display.out.println("Ce n'est pas correct, veuillez retenter !");
                         Game.display.out.println(" ");
                         reponse = " ";
                     }
                     if (listeRes != null){
                        Game.display.out.println("Vous avez déja pris : " + listeRes);
                     }
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
        String reponse = scanner.nextLine().toUpperCase();
        
        return new DiscardTokensAction(TypeResourcPlayer(reponse), nbTokens);
    }
}
