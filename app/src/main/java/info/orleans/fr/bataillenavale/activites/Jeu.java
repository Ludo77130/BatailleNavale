package info.orleans.fr.bataillenavale.activites;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import info.orleans.fr.bataillenavale.R;

public class Jeu extends AppCompatActivity {

    private static final int TAILLE_GRILLE_JOUABLE = 10;

    private String difficulte;
    private boolean[][] positionsNaviresJoueur;
    private boolean[][] positionsNaviresEnnemi;
    private TextView textViewInfoTour;
    private GridLayout grilleJoueur, grilleEnnemi;
    private ImageView[][] imagesGrilleJoueur, imagesGrilleEnnemi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);
        setTitle("Bataille navale");
        Bundle extras = getIntent().getExtras();
        difficulte = extras.getString("difficulte");
        positionsNaviresJoueur = obtenirPositionsNavires("positionsNaviresJoueur");
        if (difficulte.equals("facile") || difficulte.equals("difficile"))
            positionsNaviresEnnemi = obtenirPositionsNavires("positionsNaviresEnnemi");
        textViewInfoTour = (TextView) findViewById(R.id.text_view_info_tour);
        grilleJoueur = (GridLayout) findViewById(R.id.grid_layout_joueur);
        grilleEnnemi = (GridLayout) findViewById(R.id.grid_layout_ennemi);
        imagesGrilleJoueur = new ImageView[TAILLE_GRILLE_JOUABLE][TAILLE_GRILLE_JOUABLE];
        dessinerGrille(grilleJoueur, imagesGrilleJoueur, positionsNaviresJoueur);
        imagesGrilleEnnemi = new ImageView[TAILLE_GRILLE_JOUABLE][TAILLE_GRILLE_JOUABLE];
        dessinerGrille(grilleEnnemi, imagesGrilleEnnemi, positionsNaviresEnnemi);
        textViewInfoTour = (TextView) findViewById(R.id.text_view_info_tour);
        textViewInfoTour.setText("À vous de jouer !");
    }

    private void dessinerGrille(final GridLayout grille, final ImageView[][] imagesGrille,
                                final boolean[][] positionsNavire) {
        final TextView[] ligneLettres, colonneNombres;
        ligneLettres = new TextView[TAILLE_GRILLE_JOUABLE];
        colonneNombres = new TextView[TAILLE_GRILLE_JOUABLE +1];
        grille.post(new Runnable() {
            @Override
            public void run() {
                GridLayout.LayoutParams params;
                int height, width;
                for (int i = 0; i < TAILLE_GRILLE_JOUABLE + 1; i++) {
                    params = new GridLayout.LayoutParams();
                    height = grille.getHeight();
                    width = grille.getWidth();
                    params.height = height / (TAILLE_GRILLE_JOUABLE + 1);
                    params.width = width / (TAILLE_GRILLE_JOUABLE + 1);
                    colonneNombres[i] = new TextView(Jeu.this);
                    colonneNombres[i].setLayoutParams(params);
                    colonneNombres[i].setText(determinerNombre(i));
                    colonneNombres[i].setTypeface(null, Typeface.BOLD);
                    colonneNombres[i].setGravity(Gravity.CENTER);
                    grille.addView(colonneNombres[i]);
                }
                for (int i = 0; i < TAILLE_GRILLE_JOUABLE; i++) {
                    params = new GridLayout.LayoutParams();
                    height = grille.getHeight();
                    width = grille.getWidth();
                    params.height = height / (TAILLE_GRILLE_JOUABLE + 1);
                    params.width = width / (TAILLE_GRILLE_JOUABLE + 1);
                    ligneLettres[i] = new TextView(Jeu.this);
                    ligneLettres[i].setLayoutParams(params);
                    ligneLettres[i].setText(Character.toString(determinerLettre(i)));
                    ligneLettres[i].setTypeface(null, Typeface.BOLD);
                    ligneLettres[i].setGravity(Gravity.CENTER);
                    grille.addView(ligneLettres[i]);
                    for (int j = 0; j < TAILLE_GRILLE_JOUABLE; j++) {
                        params = new GridLayout.LayoutParams();
                        height = grille.getHeight();
                        width = grille.getWidth();
                        params.height = height / (TAILLE_GRILLE_JOUABLE + 1);
                        params.width = width / (TAILLE_GRILLE_JOUABLE + 1);
                        imagesGrille[i][j] = new ImageView(Jeu.this);
                        imagesGrille[i][j].setLayoutParams(params);
                        if (!positionsNavire[i][j] || (grille.getId() == R.id.grid_layout_ennemi))
                            imagesGrille[i][j].setBackgroundDrawable(
                                    getResources().getDrawable(R.drawable.eau));
                        else
                            imagesGrille[i][j].setBackgroundDrawable(
                                    getResources().getDrawable(R.drawable.navire));
                        grille.addView(imagesGrille[i][j]);
                    }
                }
            }
        });
    }

    boolean[][] obtenirPositionsNavires(String key) {
        Object[] objectPositionsNavires = (Object[]) getIntent().getExtras().getSerializable(key);
        boolean[][] positionsNavires = new boolean[objectPositionsNavires.length][];
        for (int i = 0; i < objectPositionsNavires.length; i++)
            positionsNavires[i] = (boolean[]) objectPositionsNavires[i];
        return positionsNavires;
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

}
