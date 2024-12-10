
/**
 * Décrivez votre classe Resources ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import java.util.ArrayList;
public class Resources
{
    Resource re;
    private int[] listeResources;
    public Resources() {
        // Initialisation du tableau avec 8 éléments à 0
        
        for(int i=0;i<4;i++){
            listeResources = new int[8];
        }
    }
    public int getNbResource(Resource elements_resources){
        switch(elements_resources){
            case DIAMOND:
                return listeResources[1];
            case SAPPHIRE:
                return listeResources[2];
            case EMERALD:
                return listeResources[3];
            case RUBY:
                return listeResources[4];
            case ONYX:
                return listeResources[5];
            default:
                return 0;
        }
        
    }
    public void setNbResouce(Resource elements_resources,int new_resources){
        switch(elements_resources){
            case DIAMOND:
                listeResources[1]=new_resources;
            case SAPPHIRE:
                listeResources[2]=new_resources;
            case EMERALD:
                listeResources[3]=new_resources;
            case RUBY:
                listeResources[4]=new_resources;
            case ONYX:
                listeResources[5]=new_resources;
        }   
    }

    
    public void updateNbResource(Resource elements_resources, int quantité){
        int somme=getNbResource(elements_resources)+quantité;
        if(somme>0){
            setNbResouce(elements_resources,somme);
        }
        else{
            setNbResouce(elements_resources,0);
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
        for(int i=0;i<8;i++){//ajoute l'indice des pierres qui ont plus de 0 resources.
            if(listeResources[i]>0){
                tableau[elements]=i;
                elements+=1;
            }
        }
        return tableau;
        
        }
    }   
}
