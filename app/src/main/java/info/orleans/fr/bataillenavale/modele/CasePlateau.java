package info.orleans.fr.bataillenavale.modele;

/**
 * Created by Wilsigh on 03/11/2015.
 */
public class CasePlateau {
    private enum Stockage {MER, PIECE, TROU};
    private Stockage stock;
    private int vie;
    private boolean coule;

    public CasePlateau(Stockage _stock){
        stock = (contientStockage(_stock)) ? _stock : null;
        coule = (_stock.equals(Stockage.TROU)) ? true : false;

        if(_stock.equals(Stockage.MER)){
            vie = 1;
        }
        else{//piece
            //trouver un mode pour ça, pour le moment 1
            vie = 1;
        }

    }


    public boolean contientStockage(Stockage _stock){
        for (Stockage s : Stockage.values()) {
            if (s.equals(_stock)) {
                return true;
            }
        }
        return false;
    }

    public boolean estCoule(){
        return coule;
    }

    public boolean estTouche(){
        if(coule) return coule;
        else if (stock.equals(Stockage.MER) || stock.equals(Stockage.TROU)){
            coule = true;
            return coule;
        }
        else{//piece
           //A faire -> lecture du nombre de point de vie de la pièce
            return coule;
        }
    }
}
