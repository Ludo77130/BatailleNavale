package info.orleans.fr.bataillenavale.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import info.orleans.fr.bataillenavale.R;

public class Accueil extends AppCompatActivity {

    private Button boutonJoueurContreOrdinateur, boutonJoueurContreJoueur, boutonOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        boutonJoueurContreOrdinateur = (Button) findViewById(R.id.bouton_joueur_contre_ordinateur);
        boutonJoueurContreJoueur = (Button) findViewById(R.id.bouton_joueur_contre_joueur);
        boutonOptions = (Button) findViewById(R.id.bouton_options);
        ajouterOnClickListener(boutonJoueurContreOrdinateur);
        ajouterOnClickListener(boutonJoueurContreJoueur);
        ajouterOnClickListener(boutonOptions);
    }

    private void ajouterOnClickListener(Button bouton) {
        final Intent intent;
        switch (bouton.getId()) {
            case R.id.bouton_joueur_contre_ordinateur:
                intent = new Intent(Accueil.this, ChoixDifficulte.class);
                break;
            // TODO Gérer les autres boutons.
            default:
                return;
        }
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
