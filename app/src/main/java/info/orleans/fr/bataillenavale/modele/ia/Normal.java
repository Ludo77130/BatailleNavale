package info.orleans.fr.bataillenavale.modele.ia;

import java.util.ArrayList;
import java.util.Random;

import info.orleans.fr.bataillenavale.modele.Position;

/**
 * Created by Romain on 24/12/2015.
 */

public class Normal extends Facile  {
    /*
    * cette ia est un peu moins débile, si elle touche elle rentre en chasse (continue tant qu'elle n'a pas coulé ça cible)
    */
    ArrayList<Position> memTir;// Liste mémorisant toute les zone visé par l'ia
    ArrayList<Position> lastTir;// Liste mémorisant les tirs réussi depuis le début de la chasse du bateau
    boolean chasseLance;// Boolean permettant de savoir si l'ia est en chasse ou non, ie il a touché un bateau au moins un fois le bateau sans qu'il soit coulé

    public Normal(ArrayList<Position> memTir, ArrayList<Position> lastTir, boolean chasseLance) {
        this.memTir = memTir;
        this.lastTir = lastTir;
        this.chasseLance = chasseLance;
    }

    public Normal() {
        this.memTir = new ArrayList();
        this.lastTir =  new ArrayList();
        this.chasseLance = false;
    }

    enum etatChasse {all , verticale , horizontale}

    public Normal(ArrayList<Position> memTir, Position lastTir, boolean etatLastTir) {
        this.memTir = new ArrayList<>();
        this.lastTir = new ArrayList<>();
        this.chasseLance = false;
    }

    /*
    * Cette fonction n'est probablement pas utile mais je la garde de coté au cas où
    * elle permet de cible une zone directement avec les indice
     */
    public Position selectionCase(int x, int y){
        Position p = new Position(x, y);
        memTir.add(p);
        return p;
    }

    /*
    * cette fonction permet de selectionner une case directement via une position prédéfini.
    * utile des que la chasse est lancé
    */
    public Position selectionCase(Position p){
        return p;
    }

    /*
    * Fonction que je n'utilise plus pour l'instant
    * elle permet de comparer deux X ou deux Y.
    * Je l'a met de coté au cas ou
    */
    public boolean compare(int x1,int x2){
        if (x1!=x2)return false;
        else return true;
    }

    /*
    * Cette fonction permet à l'ia de faire ces propre choix, elle sera à pofinner par la suite.
    * Surtout une fois que je serai comment se déroule le jeu
    */
    public Position selectionCase(boolean chasseLance, etatChasse etat){
        boolean bx;
        boolean by;
        Position p = null;
        Position p1 ;
        Position p2;
        if(!chasseLance) { // L'ia n'est pas en actuelement en chasse
            p = selectionCase();
        }else{
            switch(etat){ // l'ia est en chasse
                case all: // l'ia n'a réussi qu'une touche sur le bateau actuellement focus
                    p=lastTir.get(0);
                    int x=p.getX();
                    int y= p.getY();
                    p1=new Position (x,y+1);
                    p2=new Position (x+1,y);
                    // Nous avons 4 cas possibles
                    if(!memTir.contains(p1) && !lastTir.contains(p1)){
                       // etat=etat.horizontale; si touche
                        memTir.add(p1);
                        //lastTir.add(p1); si touche
                        return selectionCase(p1);
                    }else{
                        p1=new Position (x,y-1);
                        if(!memTir.contains(p1) && !lastTir.contains(p1)){
                          //  etat=etat.horizontale; si touche
                            memTir.add(p1);
                            //lastTir.add(p1); si touche
                            return selectionCase(p1);
                        }
                    }
                    if(!memTir.contains(p2) && !lastTir.contains(p2)){
                       // etat=etat.verticale; si touche
                        memTir.add(p2);
                        //lastTir.add(p2); si touche
                        return selectionCase(p2);
                    }else{
                        p2=new Position (x-1,y);
                        if(!memTir.contains(p2) && !lastTir.contains(p2)){
                            //etat=etat.verticale; si touche
                            memTir.add(p1);
                            //lastTir.add(p2); si touche
                            return selectionCase(p2);
                        }
                    }

                    // l'ia à touché deux fois et sait qu'il est positionné verticalement
                case verticale:
                    p=lastTir.get(lastTir.size() - 1);
                    x=p.getX();
                    y=p.getY();
                    p1=new Position (x,y+1);
                    if(memTir.contains(p1)){
                        p1.setY(lastTir.get(0).getY()-1);
                        memTir.add(p1);
                        return selectionCase(p1);
                    }else{
                        memTir.add(p1);
                        return selectionCase(p1);
                    }

                    // l'ia à touché deux fois et sait qu'il est positionné horizontalement
                case horizontale:
                    p=lastTir.get(lastTir.size() - 1);
                    x=p.getX();
                    y=p.getY();
                    p1=new Position (x+1,y);
                    if(memTir.contains(p1)){
                        p1.setX(lastTir.get(0).getX()-1);
                        memTir.add(p1);
                        return selectionCase(p1);
                    }else{
                        memTir.add(p1);
                        return selectionCase(p1);
                    }
            }
        }
        return p;
    }
}