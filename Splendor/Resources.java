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
    
    /**
     * Constructeur de la classe Resources
     * 
     * tableau d'entiers de la forme [tier, diamond, sapphire, emerald, ruby, onyx, point, type]
     */
    public Resources() {
        // Initialisation du tableau avec 8 éléments à 0
        
        for(int i=0;i<4;i++){
            listeResources = new int[8];
        }
    }
    
    /**
     * Acesseur du nombre de ressources disponible pour un type donné
     * 
     * @param le type de la ressource (Resource)
     * @return le nombre (int)
     */
    public int getNbResource(Resource elements_resources){
        switch(elements_resources){
            case Resource.DIAMOND:
                return listeResources[1];
            case Resource.SAPPHIRE:
                return listeResources[2];
            case Resource.EMERALD:
                return listeResources[3];
            case Resource.RUBY:
                return listeResources[4];
            case Resource.ONYX:
                return listeResources[5];
            default:
                return 0;
        }
        
    }
    
    /**
     * Manipulateur du nombre de ressources pour un type donné
     * 
     * @param le type de ressource dont on veut faire la modification (Resource)
     * @param la nouvelle valeur de la ressource (int)
     *
     */
    public void setNbResource(Resource elements_resources,int new_resources){
        if(elements_resources.equals(Resource.DIAMOND)){
            listeResources[1]=new_resources;  
            }
        else if (elements_resources.equals(Resource.SAPPHIRE)){
            listeResources[2]=new_resources;
        }
        else if (elements_resources.equals(Resource.EMERALD)){
            listeResources[3]=new_resources;
        }
        else if (elements_resources.equals(Resource.RUBY)){
            listeResources[4]=new_resources;
        }
        else if (elements_resources.equals(Resource.ONYX)){
            listeResources[5]=new_resources;
        }   
    }
    
    /**
     * Met à jour le nombre de ressource (pour un type donné) en fonction d'une quantité
     * si quantité > 0, ajoute
     * si quantité < 0, supprime
     * 
     * @param le type de ressource (Resource)
     * @param la quantité que l'on veut modifier (int)
     * 
     * @return le nombre de ressource actualisé
     */
    public void updateNbResource(Resource elements_resources, int quantité){
        int somme=getNbResource(elements_resources)+quantité;
        if(somme>0){
            setNbResource(elements_resources,somme);
        }
        else{
            setNbResource(elements_resources,0);
        }
    }
    
    
    /**
     * Permet de connaitre la disponibilité des ressources
     * 
     * @return les types de ressources (indice) pour lesquels des ressources sont disponibles sous forme de liste
     */
    public int[] getAvailableResources(){
        
        int cpt=0;
        
        for(int i=0;i<8;i++){//Permet de savoir quelle taille la liste doit etre
            if(listeResources[i]>0){
                cpt+=1;
            }
        }
        if (cpt==0){ //verifie que la liste n'est pas nulle
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
