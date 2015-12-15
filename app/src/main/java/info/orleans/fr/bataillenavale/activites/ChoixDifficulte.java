package info.orleans.fr.bataillenavale.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import info.orleans.fr.bataillenavale.R;

public class ChoixDifficulte extends AppCompatActivity {

    private Button boutonFacile, boutonDifficle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_difficulte);
        setTitle("Choix de la difficulté");
        boutonFacile = (Button) findViewById(R.id.bouton_facile);
        boutonDifficle = (Button) findViewById(R.id.bouton_difficile);
        ajouterOnClickListener(boutonFacile);
        ajouterOnClickListener(boutonDifficle);
    }

    private void ajouterOnClickListener(Button bouton) {
        final String difficulte;
        final Intent intent = new Intent(ChoixDifficulte.this, PlacementNavires.class);
        switch (bouton.getId()) {
            case R.id.bouton_facile:
                difficulte = "facile";
                break;
            case R.id.bouton_difficile:
                difficulte = "difficile";
                break;
            default:
                return;
        }
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("difficulte", difficulte);
                startActivity(intent);
            }
        });
    }
}
