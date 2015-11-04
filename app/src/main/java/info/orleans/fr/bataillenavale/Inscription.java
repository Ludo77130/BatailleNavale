package info.orleans.fr.bataillenavale;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Inscription extends AppCompatActivity {

    Button boutonInscription;
    Button boutonRetour;
    EditText nom;
    EditText prenom;
    EditText login;
    EditText motDePasse;
    EditText confirmationMotDePasse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boutonInscription = (Button) findViewById(R.id.inscription);
        boutonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ici une intéraction avec la bdd
                //ajoute le compte si tout est ok
            }
        });

        boutonRetour = (Button) findViewById(R.id.retour);
        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent joueurContreJoueurIntent = new Intent(Inscription.this, JoueurContreJoueur.class);
                startActivity(joueurContreJoueurIntent);
            }
        });

        nom = (EditText) findViewById(R.id.nom);
        nom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(nom.getText().equals("Nom")){
                    nom.setText("");
                }
            }
        });
    }

}
