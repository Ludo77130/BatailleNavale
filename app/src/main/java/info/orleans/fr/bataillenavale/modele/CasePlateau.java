package info.orleans.fr.bataillenavale.modele;

/**
 * Created by Wilsigh on 03/11/2015.
 */
public class CasePlateau implements StockageInterface{
    private Stockage stock;
    private int vie;
    private boolean coule;

    public CasePlateau(Stockage _stock){

        stock = (contientStockage(_stock)) ? _stock : null;

        switch(stock){
            case TROU:
            case INDICE:
                coule = true;
                vie = 0;
                break;
            case MER:
                coule = false;
                vie = 1;
                break;
            case PIECE:
                coule = false;
                //pour le moment vie = 1, voir à rajouter un argument de mode
                vie = 1;
                break;
            case MERTOUCHE:
            case PIECETOUCHE:
            case PIECECOULE:
                coule = true;
                vie = -2;
                break;
            default:
                //si stock vaut null
                coule = true;
                vie = -1;
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
        if(coule) return !coule;
        switch(this.stock){
            case MER:
                stock = Stockage.MERTOUCHE;
                vie--;
                coule = true;
                return coule;
            case PIECE:
            case PIECETOUCHE:
                vie--;
                if(vie > 0) {
                    stock = Stockage.PIECETOUCHE;
                }
                else {
                    stock = Stockage.PIECECOULE;
                    coule = true;
                }
                return true;
            default://normalement géré par le isCoule, il reste le null
                return false;
        }
    }

    public Stockage getStock() {
        return stock;
    }
}