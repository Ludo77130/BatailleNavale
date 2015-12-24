package info.orleans.fr.bataillenavale.modele.ia;

import java.util.ArrayList;

import info.orleans.fr.bataillenavale.modele.Position;

/**
 * Created by Romain on 24/12/2015.
 */
public class Difficile extends Normal {
    /*
    * cette ia sera la continuité des l'ia Normal, elle aura en plus un quadrillage de possibilité de tir en plus
    */
    ArrayList<Position> memTir;// Liste mémorisant toute les zone visé par l'ia
    ArrayList<Position> lastTir;// Liste mémorisant les tirs réussi depuis le début de la chasse du bateau
    boolean chasseLance;// Boolean permettant de savoir si l'ia est en chasse ou non, ie il a touché un bateau au moins un fois le bateau sans qu'il soit coulé
}
