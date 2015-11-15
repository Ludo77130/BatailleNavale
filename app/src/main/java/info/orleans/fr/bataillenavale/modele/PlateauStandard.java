package info.orleans.fr.bataillenavale.modele;

/**
 * Created by Wilsigh on 03/11/2015.
 */
public class PlateauStandard implements PlateauInterface, StockageInterface{
    private Stockage stock;
    private CasePlateau[][] plateau;
    private int hauteur, largeur;

    public PlateauStandard(int _hauteur, int _largeur){
        hauteur = _hauteur+1;
        largeur = _largeur+1;
        plateau = new CasePlateau[hauteur][largeur];
        initPlateau(plateau);
    }

    private void initPlateau(CasePlateau[][] _plateau){
        int i, j;

        for(i = 0; i< hauteur; i++){
            for(j = 0; j < largeur; j++){
                if(i == 0 || j == 0){
                    plateau[i][j] = new CasePlateau(Stockage.INDICE);
                }
                else{
                    plateau[i][j] = new CasePlateau(Stockage.MER);
                }
            }
        }
    }

    public int tireCase(int _hauteur, int _largeur){
        //retourner un code de bienséance ?
        if(0 < _hauteur && _hauteur <= hauteur &&
                0 < _largeur && _largeur <= largeur){
            if(plateau[_hauteur][_largeur].estCoule()){
                return 1;//déjà coulé
            }
            else if(plateau[_hauteur][_largeur].estTouche()){
                return 0;//tout s'est bien passé
            }
            else return 2;//erreur
        }
        return 3;//t'as cliqué où ??
    }
}
