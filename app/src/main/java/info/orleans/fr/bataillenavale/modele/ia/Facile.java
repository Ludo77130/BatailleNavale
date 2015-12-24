package info.orleans.fr.bataillenavale.modele.ia;

import java.util.ArrayList;
import java.util.Random;

import info.orleans.fr.bataillenavale.modele.Position;

/**
 * Created by Romain on 24/12/2015.
 */
public class Facile {
/*
Cette ia est simpliste, elle tir toujours aléatoirement
 */
    ArrayList<Position> memTir;// Liste mémorisant toute les zone visé par l'ia

    public Facile() {
        this.memTir = new ArrayList();
    }

    /*
    * Fonction permettant de choisir une case random
     */
    public Position selectionCase(){
        Random r = new Random();
        int i = (r.nextInt(10));
        int j = (r.nextInt(10));
        Position p= new Position(i,j);
        if(memTir.contains(p)){
            p=selectionCase();
        }else{
            memTir.add(p);
            return p;
        }
        return p;
    }
}
