package info.orleans.fr.bataillenavale.activites;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import info.orleans.fr.bataillenavale.R;
import info.orleans.fr.bataillenavale.modele.Navire;
import info.orleans.fr.bataillenavale.modele.Position;

public class PlacementNavires extends AppCompatActivity {

    private static final int TAILLE_GRILLE_JOUABLE = 10;
    private static final int NB_NAVIRES = 7;
    private static final int LONG_PORTE_NAVIRES = 5;
    private static final int LONG_CROISEUR = 4;
    private static final int LONG_CONTRE_TORPILLEUR = 3;
    private static final int LONG_SOUS_MARIN = 3;
    private static final int LONG_TORPILLEUR = 2;


    private Button boutonGenererAleatoirement, boutonJouer;
    private GridLayout grillePlacementNavires;
    private ImageView[][] imagesGrillePlacementNavires;
    private Navire[] naviresJoueur;
    private Navire[] naviresEnnemi;
    private boolean[][] positionsNaviresJoueur;
    private boolean[][] positionsNaviresEnnemi;
    private String difficulte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_navires);
        setTitle("Placement des navires");
        Bundle extras = getIntent().getExtras();
        difficulte = extras.getString("difficulte");
        boutonGenererAleatoirement = (Button) findViewById(R.id.bouton_generer_aleatoirement);
        boutonJouer = (Button) findViewById(R.id.bouton_jouer);
        ajouterOnClickListener(boutonGenererAleatoirement);
        ajouterOnClickListener(boutonJouer);
        grillePlacementNavires = (GridLayout) findViewById(R.id.grid_layout_placement_navires);
        naviresJoueur = genererNavires();
        positionsNaviresJoueur = placerNavires(naviresJoueur);
        imagesGrillePlacementNavires = new ImageView[TAILLE_GRILLE_JOUABLE][TAILLE_GRILLE_JOUABLE];
        dessinerGrille(grillePlacementNavires, imagesGrillePlacementNavires);
        if (difficulte.equals("facile") || difficulte.equals("difficile")) {
            naviresEnnemi = genererNavires();
            positionsNaviresEnnemi = placerNavires(naviresEnnemi);
        }
    }

    private void ajouterOnClickListener(final Button bouton) {
        switch (bouton.getId()) {
            case R.id.bouton_generer_aleatoirement:
                bouton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        positionsNaviresJoueur = placerNavires(naviresJoueur);
                        majGrille(imagesGrillePlacementNavires);
                    }
                });
                break;
            case R.id.bouton_jouer:
                bouton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PlacementNavires.this, Jeu.class);
                        Bundle bundle = new Bundle();
                        intent.putExtra("difficulte", difficulte);
                        bundle.putSerializable("positionsNaviresJoueur", positionsNaviresJoueur);
                        if (difficulte.equals("facile") || difficulte.equals("difficile"))
                            bundle.putSerializable("positionsNaviresEnnemi",
                                    positionsNaviresEnnemi);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            default: return;
        }
    }

    private void majGrille(final ImageView[][] imagesGrille) {
        grillePlacementNavires.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < TAILLE_GRILLE_JOUABLE; i++) {
                    for (int j = 0; j < TAILLE_GRILLE_JOUABLE; j++) {
                        if (!positionsNaviresJoueur[i][j])
                            imagesGrille[i][j].setBackgroundDrawable(
                                    getResources().getDrawable(R.drawable.eau));
                        else
                            imagesGrille[i][j].setBackgroundDrawable(
                                    getResources().getDrawable(R.drawable.navire));
                    }
                }
            }
        });
    }

    private void dessinerGrille(final GridLayout gridLayout, final ImageView[][] imagesGrille) {
        final TextView[] ligneLettres, colonneNombres;
        ligneLettres = new TextView[TAILLE_GRILLE_JOUABLE];
        colonneNombres = new TextView[TAILLE_GRILLE_JOUABLE +1];
        grillePlacementNavires.post(new Runnable() {
            @Override
            public void run() {
                GridLayout.LayoutParams params;
                int height, width;
                for (int i = 0; i < TAILLE_GRILLE_JOUABLE + 1; i++) {
                    params = new GridLayout.LayoutParams();
                    height = gridLayout.getHeight();
                    width = gridLayout.getWidth();
                    params.height = height / (TAILLE_GRILLE_JOUABLE + 1);
                    params.width = width / (TAILLE_GRILLE_JOUABLE + 1);
                    colonneNombres[i] = new TextView(PlacementNavires.this);
                    colonneNombres[i].setLayoutParams(params);
                    colonneNombres[i].setText(determinerNombre(i));
                    colonneNombres[i].setTypeface(null, Typeface.BOLD);
                    colonneNombres[i].setGravity(Gravity.CENTER);
                    gridLayout.addView(colonneNombres[i]);
                }
                for (int i = 0; i < TAILLE_GRILLE_JOUABLE; i++) {
                    params = new GridLayout.LayoutParams();
                    height = gridLayout.getHeight();
                    width = gridLayout.getWidth();
                    params.height = height / (TAILLE_GRILLE_JOUABLE + 1);
                    params.width = width / (TAILLE_GRILLE_JOUABLE + 1);
                    ligneLettres[i] = new TextView(PlacementNavires.this);
                    ligneLettres[i].setLayoutParams(params);
                    ligneLettres[i].setText(Character.toString(determinerLettre(i)));
                    ligneLettres[i].setTypeface(null, Typeface.BOLD);
                    ligneLettres[i].setGravity(Gravity.CENTER);
                    gridLayout.addView(ligneLettres[i]);
                    for (int j = 0; j < TAILLE_GRILLE_JOUABLE; j++) {
                        params = new GridLayout.LayoutParams();
                        height = gridLayout.getHeight();
                        width = gridLayout.getWidth();
                        params.height = height / (TAILLE_GRILLE_JOUABLE + 1);
                        params.width = width / (TAILLE_GRILLE_JOUABLE + 1);
                        imagesGrille[i][j] = new ImageView(PlacementNavires.this);
                        imagesGrille[i][j].setLayoutParams(params);
                        if (!positionsNaviresJoueur[i][j])
                            imagesGrille[i][j].setBackgroundDrawable(
                                    getResources().getDrawable(R.drawable.eau));
                        else
                            imagesGrille[i][j].setBackgroundDrawable(
                                    getResources().getDrawable(R.drawable.navire));
                        gridLayout.addView(imagesGrille[i][j]);
                    }
                }
            }
        });
    }

    private String determinerNombre(int i) {
        if (i == 0)
            return " ";
        else
            return  Integer.toString(i);
    }

    private char determinerLettre(int i) {
        return Character.toChars(65+i)[0];
    }

    private boolean[][] placerNavires(Navire[] navires) {
        boolean[][] positionsNavires = new boolean[TAILLE_GRILLE_JOUABLE][TAILLE_GRILLE_JOUABLE];
        boolean[][] positionsValides = new boolean[TAILLE_GRILLE_JOUABLE][TAILLE_GRILLE_JOUABLE];
        for (int i=0; i< TAILLE_GRILLE_JOUABLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE_JOUABLE; j++) {
                positionsNavires[i][j] = false;
                positionsValides[i][j] = true;
            }
        }
        boolean navireEnPlace, teteEnPlace;
        for (int i = 0; i < NB_NAVIRES; i++) {
            navireEnPlace = false;
            while (!navireEnPlace) {
                teteEnPlace = false;
                while (!teteEnPlace) {
                    navires[i].setOrientation(genererOrientation());
                    teteEnPlace =
                            placerTete(navires[i], positionsValides);
                }
                navireEnPlace =
                        placerResteNavire(navires[i], positionsNavires, positionsValides);
            }
        }
        return positionsNavires;
    }

    private boolean placerResteNavire(Navire navire, boolean[][] positionsNavire,
                                      boolean[][] positionsValides) {
        int x, y, xTete, yTete;
        xTete = navire.getPositions()[0].getX();
        yTete = navire.getPositions()[0].getY();
        if (navire.getOrientation() == Navire.Orientation.HORIZONTAL) {
            for (int i = 1; i < navire.getLongueur(); i++) {
                x = xTete;
                y = yTete + i;
                if (estPositionValide(x, y, positionsValides)) {
                    navire.getPositions()[i].setX(x);
                    navire.getPositions()[i].setY(y);
                } else return false;
            }
            for (int i = 0; i < navire.getLongueur(); i++) {
                x = navire.getPositions()[i].getX();
                y = navire.getPositions()[i].getY();
                positionsNavire[x][y] = true;
            }
        } else {
            for (int i = 1; i < navire.getLongueur(); i++) {
                x = xTete+i;
                y = yTete;
                if (estPositionValide(x, y, positionsValides)) {
                    navire.getPositions()[i].setX(x);
                    navire.getPositions()[i].setY(y);
                } else return false;
            }
            for (int i = 0; i < navire.getLongueur(); i++) {
                x = navire.getPositions()[i].getX();
                y = navire.getPositions()[i].getY();
                positionsNavire[x][y] = true;
            }
        }
        determinerPositionsInvalides(navire, positionsValides);
        return true;
    }

    private void determinerPositionsInvalides(Navire navire, boolean[][] positionsValides) {
        int x, y;
        Position[] coordinates = navire.getPositions();
        for (int i = 0; i < coordinates.length; i++) {
            x = coordinates[i].getX();
            y = coordinates[i].getY();
            positionsValides[x][y] = false;
            if (navire.getOrientation() == Navire.Orientation.HORIZONTAL) {
                if (i == 0) {
                    if (x - 1 >= 0 && y - 1 >= 0) positionsValides[x - 1][y - 1] = false;
                    if (x + 1 <= 9 && y - 1 >= 0) positionsValides[x + 1][y - 1] = false;
                }
                if (i == coordinates.length-1) {
                    if (x - 1 >= 0 && y + 1 <= 9) positionsValides[x-1][y + 1] = false;
                    if (x + 1 <= 9 && y + 1 <= 9) positionsValides[x+1][y + 1] = false;
                }
            } else {
                if (i == 0) {
                    if (x - 1 >= 0 && y - 1 >= 0) positionsValides[x - 1][y - 1] = false;
                    if (x - 1 >= 0 && y + 1 <= 9) positionsValides[x - 1][y + 1] = false;
                }
                if (i == coordinates.length-1) {
                    if (x+1 <= 9 && y-1 >= 0) positionsValides[x + 1][y - 1] = false;
                    if (x+1 <= 9 && y+1 <= 9) positionsValides[x + 1][y + 1] = false;
                }
            }
            if (x - 1 >= 0) positionsValides[x - 1][y] = false;
            if (x + 1 <= 9) positionsValides[x + 1][y] = false;
            if (y - 1 >= 0) positionsValides[x][y - 1] = false;
            if (y + 1 <= 9) positionsValides[x][y + 1] = false;
        }
    }

    private boolean placerTete(Navire navire, boolean[][] positionsValides) {
        int x, y;
        Position coordinate = navire.getPositions()[0];
        x = genererChiffre();
        y = genererChiffre();
        if (estPositionValide(x, y, positionsValides)) {
            coordinate.setX(x);
            coordinate.setY(y);
            return true;
        }
        return false;
    }

    private boolean estPositionValide(int x, int y, boolean[][] positionsValides) {
        if (x < 0 || x > 9 || y < 0 || y > 9)
            return false;
        return positionsValides[x][y];
    }

    private Navire[] genererNavires() {
        Navire[] ships = new Navire[NB_NAVIRES];
        ships[0] = new Navire(LONG_PORTE_NAVIRES);
        ships[1] = new Navire(LONG_CROISEUR);
        ships[2] = new Navire(LONG_CROISEUR);
        ships[3] = new Navire(LONG_CONTRE_TORPILLEUR);
        ships[4] = new Navire(LONG_SOUS_MARIN);
        ships[5] = new Navire(LONG_TORPILLEUR);
        ships[6] = new Navire(LONG_TORPILLEUR);
        return ships;
    }

    private Navire.Orientation genererOrientation() {
        Random rand = new Random();
        if (rand.nextInt(2) == 0)
            return Navire.Orientation.HORIZONTAL;
        return Navire.Orientation.VERTICAL;
    }

    private int genererChiffre() {
        Random rand = new Random();
        return rand.nextInt(10);
    }
}
