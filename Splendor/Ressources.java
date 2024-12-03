
/**
 * Décrivez votre classe Ressources ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import java.util.ArrayList;
public class Ressources
{
    private int[] listeRessources;

    public Ressources() {
        // Initialisation du tableau avec 8 éléments à 0
        
        for(int i=0;i<4;i++){
            listeRessources = new int[8];
        }
    }
    public int getNbRessource(int elements_ressources){
        return listeRessources[elements_ressources];       
    }
    
    public void setNbRessouce(int elements_ressources,int new_ressources){
        listeRessources[elements_ressources]=new_ressources;
    }
    
    public void updateNbRessource(int elements_ressources, int quantité){
        int somme=listeRessources[elements_ressources]+quantité;
        if(somme>0){
            listeRessources[elements_ressources]=somme;
        }
        else{
            listeRessources[elements_ressources]=0;
        }
        
    }
    
    public int[] getAvailableRessources(){
        tableau=new int[]
        
        for(int i=0;i<8;i++){
            if(listeRessources[i]!=0){
                tableau[cpt];
            }
        }
    }
}
