package info.orleans.fr.bataillenavale.modele.bateau;

/**
 * Created by Wilsigh on 03/11/2015.
 */
public class BateauStandard implements RegleInterface {
    private int nombrePiece;
    private int[] emplacementPiece;

    public BateauStandard(Regle regle, int _nbPiece){
        nombrePiece = _nbPiece;
        emplacementPiece = new int [nombrePiece];
    }

    public boolean estCoule(){
        return (nombrePiece == 0);
    }



}
