package info.orleans.fr.bataillenavale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import info.orleans.fr.bataillenavale.modele.JoueurContreJoueur;
import info.orleans.fr.bataillenavale.modele.Options;

public class Accueil extends AppCompatActivity {

    Button boutonJoueurContreOrdinateur = null;
    Button boutonJoueurContreJoueur = null;
    Button boutonOptions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        setTitle(getResources().getString(R.string.title_accueil));
        ajouterToolbar();
        ajouterBoutons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accueil, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void ajouterToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Affichage des informations relatives à l'application dans une fenêtre.
                final AlertDialog alertDialog = new AlertDialog.Builder(Accueil.this).create();
                alertDialog.setTitle("À propos");
                alertDialog.setMessage("Bataille navale pour Android.");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                return true;
            }
        });
    }

    private void ajouterBoutons() {
        // Bouton "Joueur contre ordinateur"
        boutonJoueurContreOrdinateur = (Button) findViewById(R.id.bouton_joueur_contre_ordinateur);
        boutonJoueurContreOrdinateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent joueurContreOrdinateurIntent = new Intent(Accueil.this, JoueurContreOrdinateur.class);
                startActivity(joueurContreOrdinateurIntent);
            }
        });
        // Bouton "Joueur contre joueur"
        boutonJoueurContreJoueur = (Button) findViewById(R.id.bouton_joueur_contre_joueur);
        boutonJoueurContreJoueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent joueurContreJoueurIntent = new Intent(Accueil.this, JoueurContreJoueur.class);
                startActivity(joueurContreJoueurIntent);
            }
        });
        // Bouton "Options"
        boutonOptions = (Button) findViewById(R.id.bouton_options);
        boutonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent optionsIntent = new Intent(Accueil.this, Options.class);
                startActivity(optionsIntent);
            }
        });
    }

}
