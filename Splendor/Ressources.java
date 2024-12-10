/**
 * Décrivez votre classe Ressources ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import java.util.ArrayList;
public class Resources
{
    private int[] listeResources;

    public Resources() {
        // Initialisation du tableau avec 8 éléments à 0
        
        for(int i=0;i<4;i++){
            listeResources = new int[8];
        }
    }
    public int getNbResource(int elements_resources){
        return listeResources[elements_resources];       
    }
    
    public void setNbResource(int elements_resources,int new_resources){
        listeResources[elements_resources]=new_resources;
    }
    
    public void updateNbResource(int elements_resources, int quantité){
        int somme=listeResources[elements_resources]+quantité;
        if(somme>0){
            listeResources[elements_resources]=somme;
        }
        else{
            listeResources[elements_resources]=0;
        }
        
    }
    
    public int[] getAvailableResources(){
        
        int cpt=-1;
        
        for(int i=0;i<8;i++){//Permet de savoir quelle taille la liste doit etre
            if(listeResources[i]>0){
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
            if(listeResources[i]>0){
                tableau[elements]=i;
                elements+=1;
            }
        }
        return tableau;
            }
        }
    }
