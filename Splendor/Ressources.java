
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
    
    public void setNbRessource(int elements_ressources,int new_ressources){
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
        
        int cpt=-1;
        
        for(int i=0;i<8;i++){//Permet de savoir quelle taille la liste doit etre
            if(listeRessources[i]>0){
                cpt+=1;
            }
        }
        if (cpt==-1){ //verifie que la liste n'est pas nul
            return null;
        }
        else{
        int[] tableau= new int[cpt];
        int elements=0;
        for(int i=0;i<8;i++){//ajoute l'indice des pierres qui ont plus de 0 ressources.
            if(listeRessources[i]>0){
                tableau[elements]=i;
                elements+=1;
            }
        }
        return tableau;
            }
        }
    }
