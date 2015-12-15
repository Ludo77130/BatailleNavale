package info.orleans.fr.bataillenavale.modele;

public class Navire {

    public enum Orientation { HORIZONTAL, VERTICAL }

    private Position[] positions;
    private Orientation orientation;
    private int longueur;

    public Navire(int longueur) {
        this.longueur = longueur;
        positions = new Position[longueur];
        for (int i = 0; i < longueur; i++) {
            positions[i] = new Position();
        }
    }

    public Position[] getPositions() {
        return positions;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public int getLongueur() {
        return longueur;
    }

}
